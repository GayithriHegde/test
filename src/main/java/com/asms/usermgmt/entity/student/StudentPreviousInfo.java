package com.asms.usermgmt.entity.student;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/* Class name : StudentPreviousInfo
 * 
 * StudentPreviousInfo class is the entity class for parent_details table in DB
 */

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "student_previous_information")
public class StudentPreviousInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="student_id")  
	private Student studentObject;
	
	

	@Column(name = "student_previous_school_name")
	private String schoolName;

	@Column(name = "student_previous_studied_from")
	private Date studiedFrom;
	
	@Column(name = "student_previous_studied_to")
	private Date studiedTo;
	
	@Column(name = "student_no_of_years_studied")
	private int noOfYears;
	
	@Column(name = "student_previous_class")
	private String previousClass;
	
	@Column(name = "student_previous_total_marks")
	private int totalMarks;
	
	@Column(name = "student_previous_obtained_marks")
	private int obtainedMarks;
	
	
	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	
	@JsonIgnore
	public Student getStudentObject() {
		return studentObject;
	}

	public void setStudentObject(Student studentObject) {
		this.studentObject = studentObject;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Date getStudiedFrom() {
		return studiedFrom;
	}

	public void setStudiedFrom(Date studiedFrom) {
		this.studiedFrom = studiedFrom;
	}

	public Date getStudiedTo() {
		return studiedTo;
	}

	public void setStudiedTo(Date studiedTo) {
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
