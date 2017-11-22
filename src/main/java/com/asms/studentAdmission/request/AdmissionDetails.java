package com.asms.studentAdmission.request;

import java.util.Date;


public class AdmissionDetails {
	private String studentName;
	
	private String studentType;
	
	private String adminssionID;
	
	private String adminssionYear;
	
	private Date adminssionDate;
	
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentType() {
		return studentType;
	}

	public void setStudentType(String studentType) {
		this.studentType = studentType;
	}

	public String getAdminssionID() {
		return adminssionID;
	}

	public void setAdminssionID(String adminssionID) {
		this.adminssionID = adminssionID;
	}

	public String getAdminssionYear() {
		return adminssionYear;
	}

	public void setAdminssionYear(String adminssionYear) {
		this.adminssionYear = adminssionYear;
	}

	public Date getAdminssionDate() {
		return adminssionDate;
	}

	public void setAdminssionDate(Date adminssionDate) {
		this.adminssionDate = adminssionDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public long getPhnNumber() {
		return phnNumber;
	}

	public void setPhnNumber(long phnNumber) {
		this.phnNumber = phnNumber;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getBirthPlcae() {
		return birthPlcae;
	}

	public void setBirthPlcae(String birthPlcae) {
		this.birthPlcae = birthPlcae;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	private String gender;
	
	private String className;
	
	private long phnNumber;
	
	private Date dob;
	
	private String birthPlcae;
	
	private String caste;
}
