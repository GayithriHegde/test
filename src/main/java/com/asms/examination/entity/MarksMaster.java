package com.asms.examination.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/* Class name : MarksMaster
 * 
 * MarksMaster class is the Mapping entity class for marks table in DB
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "marks_master")
public class MarksMaster {
	/**
	@{author} Devendra Singh
	23-oct-2017
	*/
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	


	@Column(name = "academic_year")
	private String academicYear;
	
	
	@Column(name = "class_id")
	private String classId;
	
	
	@Column(name = "section_id")
	private String sectionId;
	
	
	@Column(name = "student_id")
	private String studentId;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "marksMasterObject",fetch = FetchType.EAGER)
	private List<Marks> marks = new ArrayList<Marks>();



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

	@JsonIgnore
	public List<Marks> getMarks() {
		return marks;
	}


	public void setMarks(List<Marks> marks) {
		this.marks = marks;
	}
	
	

}
