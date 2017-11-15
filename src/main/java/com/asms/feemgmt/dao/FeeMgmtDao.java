package com.asms.feemgmt.dao;

import java.util.List;

import com.asms.Exception.AsmsException;
import com.asms.feemgmt.entity.FeeCategory;
import com.asms.feemgmt.entity.FeeMaster;
import com.asms.feemgmt.entity.PaymentMode;
import com.asms.feemgmt.request.FeeCategoryDetails;
import com.asms.feemgmt.request.FeeMasterDetails;

public interface FeeMgmtDao {
	
	public List<FeeCategory> getFeeCategory(String tenantId) throws AsmsException; 	
	
	public void createDefaultFee(String schema) throws AsmsException;
	
	public void insertFee(FeeCategoryDetails categoryDetails ,String tenant) throws AsmsException;
	
	public void setupFee(FeeMasterDetails feeMasterDetails , String tenant) throws AsmsException;
	
	public List<FeeMaster> viewSetupFee(String tenant) throws AsmsException;
	
	public void createDefaultPaymentMode(String schema) throws AsmsException;
	
	
	public List<PaymentMode> getPaymentMode(String tenantId) throws AsmsException;
	
	

}
