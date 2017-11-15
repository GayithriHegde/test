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

/* Class name : InstallmentAmount
 * 
 * InstallmentAmount class is the Mapping entity class for installment_amount table in DB
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "installment_amount")
public class InstallmentAmount {
	

	/**
	@{author} Devendra Singh
	09-11-2017
	*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	
	
	@Column(name = "amount")
	private int amount;
	
	
	@XmlElement
	@ManyToOne
	@JoinColumn(name = "tutionFee_id")
	private TutionFee tutionFeeObject;


	public int getSerialNo() {
		return serialNo;
	}


	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public TutionFee getTutionFeeObject() {
		return tutionFeeObject;
	}


	public void setTutionFeeObject(TutionFee tutionFeeObject) {
		this.tutionFeeObject = tutionFeeObject;
	}
	

}
