package com.asms.reportsGeneration.request;

import java.util.List;

import com.asms.curriculumplan.entity.Unit;

public class RequestForReports {

	private CurriculamDetails curriculamDetails;
	private List<Unit> Unit;

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



}
