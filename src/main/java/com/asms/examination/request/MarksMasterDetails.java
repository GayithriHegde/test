package com.asms.examination.request;


/*
 * Class name: MarksMasterDetails
 * class is the mapping class for the UI 
 */
public class MarksMasterDetails {

	private String academicYear;
	
private String 	classId;

private String sectionId;

private String studentId;

public String getAcademicYear() {
	return academicYear;
}

public void setAcademicYear(String academicYear) {
	this.academicYear = academicYear;
}

public String getClassId() {
	return classId;
}

public void setClassId(String classId) {
	this.classId = classId;
}

public String getSectionId() {
	return sectionId;
}

public void setSectionId(String sectionId) {
	this.sectionId = sectionId;
}

public String getStudentId() {
	return studentId;
}

public void setStudentId(String studentId) {
	this.studentId = studentId;
}
}
