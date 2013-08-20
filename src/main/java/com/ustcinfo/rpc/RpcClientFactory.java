/**        
 * Copyright (c) 2013 by 苏州科大国创信息技术有限公司.    
 */    
package com.ustcinfo.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ustcinfo.rpc.client.Client;
import com.ustcinfo.rpc.netty4.Netty4ClientFactory;

/**
 * Create on @2013-8-20 @下午2:39:50 
 * @author bsli@ustcinfo.com
 */
public class RpcClientFactory {
	private static final Logger LOGGER = LoggerFactory.getLogger(RpcClientFactory.class);

	/** 
     * 引用服务 
     *  
     * @param <T> 接口泛型 
     * @param interfaceClass 接口类型 
     * @param host 服务器主机名 
     * @param port 服务器端口 
     * @return 远程服务 
     * @throws RpcException 
     */  
    @SuppressWarnings("unchecked")  
    public static <T> T refer(final Class<T> interfaceClass, final String host, final int port) throws RpcException {  
        if (interfaceClass == null)  
            throw new IllegalArgumentException("Interface class == null");  
        if (! interfaceClass.isInterface())  
            throw new IllegalArgumentException("The " + interfaceClass.getName() + " must be interface class!");  
        if (host == null || host.length() == 0)  
            throw new IllegalArgumentException("Host == null!");  
        if (port <= 0 || port > 65535)  
            throw new IllegalArgumentException("Invalid port " + port);  
        LOGGER.info("Get remote service " + interfaceClass.getName() + " from server " + host + ":" + port);  
        
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[] {interfaceClass}, new JDKInvocationHandler(interfaceClass, host, port));  
    }  
    
    private static class JDKInvocationHandler implements InvocationHandler {
    	private Class<?> interfaceClass;
    	
    	private Client client;
    	
		public JDKInvocationHandler(Class<?> interfaceClass, String host, int port) {
			this.interfaceClass = interfaceClass;
			try {
				client = Netty4ClientFactory.getInstance().get(host, port, 2000);
			} catch (Exception e) {
				throw new RpcException(e.getMessage(), e);
			}
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] arguments)
				throws Throwable {
			return client.invokeSync(interfaceClass.getName(), method.getName(), method.getParameterTypes(), arguments, 10000, Codecs.JAVA_CODEC);
		}
    	
    }
}
