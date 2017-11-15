package com.asms.schoolmgmt.response;

import com.asms.common.response.SuccessResponse;


/*
 * RegistrationResponse.java is to return user registration response
 */

public class RegistrationResponse extends SuccessResponse{
	
	private String domain;
	
	private int schoolId;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	
	

	

}
