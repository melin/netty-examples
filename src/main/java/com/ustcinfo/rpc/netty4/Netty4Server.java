package com.ustcinfo.rpc.netty4;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ustcinfo.rpc.netty4.serialize.Netty4ProtocolDecoder;
import com.ustcinfo.rpc.netty4.serialize.Netty4ProtocolEncoder;
import com.ustcinfo.rpc.protocol.ProtocolUtils;
import com.ustcinfo.rpc.server.Server;

public class Netty4Server implements Server {

	private static final Log LOGGER = LogFactory.getLog(Netty4Server.class);
	private AtomicBoolean startFlag = new AtomicBoolean(false);

	private NioEventLoopGroup bossGroup;
	private NioEventLoopGroup ioGroup;
	private EventExecutorGroup businessGroup;
	private final int businessThreads;

	public Netty4Server(int businessThreads) {
		this.businessThreads = businessThreads;
	}

	public void start(int listenPort)
	        throws Exception {
		if (!startFlag.compareAndSet(false, true)) {
			return;
		}
		bossGroup = new NioEventLoopGroup();
		ioGroup = new NioEventLoopGroup();
		businessGroup = new DefaultEventExecutorGroup(businessThreads);

		ServerBootstrap b = new ServerBootstrap();
		b.group(bossGroup, ioGroup).channel(NioServerSocketChannel.class)
		        .childOption(ChannelOption.TCP_NODELAY, 
		                     Boolean.parseBoolean(System.getProperty(
		                             "nfs.rpc.tcp.nodelay", "true")))
		        .childOption(ChannelOption.SO_REUSEADDR, 
		                     Boolean.parseBoolean(System.getProperty(
		                             "nfs.rpc.tcp.reuseaddress", "true")))
		        .childHandler(new ChannelInitializer<SocketChannel>() {
			        @Override
			        public void initChannel(SocketChannel ch)
			                throws Exception {
						ch.pipeline().addLast("decoder",
								new Netty4ProtocolDecoder());
						ch.pipeline().addLast("encoder",
								new Netty4ProtocolEncoder());
						ch.pipeline().addLast(businessGroup, "handler",
								new Netty4ServerHandler());
			        }
		        });

		b.bind(listenPort).sync();
		LOGGER.info("Server started,listen at: " + listenPort + ", businessThreads is " + businessThreads);
	}

	public void registerProcessor(String serviceName, Object serviceInstance) {
		ProtocolUtils.getServerHandler().registerProcessor(serviceName, serviceInstance);
	}

	public void stop() throws Exception {
		LOGGER.warn("Server stop!");
		bossGroup.shutdownGracefully();
		ioGroup.shutdownGracefully();
		businessGroup.shutdownGracefully();
		startFlag.set(false);
	}

}
