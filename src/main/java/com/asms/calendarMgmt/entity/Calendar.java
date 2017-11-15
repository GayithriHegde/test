/**
	 * @{author} karishma.k 06-10-2017
	 */
package com.asms.calendarMgmt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/* Calendar class
 * and maps calendar table
 */

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
@Entity
@Table(name = "calendar")
//@Inheritance(strategy=InheritanceType.JOINED)

public class Calendar {
	
	/**
	 * @{author} karishma.k 06-10-2017
	 */

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	
	@Column(name = "from_date")
	private String fromDate;
	
	public String getFromDate() {
		return fromDate;
	}


	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	


	


	public String getToDate() {
		return toDate;
	}


	public void setToDate(String toDate) {
		this.toDate = toDate;
	}


	public String getFromTime() {
		return fromTime;
	}


	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}


	public String getToTime() {
		return toTime;
	}


	public void setToTime(String toTime) {
		this.toTime = toTime;
	}


	public String getAcademicYear() {
		return academicYear;
	}


	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}






	@Column(name = "name")
	private String name;
	 
	//"recurrencePattern" : "string"
	
	public String getRecurrencePattern() {
		return recurrencePattern;
	}


	public void setRecurrencePattern(String recurrencePattern) {
		this.recurrencePattern = recurrencePattern;
	}






	@Column(name = "recurrence_pattern")
	private String recurrencePattern;

	
	@Column(name = "to_date")
	private String toDate;
	
	@Column(name = "from_time")
	private String fromTime;

	@Column(name = "to_time")
	private String toTime;
	
	@Column(name = "createdby")
	private int createdby;
	
	
	
	
	


	
	


	public int getCreatedby() {
		return createdby;
	}


	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}






	@Column(name = "academic_year")
	private String academicYear;
	 
	
	
	

	public int getSerialNo() {
		return serialNo;
	}




	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}



	
	


	

}
