package com.ustcinfo.rpc.server;
/**
 * nfs-rpc
 *   Apache License
 *   
 *   http://code.google.com/p/nfs-rpc (c) 2011
 */
/**
 * RPC Server Interface
 * 
 * @author <a href="mailto:bluedavy@gmail.com">bluedavy</a>
 */
public interface Server {

	/**
	 * start server at listenPort,requests will be handled in businessThreadPool
	 */
	public void start(int listenPort) throws Exception;
	
	/**
	 * register business handler
	 */
	public void registerProcessor(String serviceName,Object serviceInstance);
	
	/**
	 * stop server
	 */
	public void stop() throws Exception;
	
}
