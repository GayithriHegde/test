package com.asms.usermgmt.helper;

import com.asms.usermgmt.entity.User;

public class PrincipalUser {
	
	private boolean isPrivileged;
	private User loggedInUser;
	public boolean isPrivileged() {
		return isPrivileged;
	}
	public void setPrivileged(boolean isPrivileged) {
		this.isPrivileged = isPrivileged;
	}
	public User getLoggedInUser() {
		return loggedInUser;
	}
	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
	

}
