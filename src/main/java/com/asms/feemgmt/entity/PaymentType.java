package com.asms.feemgmt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/* Class name : PaymentType
 * 
 * PaymentType class is the Mapping entity class for payment_type table in DB
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "payment_type")
public class PaymentType {
	
	/**
	@{author} Devendra Singh
	31-oct-2017
	*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	
	
	@Column(name="type")
	private String type;
	
	
	@Column(name="display")
	private boolean display;

	
	
	@XmlElement
	@ManyToOne
	@JoinColumn(name = "fee_id")
	private FeeCategory feeCategoryObject;
	
	
	

	public int getSerialNo() {
		return serialNo;
	}


	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public boolean isDisplay() {
		return display;
	}


	public void setDisplay(boolean display) {
		this.display = display;
	}


	public FeeCategory getFeeCategoryObject() {
		return feeCategoryObject;
	}


	public void setFeeCategoryObject(FeeCategory feeCategoryObject) {
		this.feeCategoryObject = feeCategoryObject;
	}
	

}
