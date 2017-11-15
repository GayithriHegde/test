package com.asms.rolemgmt.request;

import java.util.ArrayList;
import java.util.List;

import com.asms.usermgmt.entity.User;

/*
 * RoleDetails is the mapping class
 * to get RoleDetails from front end
 * 
 */

public class RoleDetails {
	
	private int roleId;
	
	private String roleName;
	
	private List<User> userObject = new ArrayList<User>();

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<User> getUserObject() {
		return userObject;
	}

	public void setUserObject(List<User> userObject) {
		this.userObject = userObject;
	}

}
