package com.asms.common.response;

import com.asms.Exception.AsmsException;

public class FailureResponse extends Response {
	
	private String errorDescription;
	
	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public FailureResponse() {
		this.setStatus("FAILURE");
	}
	
	public FailureResponse(AsmsException asmsException) {
		this.setStatus("FAILURE");
		this.setCode(Integer.parseInt(asmsException.getCode()));
		this.setErrorDescription(asmsException.getDescription());
		
	}
}