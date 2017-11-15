package com.asms.reportsGeneration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Class name : CurriculamReport
 * This class is Hibernate mapping Entity to management table in DB
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "Curriculam_Report")
public class CurriculamReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	
	@Column(name = "academic_Year")
	private String academicYear;
	
	@Column(name = "class_Name")
	private String className;
	
	public int getSerialNo() {
		return serialNo;
	}



	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
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



	public String getSubject() {
		return subject;
	}



	public void setSubject(String subject) {
		this.subject = subject;
	}



	@Column(name = "subject")
	private String subject;
	
}
