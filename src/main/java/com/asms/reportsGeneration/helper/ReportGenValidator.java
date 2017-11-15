package com.asms.reportsGeneration.helper;

import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.asms.Exception.AsmsException;
import com.asms.Exception.ExceptionHandler;
import com.asms.reportsGeneration.request.CurriculamDetails;
import com.asms.schoolmgmt.helper.ValidateAcademicYear;
@Service
@Component
public class ReportGenValidator {
	
	@Autowired
	private ExceptionHandler exceptionHandler;
	
	public void validateRequest(CurriculamDetails curriculamDetails, ResourceBundle messages) throws AsmsException {

	if (null == curriculamDetails) {
		throw exceptionHandler.constructAsmsException(messages.getString("CURRICULAM_REPORT_TYPE_NULL_CODE"),
				messages.getString("CURRICULAM_REPORT_TYPE_NULL_MSG"));

	}

	if (null == curriculamDetails.getAcademicYear() || true == curriculamDetails.getAcademicYear().isEmpty()) {
		throw exceptionHandler.constructAsmsException(messages.getString("CURRICULAM_REPORT_ACADEMIC_YEAR_NULL_CODE"),
				messages.getString("CURRICULAM_REPORT_ACADEMIC_YEAR_NULL_MSG"));
	}
	if ( ! (ValidateAcademicYear.validateAcademicYear(curriculamDetails.getAcademicYear()))) {
		throw exceptionHandler.constructAsmsException(messages.getString("CURRICULAM_REPORT_VALID_ACADEMIC_YEAR_NULL_CODE"),
				messages.getString("CURRICULAM_REPORT_VALID_ACADEMIC_YEAR_NULL_MSG"));
	}
	
	
	if (null == curriculamDetails.getClass() || true == curriculamDetails.getClassName().isEmpty()) {
		throw exceptionHandler.constructAsmsException(messages.getString("CURRICULAM_REPORT_CLASS_NAME_NULL_CODE"),
				messages.getString("CURRICULAM_REPORT_CLASS_NAME_NULL_MSG"));

	}
	
	if (null == curriculamDetails.getSubject() || true == curriculamDetails.getSubject().isEmpty()) {
		throw exceptionHandler.constructAsmsException(messages.getString("CURRICULAM_REPORT_SUBJECT_NULL_CODE"),
				messages.getString("CURRICULAM_REPORT_SUBJECTS_NULL_MSG"));

	}
	
	
	
	}
}
