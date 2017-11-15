package com.asms.feemgmt.request;

import com.asms.feemgmt.request.InstallmentDetails;

public class TutionFeeDetails {
	
	private int amount;
	
	private boolean isInstallments;
	
	private InstallmentDetails installmentDetails;
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public boolean isInstallments() {
		return isInstallments;
	}
	public void setInstallments(boolean isInstallments) {
		this.isInstallments = isInstallments;
	}
	public InstallmentDetails getInstallmentDetails() {
		return installmentDetails;
	}
	public void setInstallmentDetails(InstallmentDetails installmentDetails) {
		this.installmentDetails = installmentDetails;
	}
	

}
