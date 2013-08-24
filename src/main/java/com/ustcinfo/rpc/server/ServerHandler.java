package com.ustcinfo.rpc.server;

import com.ustcinfo.rpc.RequestWrapper;
import com.ustcinfo.rpc.ResponseWrapper;

public interface ServerHandler {

	/**
	 * register business handler,provide for Server
	 */
	public void registerProcessor(String instanceName, Object instance);

	/**
	 * handle the request
	 */
	public ResponseWrapper handleRequest(final RequestWrapper request);

}