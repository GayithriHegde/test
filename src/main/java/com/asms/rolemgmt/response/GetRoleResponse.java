package com.asms.rolemgmt.response;

import java.util.List;

import com.asms.common.response.SuccessResponse;
import com.asms.rolemgmt.entity.Role;
import com.asms.rolemgmt.entity.SubRole;

/*
 * RegistrationResponse.java is to return user registration response
 */

public class GetRoleResponse extends SuccessResponse {
	
	private List<Role> roles;
	
	private List<SubRole> subRoles;

	public List<SubRole> getSubRoles() {
		return subRoles;
	}

	public void setSubRoles(List<SubRole> subRoles) {
		this.subRoles = subRoles;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	


	

	

}
