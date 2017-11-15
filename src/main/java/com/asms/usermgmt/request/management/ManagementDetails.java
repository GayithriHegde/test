package com.asms.usermgmt.request.management;

import java.util.Date;

/*
 * Class name: ManagementDetails
 * class is the mapping class for the UI 
 */
public class ManagementDetails {
	
	private int serId;
	
	private String schoolId;
	
	private String trustId;
	
	//private String trustName;
	
	private String mngmtFirstName;
	
	private String mngmtMiddleName;
	
	private String mngmtLastName;
	
	private String mngmtDesignation;
	
	//private String mngmtLoginId;
	
	private String mngmtContactNo;
	
	private String mngmtCreatedByWadmin;
	
	private Date mngmtCreationTime;
	
	private String acStatus;
	
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getTrustId() {
		return trustId;
	}
	public void setTrustId(String trustId) {
		this.trustId = trustId;
	}
	/*public String getTrustName() {
		return trustName;
	}
	public void setTrustName(String trustName) {
		this.trustName = trustName;
	}*/
	
	public String getMngmtFirstName() {
		return mngmtFirstName;
	}
	public void setMngmtFirstName(String mngmtFirstName) {
		this.mngmtFirstName = mngmtFirstName;
	}
	public String getMngmtMiddleName() {
		return mngmtMiddleName;
	}
	public void setMngmtMiddleName(String mngmtMiddleName) {
		this.mngmtMiddleName = mngmtMiddleName;
	}
	public String getMngmtLastName() {
		return mngmtLastName;
	}
	public void setMngmtLastName(String mngmtLastName) {
		this.mngmtLastName = mngmtLastName;
	}
	public String getMngmtDesignation() {
		return mngmtDesignation;
	}
	public void setMngmtDesignation(String mngmtDesignation) {
		this.mngmtDesignation = mngmtDesignation;
	}
	/*public String getMngmtLoginId() {
		return mngmtLoginId;
	}
	public void setMngmtLoginId(String mngmtLoginId) {
		this.mngmtLoginId = mngmtLoginId;
	}*/
	public String getMngmtContactNo() {
		return mngmtContactNo;
	}
	public void setMngmtContactNo(String mngmtContactNo) {
		this.mngmtContactNo = mngmtContactNo;
	}
	
	public String getMngmtCreatedByWadmin() {
		return mngmtCreatedByWadmin;
	}
	public void setMngmtCreatedByWadmin(String mngmtCreatedByWadmin) {
		this.mngmtCreatedByWadmin = mngmtCreatedByWadmin;
	}
	public Date getMngmtCreationTime() {
		return mngmtCreationTime;
	}
	public void setMngmtCreationTime(Date mngmtCreationTime) {
		this.mngmtCreationTime = mngmtCreationTime;
	}
	public String getAcStatus() {
		return acStatus;
	}
	public void setAcStatus(String acStatus) {
		this.acStatus = acStatus;
	}
	public int getSerId() {
		return serId;
	}
	public void setSerId(int serId) {
		this.serId = serId;
	}
	
	
}
