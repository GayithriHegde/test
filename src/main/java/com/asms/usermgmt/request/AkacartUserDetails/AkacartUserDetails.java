package com.asms.usermgmt.request.AkacartUserDetails;

public class AkacartUserDetails {
	
	/**
	 * @{author} Praveen.Tiwari 10-Nov-2017
	 */
	private String userId;
	
	private boolean akakartAccess = false;
	
	private String createdOn;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public boolean isAkakartAccess() {
		return akakartAccess;
	}
	public void setAkakartAccess(boolean akakartAccess) {
		this.akakartAccess = akakartAccess;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
}
