/**
mallikarjun.guranna
Oct 6, 2017
*/
package com.asms.attendance.request;

import java.util.Date;


public class StudentAttendanceDetails {
	
	
	
	
	
	private String stdId;
		
	
	private String academicYear;
	
	private Date date;
	
	private String name;
	
	private String className;
	
	private String sectionName;

	
	private String morningSession;

	
	
	private String afternoonSession;
	/*private String status;*/

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	/*public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}*/

	public String getMorningSession() {
		return morningSession;
	}

	public void setMorningSession(String morningSession) {
		this.morningSession = morningSession;
	}

	public String getAfternoonSession() {
		return afternoonSession;
	}

	public void setAfternoonSession(String afternoonSession) {
		this.afternoonSession = afternoonSession;
	}

	public String getStdId() {
		return stdId;
	}

	public void setStdId(String stdId) {
		this.stdId = stdId;
	}

}
