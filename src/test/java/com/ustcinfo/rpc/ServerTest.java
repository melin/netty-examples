/**        
 * Copyright (c) 2013 by 苏州科大国创信息技术有限公司.    
 */    
package com.ustcinfo.rpc;

import com.ustcinfo.rpc.netty4.Netty4Server;

/**
 * Create on @2013-8-20 @下午2:09:47 
 * @author bsli@ustcinfo.com
 */
public class ServerTest {
	public static void main(String[] args) {
		Netty4Server server = new Netty4Server(10);
		server.registerProcessor(HelloWorld.class.getName(), new HelloWorldImpl());
		
		try {
			server.start(8080);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
