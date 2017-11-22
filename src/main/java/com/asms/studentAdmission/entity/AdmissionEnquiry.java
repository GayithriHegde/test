package com.asms.studentAdmission.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Class name : AdmissionEnquiry
 * This class is Hibernate mapping Entity to Admission_Enquiry table in DB
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "admission_enquiry")
public class AdmissionEnquiry {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	
	@Column(name = "Student_Name")
	private String studentName;

	@Column(name = "admission_Applied_Date")
	private String admissionAppliedDate;
	
	@Column(name = "Gender")
	private String gender;
	
	@Column(name = "Phone_Number")
	private long phoneNumber;
	
	@Column(name = "Class_Applied")
	private String classApplied;
	
	@Column(name = "Previous_School_Name")
	private String previousSchoolName;
	
	@Column(name = "Previous_School_Board")
	private String previousSchoolBoard;
	
	@Column(name = "DOB")
	private String dob;

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
	
}
