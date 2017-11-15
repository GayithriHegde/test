package com.asms.feemgmt.request;

import java.util.List;

public class InstallmentDetails {
	
	
	private int noOfInstallments;
	
	private List<Integer> installmentAmountPerc;
	
	private  List<Integer> installmentAmountValue;
	
	private  List<String> dueDate;

	public int getNoOfInstallments() {
		return noOfInstallments;
	}

	public void setNoOfInstallments(int noOfInstallments) {
		this.noOfInstallments = noOfInstallments;
	}

	public List<Integer> getInstallmentAmountPerc() {
		return installmentAmountPerc;
	}

	public void setInstallmentAmountPerc(List<Integer> installmentAmountPerc) {
		this.installmentAmountPerc = installmentAmountPerc;
	}

	public List<Integer> getInstallmentAmountValue() {
		return installmentAmountValue;
	}

	public void setInstallmentAmountValue(List<Integer> installmentAmountValue) {
		this.installmentAmountValue = installmentAmountValue;
	}

	public List<String> getDueDate() {
		return dueDate;
	}

	public void setDueDate(List<String> dueDate) {
		this.dueDate = dueDate;
	}

	

}
