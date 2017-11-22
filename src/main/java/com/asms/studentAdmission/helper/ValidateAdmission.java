package com.asms.studentAdmission.helper;

import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asms.Exception.AsmsException;
import com.asms.Exception.ExceptionHandler;
import com.asms.studentAdmission.request.AdmissionEnquiryDetails;
import com.asms.studentAdmission.request.ApplicationStatusDetails;
import com.asms.studentAdmission.request.UserReq;

@Component
public class ValidateAdmission {

	@Autowired
	private ExceptionHandler exceptionHandler;

	// AdmissionDetails validation
	public void validateRequestAdmissionDetails(UserReq userRequest, ResourceBundle messages) throws AsmsException
	{
		if (null == userRequest) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_NULL_CODE"),
					messages.getString("REQUEST_NULL_MSG"));
		}
		
		if (null == userRequest.getAdmissionDetails().getStudentName() || userRequest.getAdmissionDetails().getStudentName().trim().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("STUDENTNAME_NULL_CODE"),
					messages.getString("STUDENTNAME_NULL_MSG"));
		}
		if (null == userRequest.getAdmissionDetails().getStudentType() || userRequest.getAdmissionDetails().getStudentType().trim().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("STUDENTTYPE_NULL_CODE"),
					messages.getString("STUDENTTYPE_NULL_MSG"));
		}
		if (null == userRequest.getAdmissionDetails().getAdminssionID() || userRequest.getAdmissionDetails().getAdminssionID().trim().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("ADMINSSIONID_NULL_CODE"),
					messages.getString("ADMINSSIONID_NULL_MSG"));
		}
		if (null == userRequest.getAdmissionDetails().getAdminssionYear() || userRequest.getAdmissionDetails().getAdminssionYear().trim().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("ADMINSSIONYEAR_NULL_CODE"),
					messages.getString("ADMINSSIONYEAR_NULL_MSG"));
		}
		if (null == userRequest.getAdmissionDetails().getAdminssionDate() || userRequest.getAdmissionDetails().getAdminssionDate().toString().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("ADMINSSIONDATE_NULL_CODE"),
					messages.getString("ADMINSSIONDATE_NULL_MSG"));
		}
		if (null == userRequest.getAdmissionDetails().getGender() || userRequest.getAdmissionDetails().getGender().trim().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("GENDER_NULL_CODE"),
					messages.getString("GENDER_NULL_MSG"));
		}
		if (null == userRequest.getAdmissionDetails().getClassName() || userRequest.getAdmissionDetails().getClassName().trim().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("CLASSNAME_NULL_CODE"),
					messages.getString("CLASSNAME_NULL_MSG"));
		}
		if (0 >= userRequest.getAdmissionDetails().getPhnNumber() ) {
			throw exceptionHandler.constructAsmsException(messages.getString("PHNNUMBER_NULL_CODE"),
					messages.getString("PHNNUMBER_NULL_MSG"));
		}
		if (null == userRequest.getAdmissionDetails().getDob() || userRequest.getAdmissionDetails().getDob().toString().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("DOB_NULL_CODE"),
					messages.getString("DOB_NULL_MSG"));
		}
		if (null == userRequest.getAdmissionDetails().getBirthPlcae() || userRequest.getAdmissionDetails().getBirthPlcae().trim().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("BIRTHPLCAE_NULL_CODE"),
					messages.getString("BIRTHPLCAE_NULL_MSG"));
		}
		if (null == userRequest.getAdmissionDetails().getCaste() || userRequest.getAdmissionDetails().getCaste().trim().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("CASTE_NULL_CODE"),
					messages.getString("CASTE_NULL_MSG"));
		}
		
		
		
		
	}
	
	public void validateRequestAdmissionEnquiryDetails(AdmissionEnquiryDetails admissionEnquiryDetails, ResourceBundle messages) throws AsmsException
	{
		if (null == admissionEnquiryDetails) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_NULL_CODE"),
					messages.getString("REQUEST_NULL_MSG"));
		}
		
		if (null == admissionEnquiryDetails.getStudentName() || admissionEnquiryDetails.getStudentName().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("STUDENTNAME_NULL_CODE"),
					messages.getString("STUDENTNAME_NULL_MSG"));
		}
		if (null == admissionEnquiryDetails.getAdmissionAppliedDate() || admissionEnquiryDetails.getAdmissionAppliedDate().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("ADMISSIONAPPLIEDDATE_NULL_CODE"),
					messages.getString("ADMISSIONAPPLIEDDATE_NULL_MSG"));
		}
		if (null == admissionEnquiryDetails.getGender() || admissionEnquiryDetails.getGender().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("GENDER_NULL_CODE"),
					messages.getString("GENDER_NULL_MSG"));
		}
		if (0 >= admissionEnquiryDetails.getPhoneNumber() ) {
			throw exceptionHandler.constructAsmsException(messages.getString("PHONENUMBER_NULL_CODE"),
					messages.getString("PHONENUMBER_NULL_MSG"));
		}
		if (null == admissionEnquiryDetails.getClassApplied() || admissionEnquiryDetails.getClassApplied().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("CLASSAPPLIED_NULL_CODE"),
					messages.getString("CLASSAPPLIED_NULL_MSG"));
		}
		if (null == admissionEnquiryDetails.getPreviousSchoolName() || admissionEnquiryDetails.getPreviousSchoolName().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("PREVIOUSSCHOOLNAME_NULL_CODE"),
					messages.getString("PREVIOUSSCHOOLNAME_NULL_MSG"));
		}
		if (null == admissionEnquiryDetails.getPreviousSchoolBoard() || admissionEnquiryDetails.getPreviousSchoolBoard().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("PREVIOUSSCHOOLBOARD_NULL_CODE"),
					messages.getString("PREVIOUSSCHOOLBOARD_NULL_MSG"));
		}
		if (null == admissionEnquiryDetails.getDob() || admissionEnquiryDetails.getDob().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("DOB_NULL_CODE"),
					messages.getString("DOB_NULL_MSG"));
		}
		
		
	}
	public void validateRequestApplicationStatusDetails(ApplicationStatusDetails applicationStatusDetails, ResourceBundle messages) throws AsmsException
	{
		if (null == applicationStatusDetails) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_NULL_CODE"),
					messages.getString("REQUEST_NULL_MSG"));
		}
		
		if (null == applicationStatusDetails.getStudentName() || applicationStatusDetails.getStudentName().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("STUDENTNAME_NULL_CODE"),
					messages.getString("STUDENTNAME_NULL_MSG"));
		}
		if (null == applicationStatusDetails.getAdmissionAppliedDate() || applicationStatusDetails.getAdmissionAppliedDate().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("ADMISSIONAPPLIEDDATE_NULL_CODE"),
					messages.getString("ADMISSIONAPPLIEDDATE_NULL_MSG"));
		}
		if (null == applicationStatusDetails.getGender() || applicationStatusDetails.getGender().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("GENDER_NULL_CODE"),
					messages.getString("GENDER_NULL_MSG"));
		}
		if (0 >= applicationStatusDetails.getPhoneNumber() ) {
			throw exceptionHandler.constructAsmsException(messages.getString("PHONENUMBER_NULL_CODE"),
					messages.getString("PHONENUMBER_NULL_MSG"));
		}
		if (null == applicationStatusDetails.getClassApplied() || applicationStatusDetails.getClassApplied().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("CLASSAPPLIED_NULL_CODE"),
					messages.getString("CLASSAPPLIED_NULL_MSG"));
		}
		if (null == applicationStatusDetails.getPreviousSchoolName() || applicationStatusDetails.getPreviousSchoolName().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("PREVIOUSSCHOOLNAME_NULL_CODE"),
					messages.getString("PREVIOUSSCHOOLNAME_NULL_MSG"));
		}
		if (null == applicationStatusDetails.getPreviousSchoolBoard() || applicationStatusDetails.getPreviousSchoolBoard().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("PREVIOUSSCHOOLBOARD_NULL_CODE"),
					messages.getString("PREVIOUSSCHOOLBOARD_NULL_MSG"));
		}
		if (null == applicationStatusDetails.getDob() || applicationStatusDetails.getDob().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("DOB_NULL_CODE"),
					messages.getString("DOB_NULL_MSG"));
		}
		if (null == applicationStatusDetails.getDob() || applicationStatusDetails.getDob().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("STATUS_NULL_CODE"),
					messages.getString("STATUS_NULL_MSG"));
		}

	}
}
