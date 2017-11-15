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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/* Class name : OtherFee
 * 
 * OtherFee class is the Mapping entity class for other_fee table in DB
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name ="other_fee")
public class OtherFee {
	/**
	@{author} Devendra Singh
	09-11-2017
	*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	
	@Column(name = "category_id")
	private int categoryId;
	
	
	@Column(name = "payment_type_id")
	private int paymentTypeId;
	
	@Column(name="no_of_installments")
	private int noOfInstallments;
	
	
	@Column(name="no_of_months")
	private int noOfMonths;
	
	@Column(name="total_amount")
	private double totalAmount;
	
	
	@Column(name="due_date")
	private int dueDate;
	
	@Column(name = "is_penalty_in_percentage")
	private boolean isPenaltyInPercentage;
	
	@Column(name = "is_penalty_in_value")
	private boolean isPenaltyInValue;
	
	@Column(name = "payment_value")
	private double penaltyValue;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "otherFeeObject",fetch = FetchType.EAGER)
	private Set<PaymentMode> paymentModes  = new HashSet<PaymentMode>();
	

	@XmlElement
	@ManyToOne
	@JoinColumn(name = "feeStructure_id")
	private FeeStructure feeStructureObject;
	
	
	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(int paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public int getNoOfInstallments() {
		return noOfInstallments;
	}

	public void setNoOfInstallments(int noOfInstallments) {
		this.noOfInstallments = noOfInstallments;
	}

	public int getNoOfMonths() {
		return noOfMonths;
	}

	public void setNoOfMonths(int noOfMonths) {
		this.noOfMonths = noOfMonths;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getDueDate() {
		return dueDate;
	}

	public void setDueDate(int dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isPenaltyInPercentage() {
		return isPenaltyInPercentage;
	}

	public void setPenaltyInPercentage(boolean isPenaltyInPercentage) {
		this.isPenaltyInPercentage = isPenaltyInPercentage;
	}

	public boolean isPenaltyInValue() {
		return isPenaltyInValue;
	}

	public void setPenaltyInValue(boolean isPenaltyInValue) {
		this.isPenaltyInValue = isPenaltyInValue;
	}

	public double getPenaltyValue() {
		return penaltyValue;
	}

	public void setPenaltyValue(double penaltyValue) {
		this.penaltyValue = penaltyValue;
	}



	public FeeStructure getFeeStructureObject() {
		return feeStructureObject;
	}

	public void setFeeStructureObject(FeeStructure feeStructureObject) {
		this.feeStructureObject = feeStructureObject;
	}

	@JsonIgnore
	public Set<PaymentMode> getPaymentModes() {
		return paymentModes;
	}

	public void setPaymentModes(Set<PaymentMode> paymentModes) {
		this.paymentModes = paymentModes;
	}
	
	
	
	
}
