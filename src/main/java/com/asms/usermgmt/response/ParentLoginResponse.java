package com.asms.usermgmt.response;

import java.util.List;

import com.asms.usermgmt.request.UserBasicDetails;
import com.asms.usermgmt.request.student.StudentDetails;

public class ParentLoginResponse extends LoginResponse {
	
	public List<UserBasicDetails> studentDetails;

	public List<UserBasicDetails> getStudentDetails() {
		return studentDetails;
	}

	public void setStudentDetails(List<UserBasicDetails> studentDetails) {
		this.studentDetails = studentDetails;
	}

	
	
	

}
