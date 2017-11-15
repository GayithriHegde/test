/**
@{author} mallikarjun.guranna
01-Sep-2017
*/
package com.asms.schoolmgmt.request;
import com.asms.rolemgmt.entity.Role;
import com.asms.rolemgmt.entity.SubRole;
import com.asms.schoolmgmt.entity.SetupSchoolDetails;

public class UserRequest {
	/**
	@{author} mallikarjun.guranna
	01-Sep-2017
	*/
	
	
	private String requestType;
	// role
	private String userRole;
	// subrole
	private String subRole;

	private SchoolDetails schoolDetails;
	
	private SetupSchoolDetails setupSchoolDetails;
	
	
    private String admissionForYear;
	
	
private Role role;

private SubRole subRole2;

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getSubRole() {
		return subRole;
	}

	public void setSubRole(String subRole) {
		this.subRole = subRole;
	}

	public SchoolDetails getSchoolDetails() {
		return schoolDetails;
	}

	public void setSchoolDetails(SchoolDetails schoolDetails) {
		this.schoolDetails = schoolDetails;
	}

	public SetupSchoolDetails getSetupSchoolDetails() {
		return setupSchoolDetails;
	}

	public void setSetupSchoolDetails(SetupSchoolDetails setupSchoolDetails) {
		this.setupSchoolDetails = setupSchoolDetails;
	}



	

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public SubRole getSubRole2() {
		return subRole2;
	}

	public void setSubRole2(SubRole subRole2) {
		this.subRole2 = subRole2;
	}

	public String getAdmissionForYear() {
		return admissionForYear;
	}

	public void setAdmissionForYear(String admissionForYear) {
		this.admissionForYear = admissionForYear;
	}

	
	
	
	
}
