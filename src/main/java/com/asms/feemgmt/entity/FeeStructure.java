package com.asms.feemgmt.entity;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/* Class name : FeeStructure
 * 
 * FeeStructure class is the Mapping entity class for fee_structure table in DB
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name="fee_structure")
public class FeeStructure {
	

	/**
	@{author} Devendra Singh
	09-11-2017
	*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	
	
	@Column(name = "class_id")
	private int classId;
	

	@Column(name = "academic_year_id")
	private int academicYear;
	
	
	@XmlElement
	@OneToOne(mappedBy="feeStructureObject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private TutionFee tutionFee;
	
	
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "feeStructureObject",fetch = FetchType.EAGER)
	private Set<OtherFee> otherFees = new HashSet<OtherFee>();

	

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public int getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(int academicYear) {
		this.academicYear = academicYear;
	}

	public TutionFee getTutionFee() {
		return tutionFee;
	}

	public void setTutionFee(TutionFee tutionFee) {
		this.tutionFee = tutionFee;
	}

	@JsonIgnore
	public Set<OtherFee> getOtherFees() {
		return otherFees;
	}

	public void setOtherFees(Set<OtherFee> otherFees) {
		this.otherFees = otherFees;
	}
	


	

}
