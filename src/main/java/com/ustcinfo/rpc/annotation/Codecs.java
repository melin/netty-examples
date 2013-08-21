/**        
 * Copyright (c) 2013 by 苏州科大国创信息技术有限公司.    
 */    
package com.ustcinfo.rpc.annotation;

import com.ustcinfo.rpc.protocol.Decoder;
import com.ustcinfo.rpc.protocol.Encoder;
import com.ustcinfo.rpc.protocol.HessianDecoder;
import com.ustcinfo.rpc.protocol.HessianEncoder;
import com.ustcinfo.rpc.protocol.JavaDecoder;
import com.ustcinfo.rpc.protocol.JavaEncoder;
import com.ustcinfo.rpc.protocol.KryoDecoder;
import com.ustcinfo.rpc.protocol.KryoEncoder;

/**
 * Create on @2013-8-21 @上午10:42:20 
 * @author bsli@ustcinfo.com
 */
public enum Codecs {
	JAVA_CODEC, HESSIAN_CODEC, KRYO_CODEC;
	
	private static Encoder[] encoders = new Encoder[5];
	
	private static Decoder[] decoders = new Decoder[5];
	
	static {
		encoders[JAVA_CODEC.ordinal()] = new JavaEncoder();
		encoders[HESSIAN_CODEC.ordinal()] = new HessianEncoder();
		encoders[KRYO_CODEC.ordinal()] = new KryoEncoder();
		
		decoders[JAVA_CODEC.ordinal()] = new JavaDecoder();
		decoders[HESSIAN_CODEC.ordinal()] = new HessianDecoder();
		decoders[KRYO_CODEC.ordinal()] = new KryoDecoder();
	}
	
	public static Encoder getEncoder(int encoderKey){
		return encoders[encoderKey];
	}
	
	public static Decoder getDecoder(int decoderKey){
		return decoders[decoderKey];
	}
}
