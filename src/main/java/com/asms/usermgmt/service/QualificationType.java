package com.asms.usermgmt.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/*
 * Entity Class: qualification
 * 
 * This class is the Hibernate mapping entity class for qualification table in DB
 * 
 */

@Entity
@Table(name="qualification")
public class QualificationType {
	/**
	@{author} karishma.k
	05-Sep-2017
	*/

	@Id
	@GenericGenerator(name="ref",strategy="increment")
	@GeneratedValue(generator="ref")
	@Column(name="serial_no")
	private int serialNo;
	
	@Column(name="qualification_name")
	private String qualificationTypeName;

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getQualificationTypeName() {
		return qualificationTypeName;
	}

	public void setQualificationTypeName(String qualificationTypeName) {
		this.qualificationTypeName = qualificationTypeName;
	}

	
}
