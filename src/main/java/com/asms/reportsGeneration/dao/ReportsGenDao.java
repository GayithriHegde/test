package com.asms.reportsGeneration.dao;



import com.asms.Exception.AsmsException;
import com.asms.reportsGeneration.request.CurriculamDetails;
import com.asms.usermgmt.entity.User;

public interface ReportsGenDao {
	
	public void getCurriculamReport(CurriculamDetails curriculamDetails, User user, String tenant) throws AsmsException;;
	public void getAllUsers(String year, String tenant ) throws AsmsException;

}
