package com.asms.curriculumplan.dao;

import java.util.List;

import com.asms.Exception.AsmsException;
import com.asms.curriculumplan.entity.CurriculumPlan;
import com.asms.curriculumplan.entity.Unit;
import com.asms.curriculumplan.request.CurriculumPlanDetails;
import com.asms.usermgmt.entity.User;

public interface CPlanMgmtDao {





	
	public List<Unit> createCPlan(CurriculumPlanDetails details, String userId, String tenant) throws AsmsException;
	
	public List<CurriculumPlan> getCPlans(User user, String tenant) throws AsmsException;


	

	


	

}
