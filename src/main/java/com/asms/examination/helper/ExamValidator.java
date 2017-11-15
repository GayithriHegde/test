package com.asms.examination.helper;

import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asms.Exception.AsmsException;
import com.asms.Exception.ExceptionHandler;
import com.asms.examination.request.ExamRequestDetails;
import com.asms.examination.request.ExaminationRequestDetails;
import com.asms.examination.request.MarksDetails;
import com.asms.examination.request.MarksMasterDetails;
import com.asms.examination.request.UserRequest;



/*
 * ExamValidator.java does the validation of requests
 * 
 */
@Component
public class ExamValidator {
	
	@Autowired
	private ExceptionHandler exceptionHandler;
	
	
	
	
	/*
	 * Method: validateExamDetails -> validates userRequest for null. Parameters ->
	 * UserRequest throws -> AsmsException
	 */

	public void validateExamDetails(UserRequest request, ResourceBundle messages, String type)
			throws AsmsException {

		if (null == request) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_NULL_CODE"),
					messages.getString("REQUEST_NULL"));
		}

		if (null == request.getRequestType() || request.getRequestType().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_TYPE_NULL_CODE"),
					messages.getString("REQUEST_TYPE_NULL"));
		}

		ExamRequestDetails details = request.getExamRequestDetails();

		if (null == details) {
			throw exceptionHandler.constructAsmsException(messages.getString("EXAM_DETAILS_NULL_CODE"),
					messages.getString("EXAM_DETAILS_NULL_MSG"));
		}

		if (null == details.getExamName() || details.getExamName().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("EXAM_NAME_NULL_CODE"),
					messages.getString("EXAM_NAME_NULL_MSG"));
		}
		if ( details.getBoardId()  <= 0) {
			throw exceptionHandler.constructAsmsException(messages.getString("EXAM_BOARD_ID_NULL_CODE"),
					messages.getString("EXAM_BOARD_ID_NULL_MSG"));
		}

		if (null == details.getAcademicYear() || details.getAcademicYear().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("EXAM_ACADEMIC_YEAR_NULL_CODE"),
					messages.getString("EXAM_ACADEMIC_YEAR_NULL_MSG"));
		}

		if (details.getTotalMarks() <= 0) {
			throw exceptionHandler.constructAsmsException(messages.getString("EXAM_TOTAL_MARKS_NULL_CODE"),
					messages.getString("EXAM_TOTAL_MARKS_NULL_MSG"));
		}

	}
	
	

	/*
	 * Method: validateExaminationDetailsRequest -> validates userRequest for null. Parameters ->
	 * UserRequest throws -> AsmsException
	 */
	public void validateExaminationDetailsRequest(UserRequest request, ResourceBundle messages, String type)
			throws AsmsException {

		if (null == request) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_NULL_CODE"),
					messages.getString("REQUEST_NULL"));
		}

		if (null == request.getRequestType() || request.getRequestType().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_TYPE_NULL_CODE"),
					messages.getString("REQUEST_TYPE_NULL"));
		}

		ExaminationRequestDetails details = request.getExaminationRequestDetails();

		if (null == details) {
			throw exceptionHandler.constructAsmsException(messages.getString("EXAMINATION_DETAILS_NULL_CODE"),
					messages.getString("EXAMINATION_DETAILS_NULL_MSG"));
		}

		if (null == details.getClassName() || details.getClassName().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("EXAMINATION_DETAILS_CLASS_NAME_NULL_CODE"),
					messages.getString("EXAMINATION_DETAILS_CLASS_NAME_NULL_MSG"));
		}
		if (details.getExamDate() ==null) {
			throw exceptionHandler.constructAsmsException(messages.getString("EXAMINATION_DETAILS_EXAM_DATE_NULL_CODE"),
					messages.getString("EXAMINATION_DETAILS_EXAM_DATE_NULL_MSG"));
		}

		if (null == details.getTimeFrom() || details.getTimeFrom().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("EXAMINATION_DETAILS_TIME_FROM_NULL_CODE"),
					messages.getString("EXAMINATION_DETAILS_TIME_FROM_NULL_MSG"));
		}
		
		if (null == details.getTimeTo() || details.getTimeTo().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("EXAMINATION_DETAILS_TIME_TO_NULL_CODE"),
					messages.getString("EXAMINATION_DETAILS_TIME_TO_NULL_MSG"));
		}
		
		if (null == details.getSubjectName() || details.getSubjectName().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("EXAMINATION_DETAILS_SUBJECT_NAME_NULL_CODE"),
					messages.getString("EXAMINATION_DETAILS_SUBJECT_NAME_NULL_MSG"));
		}
		

		if (details.getMarks() <= 0) {
			throw exceptionHandler.constructAsmsException(messages.getString("EXAMINATION_DETAILS_MARKS_NULL_CODE"),
					messages.getString("EXAMINATION_DETAILS_MARKS_NULL_MSG"));
		}
		

	}
	
	
	
	/*
	 * Method: validateMarksMasterRequest -> validates userRequest for null. Parameters ->
	 * UserRequest throws -> AsmsException
	 */
	public void validateMarksMasterRequest(UserRequest request, ResourceBundle messages, String type)
			throws AsmsException {
		if (null == request) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_NULL_CODE"),
					messages.getString("REQUEST_NULL"));
		}

		if (null == request.getRequestType() || request.getRequestType().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_TYPE_NULL_CODE"),
					messages.getString("REQUEST_TYPE_NULL"));
		}
		
		
		MarksMasterDetails details  = request.getMarksMasterDetails();
		
		if (null == details) {
			throw exceptionHandler.constructAsmsException(messages.getString("MARKS_MASTER_DETAILS_NULL_CODE"),
					messages.getString("MARKS_MASTER_DETAILS_NULL_MSG"));
		}

		if (null == details.getAcademicYear() || details.getAcademicYear().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("MARKS_MASTER_DETAILS_ACADEMIC_YEAR_NULL_CODE"),
					messages.getString("MARKS_MASTER_DETAILS_ACADEMIC_YEAR_NULL_MSG"));
		}
		
		if (null == details.getClassId() || details.getClassId().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("MARKS_MASTER_DETAILS_CLASS_ID_NULL_CODE"),
					messages.getString("MARKS_MASTER_DETAILS_CLASS_ID_NULL_MSG"));
		}
		
		if (null == details.getSectionId() || details.getSectionId().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("MARKS_MASTER_DETAILS_SECTION_ID_NULL_CODE"),
					messages.getString("MARKS_MASTER_DETAILS_SECTION_ID_NULL_MSG"));
		}
		
		if (null == details.getStudentId() || details.getStudentId().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("MARKS_MASTER_DETAILS_STUDENT_ID_NULL_CODE"),
					messages.getString("MARKS_MASTER_DETAILS_STUDENT_ID_NULL_MSG"));
		}
		

		
		
		
		
	}
	
	/*
	 * Method: validateMarksRequest -> validates userRequest for null. Parameters ->
	 * UserRequest throws -> AsmsException
	 */
	public void validateMarksRequest(UserRequest request, ResourceBundle messages, String type)
			throws AsmsException {
		if (null == request) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_NULL_CODE"),
					messages.getString("REQUEST_NULL"));
		}

		if (null == request.getRequestType() || request.getRequestType().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_TYPE_NULL_CODE"),
					messages.getString("REQUEST_TYPE_NULL"));
		}
		
		
		MarksDetails details  = request.getMarksDetails();
		
		if (null == details) {
			throw exceptionHandler.constructAsmsException(messages.getString("MARKS_DETAILS_NULL_CODE"),
					messages.getString("MARKS_DETAILS_NULL_MSG"));
		}

		if (null == details.getSubjectId() || details.getSubjectId().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("MARKS_DETAILS_SUBJECT_ID_NULL_CODE"),
					messages.getString("MARKS_DETAILS_SUBJECT_ID_NULL_MSG"));
		}
		
		if (details.getMarks() <= 0) {
			throw exceptionHandler.constructAsmsException(messages.getString("MARKS_DETAILS_MARKS_NULL_CODE"),
					messages.getString("MARKS_DETAILS_MARKS_NULL_MSG"));
		}
		
		if (null == details.getRemarks() || details.getRemarks().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("MARKS_DETAILS_REMARKS_NULL_CODE"),
					messages.getString("MARKS_DETAILS_REMARKS_NULL_MSG"));
		}
		
	
		if (null == details.getExamName() || details.getExamName().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("MARKS_DETAILS_EXAM_NAME_NULL_CODE"),
					messages.getString("MARKS_DETAILS_EXAM_NAME_NULL_MSG"));
		}
		
		
		
	}
	
	
	
	

}
