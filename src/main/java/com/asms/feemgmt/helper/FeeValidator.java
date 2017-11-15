package com.asms.feemgmt.helper;

import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asms.Exception.AsmsException;
import com.asms.Exception.ExceptionHandler;
import com.asms.common.helper.Constants;
import com.asms.feemgmt.request.FeeCategoryDetails;
import com.asms.feemgmt.request.FeeMasterDetails;
import com.asms.feemgmt.request.PaymentTypeDetails;
import com.asms.feemgmt.request.UserRequest;

/*
 * FeeValidator.java does the validation of requests
 * 
 */
@Component
public class FeeValidator {
	
	
	@Autowired
	private ExceptionHandler exceptionHandler;
	
	
	
	
	/*
	 * Method: validateFeeCategoryDetails -> validates userRequest for null. Parameters ->
	 * UserRequest throws -> AsmsException
	 */

	public void validateFeeCategoryDetails(UserRequest request, ResourceBundle messages, String type)
			throws AsmsException {

		if (null == request) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_NULL_CODE"),
					messages.getString("REQUEST_NULL"));
		}

		if (null == request.getRequestType() || request.getRequestType().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_TYPE_NULL_CODE"),
					messages.getString("REQUEST_TYPE_NULL"));
		}

		FeeCategoryDetails details = request.getCategoryDetails();

		if (null == details) {
			throw exceptionHandler.constructAsmsException(messages.getString("FEE_CATEGORY_DETAILS_NULL_CODE"),
					messages.getString("FEE_CATEGORY_DETAILS_NULL_MSG"));
		}

		if (null == details.getCategory() || details.getCategory().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("FEE_CATEGORY_NAME_NULL_CODE"),
					messages.getString("FEE_CATEGORY_NAME_NULL_MSG"));
		}
		
		if (false == details.isDisplay()) {
			throw exceptionHandler.constructAsmsException(messages.getString("FEE_CATEGORY_DISPLAY_NULL_CODE"),
					messages.getString("FEE_CATEGORY_DISPLAY_NULL_MSG"));
		}
		
	}
	
	
	/*
	 * Method: validatePaymentTypeDetails -> validates userRequest for null. Parameters ->
	 * UserRequest throws -> AsmsException
	 */

	public void validatePaymentTypeDetails(UserRequest request, ResourceBundle messages, String type)
			throws AsmsException {

		if (null == request) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_NULL_CODE"),
					messages.getString("REQUEST_NULL"));
		}

		if (null == request.getRequestType() || request.getRequestType().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_TYPE_NULL_CODE"),
					messages.getString("REQUEST_TYPE_NULL"));
		}

		PaymentTypeDetails details = request.getPaymentTypeDetails();

		if (null == details) {
			throw exceptionHandler.constructAsmsException(messages.getString("PAYMENT_TYPE_DETAILS_NULL_CODE"),
					messages.getString("PAYMENT_TYPE_DETAILS_NULL_MSG"));
		}

		if (null == details.getType() || details.getType().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("PAYMENT_TYPE_NULL_CODE"),
					messages.getString("PAYMENT_TYPE_NULL_MSG"));
		}
		
		if (false == details.isDisplay()) {
			throw exceptionHandler.constructAsmsException(messages.getString("PAYMENT_TYPE_DISPLAY_NULL_CODE"),
					messages.getString("PAYMENT_TYPE_DISPLAY_NULL_MSG"));
		}
		
	}
	
	

	/*
	 * Method: validatePaymentTypeDetails -> validates userRequest for null. Parameters ->
	 * UserRequest throws -> AsmsException
	 */

	public void validateFeeMasterDetails(UserRequest request, ResourceBundle messages, String type)
			throws AsmsException {

		if (null == request) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_NULL_CODE"),
					messages.getString("REQUEST_NULL"));
		}

		if (null == request.getRequestType() || request.getRequestType().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_TYPE_NULL_CODE"),
					messages.getString("REQUEST_TYPE_NULL"));
		}

		FeeMasterDetails details = request.getFeeMasterDetails();

		if (null == details) {
			throw exceptionHandler.constructAsmsException(messages.getString("FEE_MASTER_DETAILS_NULL_CODE"),
					messages.getString("FEE_MASTER_DETAILS_NULL_MSG"));
		}

		if ( details.getFeeCategory()  <= 0) {
			throw exceptionHandler.constructAsmsException(messages.getString("FEE_CATEGORY_ID_NULL_CODE"),
					messages.getString("FEE_CATEGORY_ID_NULL_MSG"));
		}
		

		if (null == details.getPaymentTypes() && details.getPaymentTypes().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("NO_OF_INSTALLMENTS_NULL_CODE"),
					messages.getString("NO_OF_INSTALLMENTS_NULL_MSG"));
		}else{
			if(details.getPaymentTypes().contains(Constants.no_of_installments)){
				if(details.getNoOfInstallments() <= 0)
				{
					throw exceptionHandler.constructAsmsException(messages.getString("NO_OF_INSTALLMENTS_INVALID_CODE"),
							messages.getString("NO_OF_INSTALLMENTS_INVALID_MSG"));
				}
			}
		}
		

		if ( details.getFeeCategory() <=0 && details.getPaymentTypes().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("NO_OF_MONTHS_NULL_CODE"),
					messages.getString("NO_OF_MONTHS_NULL_MSG"));
		}else{
			if(details.getPaymentTypes().contains(Constants.no_of_months)){
				if(details.getNoOfInstallments() <= 0)
				{
					throw exceptionHandler.constructAsmsException(messages.getString("NO_OF_MONTHS_INVALID_CODE"),
							messages.getString("NO_OF_MONTHS_INVALID_MSG"));
				}
			}
			
		}

		
	}
	

}
