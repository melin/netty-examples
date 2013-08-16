package com.ustcinfo.rpc.protocol;

public interface Decoder {

	/**
	 * decode byte[] to Object
	 */
	public Object decode(String className,byte[] bytes) throws Exception;
	
}
