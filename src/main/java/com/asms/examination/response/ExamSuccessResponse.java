package com.asms.examination.response;

import java.util.List;

import com.asms.common.response.SuccessResponse;
import com.asms.examination.entity.ExaminationDetails;
import com.asms.examination.request.ExamRequestDetails;
import com.asms.examination.request.ExaminationRequestDetails;

/*
 * ExamSuccessResponse.java is to return Examination Success response
 */

public class ExamSuccessResponse extends SuccessResponse{
	
	private ExamRequestDetails examRequestDetails;
	
	private ExaminationRequestDetails examinationRequestDetails;
	
	private List<ExaminationDetails> examinationDetails;
	
	private List<String> examNames;

	public ExamRequestDetails getExamRequestDetails() {
		return examRequestDetails;
	}

	public void setExamRequestDetails(ExamRequestDetails examRequestDetails) {
		this.examRequestDetails = examRequestDetails;
	}

	public ExaminationRequestDetails getExaminationRequestDetails() {
		return examinationRequestDetails;
	}

	public void setExaminationRequestDetails(ExaminationRequestDetails examinationRequestDetails) {
		this.examinationRequestDetails = examinationRequestDetails;
	}

	public List<ExaminationDetails> getExaminationDetails() {
		return examinationDetails;
	}

	public void setExaminationDetails(List<ExaminationDetails> examinationDetails) {
		this.examinationDetails = examinationDetails;
	}

	public List<String> getExamNames() {
		return examNames;
	}

	public void setExamNames(List<String> examNames) {
		this.examNames = examNames;
	}
	

}
