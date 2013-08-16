package com.ustcinfo.rpc.client;

public enum SendLimitPolicy {

	REJECT, // Reject send request and throw exception
	WAIT1SECOND // Wait 1 second then retry,if failed again,then throw exception
	
}
