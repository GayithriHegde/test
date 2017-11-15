package com.asms.usermgmt.request;

/* User Request class represents the request object
 * for user management operations 
 * 
 */

public class UserRequest {

	// request type can be create/update/delete
	private String requestType;

	// user role can be student/management/ teaching staff/ non teaching staff
	private String userRole;

	// subrole
	private String subRole;

	// object is for user object
	//user may be Student,Management,Admin,Teacher,Advisor,accountant,etc
	private UserDetails userDetails;

	// detail type can be 
	//Constants - > detail_address/documents/previous_info/statutory_details
	private String detailType;

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

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public String getDetailType() {
		return detailType;
	}

	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}

}
