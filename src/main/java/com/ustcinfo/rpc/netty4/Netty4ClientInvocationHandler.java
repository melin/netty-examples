package com.ustcinfo.rpc.netty4;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;

import com.ustcinfo.rpc.client.AbstractClientInvocationHandler;
import com.ustcinfo.rpc.client.ClientFactory;

public class Netty4ClientInvocationHandler extends
		AbstractClientInvocationHandler {

	public Netty4ClientInvocationHandler(List<InetSocketAddress> servers,
			int clientNums, int connectTimeout, String targetInstanceName,
			Map<String, Integer> methodTimeouts, int codectype) {
		super(servers, clientNums, connectTimeout, targetInstanceName,
				methodTimeouts, codectype);
	}

	public ClientFactory getClientFactory() {
		return Netty4ClientFactory.getInstance();
	}

}
