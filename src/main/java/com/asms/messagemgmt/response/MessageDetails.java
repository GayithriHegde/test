package com.asms.messagemgmt.response;

import java.util.ArrayList;
import java.util.List;

public class MessageDetails {
	
	private String type;     //inbox or sent
	
	private String message;
	
	private String toUserType;
	
	private String className;
	
	private String sectionName;
	
	private List<String> toUsers = new ArrayList<String>();
	
	public String getToUserType() {
		return toUserType;
	}

	public void setToUserType(String toUserType) {
		this.toUserType = toUserType;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public List<String> getToUsers() {
		return toUsers;
	}

	public void setToUsers(List<String> toUsers) {
		this.toUsers = toUsers;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	
	

}
