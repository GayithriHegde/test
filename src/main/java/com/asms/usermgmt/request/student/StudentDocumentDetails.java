package com.asms.usermgmt.request.student;

/* StudentDocumentDetails class represents the student create object
 * for student document management operations 
 * 
 */

public class StudentDocumentDetails {
	
	private String aadhaarNo;
	
	private String aadhaarCard;
	
	private String birthCirtificate;
	
	private String otherCertificate;
	
	private String remarks;

	public String getAadhaarNo() {
		return aadhaarNo;
	}

	public void setAadhaarNo(String aadhaarNo) {
		this.aadhaarNo = aadhaarNo;
	}

	public String getAadhaarCard() {
		return aadhaarCard;
	}

	public void setAadhaarCard(String aadhaarCard) {
		this.aadhaarCard = aadhaarCard;
	}

	public String getBirthCirtificate() {
		return birthCirtificate;
	}

	public void setBirthCirtificate(String birthCirtificate) {
		this.birthCirtificate = birthCirtificate;
	}

	public String getOtherCertificate() {
		return otherCertificate;
	}

	public void setOtherCertificate(String otherCertificate) {
		this.otherCertificate = otherCertificate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	

}
