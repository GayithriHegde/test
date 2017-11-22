package com.asms.studentAdmission.request;


public class ApplicationStatusDetails {
	
	private String studentName;

	private String admissionAppliedDate;
	
	private String gender;
	
	private long phoneNumber;
	
	private String classApplied;
	
	private String previousSchoolName;
	
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getAdmissionAppliedDate() {
		return admissionAppliedDate;
	}

	public void setAdmissionAppliedDate(String admissionAppliedDate) {
		this.admissionAppliedDate = admissionAppliedDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getClassApplied() {
		return classApplied;
	}

	public void setClassApplied(String classApplied) {
		this.classApplied = classApplied;
	}

	public String getPreviousSchoolName() {
		return previousSchoolName;
	}

	public void setPreviousSchoolName(String previousSchoolName) {
		this.previousSchoolName = previousSchoolName;
	}

	public String getPreviousSchoolBoard() {
		return previousSchoolBoard;
	}

	public void setPreviousSchoolBoard(String previousSchoolBoard) {
		this.previousSchoolBoard = previousSchoolBoard;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private String previousSchoolBoard;
	
	private String dob;
	
	private String status;
}
