package com.asms.examination.request;

/*
 * Class name: MarksDetails
 * class is the mapping class for the UI 
 */
public class MarksDetails {
	
	private int marks;
	
	private String subjectId;
	
	
	private String remarks;
	
	private String examName;


	public int getMarks() {
		return marks;
	}


	public void setMarks(int marks) {
		this.marks = marks;
	}


	public String getSubjectId() {
		return subjectId;
	}


	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getExamName() {
		return examName;
	}


	public void setExamName(String examName) {
		this.examName = examName;
	}

}
