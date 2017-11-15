package com.asms.usermgmt.request;

public class ChangePasswordDetails {
	
	/**
	@{author} karishma.k
	09-sep-2017
	*/
	
	/*
	 * Class name: ChangePasswordDetails
	 * class is the mapping class for the UI 
	 */
	private int userId;
	
	private String currentpassword;
	
	private String newpassword;
	
	private String confirmpassword;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCurrentpassword() {
		return currentpassword;
	}

	public void setCurrentpassword(String currentpassword) {
		this.currentpassword = currentpassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
}
