/**
mallikarjun.guranna
Oct 6, 2017
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
 * Entity class for Staff_Attendance table.
 * Hibernate mapping class for Staff_Attendance table
 * 
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "Staff_Attendance")
public class StaffAttendance {
	
	
	@Id
	@GeneratedValue
	@Column(name = "serialNo")
	private int serialNo;
	
	@Column(name = "staffId")
	private String staffId;
	
	@Column(name = "academic_year")
	private String academicYear;
	
	@Column(name = "date")
	private Date date;

	
	@Column(name = "name")
	private String name; 
	
	@Column(name = "status")
	private String status;

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

/*	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
*/
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	
	
	
	
}
