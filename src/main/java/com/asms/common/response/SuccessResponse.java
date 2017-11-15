package com.asms.common.response;



public class SuccessResponse extends Response{
	
	public SuccessResponse() {
		this.setStatus("SUCCESS");
		this.setCode(200);
	}

}
