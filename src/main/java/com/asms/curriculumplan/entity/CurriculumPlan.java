package com.asms.curriculumplan.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/* Class name : Student
 * 
 * CurriculumPlan class is the entity class for curriculum_plan table in DB
 */

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "curriculum_plan")
public class CurriculumPlan{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;


	@Column(name = "className")
	private String className;

	@Column(name = "academic_year")
	private String academicYear;

	
	
	@Column(name = "section_name")
	private String sectionName;
	
	@Column(name = "subject_name")
	private String subject;
	
	
	@Column(name = "created_on")
	private String createdOn;
	
	
	@Column(name = "created_by")
	private String createdBy;

	

	@XmlElement
	@OneToMany(mappedBy="curriculumObject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Unit> units = new HashSet<Unit>();



	public int getSerialNo() {
		return serialNo;
	}



	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}



	public String getClassName() {
		return className;
	}



	public void setClassName(String className) {
		this.className = className;
	}



	public String getAcademicYear() {
		return academicYear;
	}



	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}





	public String getSectionName() {
		return sectionName;
	}



	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}



	public String getSubject() {
		return subject;
	}



	public void setSubject(String subject) {
		this.subject = subject;
	}



	public String getCreatedOn() {
		return createdOn;
	}



	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}



	public String getCreatedBy() {
		return createdBy;
	}



	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	@JsonIgnore
	public Set<Unit> getUnits() {
		return units;
	}



	public void setUnits(Set<Unit> units) {
		this.units = units;
	}
	
	
	
  

	


}
