/**
mallikarjun.guranna
Oct 5, 2017
*/
package com.asms.attendance.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




/*
 * Entity class for Student_Attendance table.
 * Hibernate mapping class for Student_Attendance table
 * 
 */

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "Student_Attendance")
public class StudentAttendance {
	

	@Id
	@GeneratedValue
	@Column(name = "serialNo")
	private int serialNo;
	
	@Column(name = "studentId")
	private String stdId;
		
	@Column(name = "academic_year")
	private String academicYear;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "class_name")
	private String className;
	
	@Column(name = "section_name")
	private String sectionName;
	
	@Column(name = "morning_session")
	private String morningSession;

	
	@Column(name = "afternoon_session")
	private String afternoonSession;
	/*@Column(name = "status")
	private String status;*/

	

	/*public String getStdId() {
		return stdId;
	}

	public void setStdId(String stdId) {
		this.stdId = stdId;
	}*/

	/*public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}*/

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	

	
	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
