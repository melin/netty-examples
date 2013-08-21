/**        
 * Copyright (c) 2013 by 苏州科大国创信息技术有限公司.    
 */    
package com.ustcinfo.rpc;

/**
 * Create on @2013-8-20 @下午2:09:25 
 * @author bsli@ustcinfo.com
 */
public class ClientTest {
	public static void main(String[] args) {
		try {
			HelloWorld helloWorld = RpcClientFactory.refer(HelloWorld.class, "localhost", 8080);
			
			Result result = helloWorld.queryUser(new User("melin")); 
			
			System.out.println(result.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
