package com.asms.examination.request;

import java.util.Date;

import com.asms.examination.entity.Examination;


/*
 * Class name: ExaminationRequestDetails
 * class is the mapping class for the UI 
 */
public class ExaminationRequestDetails {
	
	
	private String className;
	
	private Date examDate;
	
	private String timeFrom;
	
	private String timeTo;
	
	private String subjectName;
	
	private Examination examId;
	
	private int marks;
	
	


	public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
	}


	


	public String getTimeFrom() {
		return timeFrom;
	}


	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}


	public String getTimeTo() {
		return timeTo;
	}


	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}


	public String getSubjectName() {
		return subjectName;
	}


	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}


	public int getMarks() {
		return marks;
	}


	public void setMarks(int marks) {
		this.marks = marks;
	}


	public Examination getExamId() {
		return examId;
	}


	public void setExamId(Examination examId) {
		this.examId = examId;
	}


	public Date getExamDate() {
		return examDate;
	}


	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}



}
