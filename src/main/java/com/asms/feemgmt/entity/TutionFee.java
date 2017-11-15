package com.asms.feemgmt.entity;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/* Class name : TutionFee
 * 
 * TutionFee class is the Mapping entity class for tution_fee table in DB
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "tution_fee")
public class TutionFee {
	

	/**
	@{author} Devendra Singh
	09-11-2017
	*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "is_installments")
	private boolean  isInstallments;
	
	
	@Column(name = "no_of_installments")
	private int noOfInstallments;
	
	@Column(name = "due_date")
	private int dueDate;
	
	@Column(name = "is_amount_in_percentage")
	private boolean  isAmountinPercentage;
	
	
	@Column(name = "is_amount_in_value")
	private boolean  isAmountinValue;
	
	/*@Column(name = "installment_amount")
	private double installmentAmount;*/
	
	
	@Column(name = "is_penalty_in_percentage")
	private boolean isPenaltyInPercentage;
	
	@Column(name = "is_penalty_in_value")
	private boolean isPenaltyInValue;
	
	@Column(name = "payment_value")
	private double penaltyValue;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tutionFeeObject",fetch = FetchType.EAGER)
	private Set<InstallmentAmount> installmentAmounts = new HashSet<InstallmentAmount>();
	
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="fee_structure_id")  
	private FeeStructure feeStructureObject;
	
	
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tutionFeeObject",fetch = FetchType.EAGER)
	private Set<PaymentMode> paymentModes  = new HashSet<PaymentMode>();
	

	public int getSerialNo() {
		return serialNo;
	}


	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public boolean isInstallments() {
		return isInstallments;
	}


	public void setInstallments(boolean isInstallments) {
		this.isInstallments = isInstallments;
	}


	public int getNoOfInstallments() {
		return noOfInstallments;
	}


	public void setNoOfInstallments(int noOfInstallments) {
		this.noOfInstallments = noOfInstallments;
	}


	public int getDueDate() {
		return dueDate;
	}


	public void setDueDate(int dueDate) {
		this.dueDate = dueDate;
	}


	public boolean isAmountinPercentage() {
		return isAmountinPercentage;
	}


	public void setAmountinPercentage(boolean isAmountinPercentage) {
		this.isAmountinPercentage = isAmountinPercentage;
	}


	public boolean isAmountinValue() {
		return isAmountinValue;
	}


	public void setAmountinValue(boolean isAmountinValue) {
		this.isAmountinValue = isAmountinValue;
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
	public Set<InstallmentAmount> getInstallmentAmounts() {
		return installmentAmounts;
	}


	public void setInstallmentAmounts(Set<InstallmentAmount> installmentAmounts) {
		this.installmentAmounts = installmentAmounts;
	}


	@JsonIgnore
	public Set<PaymentMode> getPaymentModes() {
		return paymentModes;
	}


	public void setPaymentModes(Set<PaymentMode> paymentModes) {
		this.paymentModes = paymentModes;
	}


	


	
	
	

}
