package com.asms.studentAdmission.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Class name : AdmissionReport
 * This class is Hibernate mapping Entity to Admission_Report table in DB
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "admission")
public class Admission {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	
	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

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

	@Column(name = "Student_Name")
	private String studentName;
	
	@Column(name = "Student_Type")
	private String studentType;
	
	@Column(name = "adminssion_ID")
	private String adminssionID;
	
	@Column(name = "adminssion_Year")
	private String adminssionYear;
	
	@Column(name = "adminssion_Date")
	private Date adminssionDate;
	
	@Column(name = "Gender")
	private String gender;
	
	@Column(name = "Class_Name")
	private String className;
	
	@Column(name = "phn_Number")
	private long phnNumber;
	
	@Column(name = "DOB")
	private Date dob;
	
	@Column(name = "birth_Plcae")
	private String birthPlcae;
	
	@Column(name = "Caste")
	private String caste;
		
}
