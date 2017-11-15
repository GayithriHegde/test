/**
@{author} mallikarjun.guranna
01-Sep-2017
*/
package com.asms.schoolmgmt.helper;

import com.asms.usermgmt.entity.User;

public class PrincipleUser {
	/**
	@{author} mallikarjun.guranna
	01-Sep-2017
	*/
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
