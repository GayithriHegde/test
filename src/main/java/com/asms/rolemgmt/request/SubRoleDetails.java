package com.asms.rolemgmt.request;

import java.util.ArrayList;
import java.util.List;

import com.asms.usermgmt.entity.User;

public class SubRoleDetails {
	
	private int subRoleId;
	
	private String subRoleName;
	
	
	
	private List<User> userObject = new ArrayList<User>();

	public int getSubRoleId() {
		return subRoleId;
	}

	public void setSubRoleId(int subRoleId) {
		this.subRoleId = subRoleId;
	}

	public String getSubRoleName() {
		return subRoleName;
	}

	public void setSubRoleName(String subRoleName) {
		this.subRoleName = subRoleName;
	}

	
	public List<User> getUserObject() {
		return userObject;
	}

	public void setUserObject(List<User> userObject) {
		this.userObject = userObject;
	}
	
}
