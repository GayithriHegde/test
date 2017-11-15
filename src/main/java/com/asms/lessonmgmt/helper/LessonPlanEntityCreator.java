package com.asms.lessonmgmt.helper;

import java.text.ParseException;
import org.springframework.stereotype.Component;

import com.asms.lessonmgmt.entity.LessonPlan;
import com.asms.lessonmgmt.request.LessonPlanDetails;


@Component
public class LessonPlanEntityCreator {

	public LessonPlan createLessonPlan(LessonPlanDetails lessonplandetails) throws ParseException{

		LessonPlan lessonPlan = new LessonPlan();
		
		
		lessonPlan.setUnitId(lessonplandetails.getUnitId());
		lessonPlan.setTotalPeriods(lessonplandetails.getTotalPeriods());
		lessonPlan.setObjective(lessonplandetails.getObjective());
		lessonPlan.setCreatedBy(lessonplandetails.getCreatedBy());
		lessonPlan.setResourcesText(lessonplandetails.getResourcesText());
		lessonPlan.setResourcesPath(lessonplandetails.getResourcesPath());
		lessonPlan.setActivitiesPlannedText(lessonplandetails.getActivitiesPlannedText());
		lessonPlan.setActivitiesPlannedPath(lessonplandetails.getActivitiesPlannedPath());
		lessonPlan.setClassHomeAssignmentText(lessonplandetails.getClassHomeAssignmentText());
		lessonPlan.setClassHomeAssignmentPath(lessonplandetails.getClassHomeAssignmentPath());
		lessonPlan.setActivitiesPlannedText(lessonplandetails.getActivitiesPlannedText());
		lessonPlan.setAcademicyear(lessonplandetails.getAcademicYear());
		lessonPlan.setActivitiesPlannedPath(lessonplandetails.getActivitiesPlannedPath());
		lessonPlan.setPeriodLessanPlans(lessonplandetails.getPeriodLessanPlans());
		return lessonPlan;
}
	
}