package com.asms.lessonmgmt.dao;

import java.util.List;


import com.asms.Exception.AsmsException;
import com.asms.lessonmgmt.entity.LessonPlan;
import com.asms.lessonmgmt.request.LessonPlanDetails;

public interface LessonPlanMgmtDao {
	
	
	public List<LessonPlan> getLessonPlans (String tenantId, String userId) throws AsmsException;

	public void createplan(LessonPlanDetails lessonPlanDetails, String tenantId) throws AsmsException;
}
