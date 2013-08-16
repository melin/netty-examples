package com.ustcinfo.rpc.netty4.serialize;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import com.ustcinfo.rpc.protocol.ProtocolUtils;

public class Netty4ProtocolEncoder extends MessageToByteEncoder<Object> {
	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out)
			throws Exception {
		Netty4ByteBufferWrapper byteBufferWrapper = new Netty4ByteBufferWrapper(out);
		ProtocolUtils.encode(msg, byteBufferWrapper);
	}

}
