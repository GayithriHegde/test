package com.asms.examination.request;


/*
 * Class name: ExamRequestDetails
 * class is the mapping class for the UI 
 */
public class ExamRequestDetails {
	
	private String examName;
	
	private int boardId;
	
	private String academicYear;
	
	private int totalMarks;
	
	
	

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

}
