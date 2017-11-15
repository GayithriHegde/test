package com.asms.common.response;


/*
 * Base class for response
 * Properties are and status
 * 
 */

public class Response {
	
	private int code;
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
