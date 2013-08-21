/**        
 * Copyright (c) 2013 by 苏州科大国创信息技术有限公司.    
 */    
package com.ustcinfo.rpc;

/**
 * Create on @2013-8-20 @下午2:06:08 
 * @author bsli@ustcinfo.com
 */
public class HelloWorldImpl implements HelloWorld {

	@Override
	public Result queryUser(User user) {
		return new Result("hello " + user.getUsername());
	}

}
