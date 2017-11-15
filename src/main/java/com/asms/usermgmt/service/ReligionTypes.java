package com.asms.usermgmt.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/*
 * Entity Class: religion
 * 
 * This class is the Hibernate mapping entity class for religion table in DB
 * 
 */

@Entity
@Table(name="religion")
public class ReligionTypes {
	/**
	@{author} karishma.k
	05-Sep-2017
	*/
	
	@Id
	@GenericGenerator(name="ref",strategy="increment")
	@GeneratedValue(generator="ref")
	@Column(name="serial_no")
	private int serialNo;
	
	@Column(name="religion_name")
	private String religionTypesName;

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getReligionTypesName() {
		return religionTypesName;
	}

	public void setReligionTypesName(String religionTypesName) {
		this.religionTypesName = religionTypesName;
	}


	
}

