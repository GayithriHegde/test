package com.asms.feemgmt.request;

import java.util.List;

/*
 * Class name: FeeMasterDetails
 * class is the mapping class for the UI 
 */
public class FeeMasterDetails {
	
	private int feeCategory;
	
	private List<Integer> paymentTypes;
	
	private int noOfInstallments;
	
	private int noOfMonths;

	

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

	public int getFeeCategory() {
		return feeCategory;
	}

	public void setFeeCategory(int feeCategory) {
		this.feeCategory = feeCategory;
	}

	public List<Integer> getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(List<Integer> paymentTypes) {
		this.paymentTypes = paymentTypes;
	}


	
	
	

}
