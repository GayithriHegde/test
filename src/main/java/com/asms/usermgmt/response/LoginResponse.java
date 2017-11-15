package com.asms.usermgmt.response;

import java.util.ArrayList;
import com.asms.common.response.SuccessResponse;
import com.asms.usermgmt.entity.Privilege;




/*
 * RegistrationResponse.java is to return user registration response
 */

public class LoginResponse extends SuccessResponse{
	
	


	public ArrayList<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(ArrayList<Privilege> privileges) {
		this.privileges = privileges;
	}

	public boolean isParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

	public boolean isFather() {
		return isFather;
	}

	public void setFather(boolean isFather) {
		this.isFather = isFather;
	}

	public boolean isMother() {
		return isMother;
	}

	public void setMother(boolean isMother) {
		this.isMother = isMother;
	}

	private String role;
	
	private ArrayList<Privilege> privileges;
	
	private boolean isParent;
	
	private boolean isFather;
	
	private boolean isMother;
	
	private String isNew;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String isNew() {
		return isNew;
	}

	public void setNew(String isNew) {
		this.isNew = isNew;
	}
	
	
	

}
