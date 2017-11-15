package com.asms.reportsGeneration.dao;


import com.asms.reportsGeneration.request.CurriculamDetails;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.response.RegistrationResponse;

public interface ReportsGenDao {
	
	public void getCurriculamReport(CurriculamDetails curriculamDetails, User user, String tenant);

}
