package com.asms.reportsGeneration.request;

import java.util.List;

import com.asms.curriculumplan.entity.Unit;
import com.asms.usermgmt.entity.User;

public class RequestForReports {

	private CurriculamDetails curriculamDetails;
	private List<Unit> Unit;//curriculum plan details
	private List<User> usersList;// list of all teaching staff and non teaching staff users 
	private AdmissionReport admissionReport;

	


	public List<User> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<User> usersList) {
		this.usersList = usersList;
	}

	public List<Unit> getUnit() {
		return Unit;
	}

	public void setUnit(List<Unit> unit) {
		Unit = unit;
	}

	public CurriculamDetails getCurriculamDetails() {
		return curriculamDetails;
	}

	public void setCurriculamDetails(CurriculamDetails curriculamDetails) {
		this.curriculamDetails = curriculamDetails;
	}

	public AdmissionReport getAdmissionReport() {
		return admissionReport;
	}

	public void setAdmissionReport(AdmissionReport admissionReport) {
		this.admissionReport = admissionReport;
	}




}
