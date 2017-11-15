package com.asms.common.helper;

import java.util.List;

import com.asms.common.response.SuccessResponse;
import com.asms.schoolmgmt.entity.AcademicYear;



public class CommonReponse extends SuccessResponse {
	
	private List<AcademicYear> academicYear;

	

	public List<AcademicYear> getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(List<AcademicYear> academicYear) {
		this.academicYear = academicYear;
	}
}
