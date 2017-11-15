package com.asms.feemgmt.response;

import java.util.List;

import com.asms.common.response.SuccessResponse;
import com.asms.feemgmt.entity.FeeCategory;
import com.asms.feemgmt.entity.FeeMaster;
import com.asms.feemgmt.entity.PaymentMode;
import com.asms.feemgmt.request.FeeCategoryDetails;

public class FeeSuccessResponse extends SuccessResponse
{
	
	private FeeCategoryDetails feeCategoryDetails;
	
	private  List<FeeCategory> feeCategories;
	
	private List<FeeMaster> feeMasters;

	private List<PaymentMode> paymentModes;
	
	public FeeCategoryDetails getFeeCategoryDetails() {
		return feeCategoryDetails;
	}

	public void setFeeCategoryDetails(FeeCategoryDetails feeCategoryDetails) {
		this.feeCategoryDetails = feeCategoryDetails;
	}

	public List<FeeCategory> getFeeCategories() {
		return feeCategories;
	}

	public void setFeeCategories(List<FeeCategory> feeCategories) {
		this.feeCategories = feeCategories;
	}

	public List<FeeMaster> getFeeMasters() {
		return feeMasters;
	}

	public void setFeeMasters(List<FeeMaster> feeMasters) {
		this.feeMasters = feeMasters;
	}

	public List<PaymentMode> getPaymentModes() {
		return paymentModes;
	}

	public void setPaymentModes(List<PaymentMode> paymentModes) {
		this.paymentModes = paymentModes;
	}

}
