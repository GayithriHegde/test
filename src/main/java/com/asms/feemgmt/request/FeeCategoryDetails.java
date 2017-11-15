package com.asms.feemgmt.request;

import java.util.List;

import com.asms.feemgmt.entity.PaymentType;


/*
 * Class name: FeeCategoryDetails
 * class is the mapping class for the UI 
 */
public class FeeCategoryDetails {
	
	
	private String category;
	
	
	private boolean display;
	
	
	private List<PaymentType> paymentTypes;
	
	private List<PaymentTypeDetails> paymentTypeDetails;
	
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isDisplay() {
		return display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}

	public List<PaymentType> getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(List<PaymentType> paymentTypes) {
		this.paymentTypes = paymentTypes;
	}

	public List<PaymentTypeDetails> getPaymentTypeDetails() {
		return paymentTypeDetails;
	}

	public void setPaymentTypeDetails(List<PaymentTypeDetails> paymentTypeDetails) {
		this.paymentTypeDetails = paymentTypeDetails;
	}
	

}
