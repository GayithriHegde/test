package com.asms.usermgmt.response;

import java.util.List;

import com.asms.messagemgmt.entity.Message;

/*
 * RegistrationResponse.java is to return user registration response
 */

public class AdminLoginResponse extends LoginResponse {

	private int noOfStudents;

	private int noOfSubjects;

	private List<Message> UnreadMessages;
	
	private List<Message> topTenMessages;

	public List<Message> getTopTenMessages() {
		return topTenMessages;
	}

	public void setTopTenMessages(List<Message> topTenMessages) {
		this.topTenMessages = topTenMessages;
	}

	public long getNoOfStudents() {
		return noOfStudents;
	}

	public void setNoOfStudents(int noOfStudents) {
		this.noOfStudents = noOfStudents;
	}

	public int getNoOfSubjects() {
		return noOfSubjects;
	}

	public void setNoOfSubjects(int noOfSubjects) {
		this.noOfSubjects = noOfSubjects;
	}

	public List<Message> getUnreadMessages() {
		return UnreadMessages;
	}

	public void setUnreadMessages(List<Message> unreadMessages) {
		UnreadMessages = unreadMessages;
	}
	
	
	
	

}
