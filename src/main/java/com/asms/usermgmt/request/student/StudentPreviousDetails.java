package com.asms.usermgmt.request.student;

/* StudentPreviousDetails class represents the student create object
 * for previous info management operations 
 * 
 */

public class StudentPreviousDetails {
	
	private String school;
	
	private String studiedFrom;
	
	private String studiedTo;
	
	private int noOfYears;
	
	private String previousClass;
	
	private int totalMarks;
	
	private int obtainedMarks;

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getStudiedFrom() {
		return studiedFrom;
	}

	public void setStudiedFrom(String studiedFrom) {
		this.studiedFrom = studiedFrom;
	}

	public String getStudiedTo() {
		return studiedTo;
	}

	public void setStudiedTo(String studiedTo) {
		this.studiedTo = studiedTo;
	}

	public int getNoOfYears() {
		return noOfYears;
	}

	public void setNoOfYears(int noOfYears) {
		this.noOfYears = noOfYears;
	}

	public String getPreviousClass() {
		return previousClass;
	}

	public void setPreviousClass(String previousClass) {
		this.previousClass = previousClass;
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	public int getObtainedMarks() {
		return obtainedMarks;
	}

	public void setObtainedMarks(int obtainedMarks) {
		this.obtainedMarks = obtainedMarks;
	}

	
	

}
