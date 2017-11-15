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

/* Class name : PaymentMode
 * 
 * PaymentMode class is the Mapping entity class for payment_mode table in DB
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "payment_mode")
public class PaymentMode {
	

	/**
	@{author} Devendra Singh
	07-11-2017
	*/

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	
	@Column(name = "payment_mode")
	private String paymentMode;
	
	
	@XmlElement
	@ManyToOne
	@JoinColumn(name = "tutionFee_id")
	private TutionFee tutionFeeObject;
	
	@XmlElement
	@ManyToOne
	@JoinColumn(name = "otherFee_id")
	private OtherFee otherFeeObject;
	
	

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public TutionFee getTutionFeeObject() {
		return tutionFeeObject;
	}

	public void setTutionFeeObject(TutionFee tutionFeeObject) {
		this.tutionFeeObject = tutionFeeObject;
	}

	public OtherFee getOtherFeeObject() {
		return otherFeeObject;
	}

	public void setOtherFeeObject(OtherFee otherFeeObject) {
		this.otherFeeObject = otherFeeObject;
	}
	
	
	
}
