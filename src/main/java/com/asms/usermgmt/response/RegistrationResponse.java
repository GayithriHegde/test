package com.asms.usermgmt.response;

import com.asms.common.response.SuccessResponse;


/*
 * RegistrationResponse.java is to return user registration response
 */

public class RegistrationResponse extends SuccessResponse{
	
	private String userId;
	
	private String isNew;
	
	private float progressPercentage = 0;
	
	private String message;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public float getProgressPercentage() {
		return progressPercentage;
	}

	public void setProgressPercentage(float progressPercentage) {
		this.progressPercentage = progressPercentage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
