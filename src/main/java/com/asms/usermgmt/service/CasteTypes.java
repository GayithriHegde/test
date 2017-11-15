package com.asms.usermgmt.service;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/*
 * Entity Class: caste
 * 
 * This class is the Hibernate mapping entity class for caste table in DB
 * 
 */

@Entity
@Table(name="caste")
public class CasteTypes {
	/**
	@{author} karishma.k
	05-Sep-2017
	*/
	
	@Id
	@GenericGenerator(name="ref",strategy="increment")
	@GeneratedValue(generator="ref")
	@Column(name="serial_no")
	private int serialNo;
	
	@Column(name="caste_name")
	private String casteTypesName;
	
	@Column(name="sub_caste_name")
	private String subcasteTypesName;

	

	public String getSubcasteTypesName() {
		return subcasteTypesName;
	}

	public void setSubcasteTypesName(String subcasteTypesName) {
		this.subcasteTypesName = subcasteTypesName;
	}

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getCasteTypesName() {
		return casteTypesName;
	}

	public void setCasteTypesName(String casteTypesName) {
		this.casteTypesName = casteTypesName;
	}

	

	
}
