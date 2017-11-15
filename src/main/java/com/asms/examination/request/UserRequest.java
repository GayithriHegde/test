package com.asms.examination.request;




/* User Request class represents the request object
 * for Examination module operations 
 * 
 */
public class UserRequest {
	
	
	private String requestType;
	// role
	private String userRole;
	// subrole
	private String subRole;
	
	
	private ExamRequestDetails examRequestDetails;
	
	private ExaminationRequestDetails examinationRequestDetails;
	
	private MarksMasterDetails marksMasterDetails;
	
	private MarksDetails marksDetails;
	
	
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getSubRole() {
		return subRole;
	}
	public void setSubRole(String subRole) {
		this.subRole = subRole;
	}
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
	public MarksMasterDetails getMarksMasterDetails() {
		return marksMasterDetails;
	}
	public void setMarksMasterDetails(MarksMasterDetails marksMasterDetails) {
		this.marksMasterDetails = marksMasterDetails;
	}
	public MarksDetails getMarksDetails() {
		return marksDetails;
	}
	public void setMarksDetails(MarksDetails marksDetails) {
		this.marksDetails = marksDetails;
	}


}
