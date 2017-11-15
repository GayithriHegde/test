package com.asms.lessonmgmt.response;

import java.util.List;

import com.asms.common.response.SuccessResponse;
import com.asms.lessonmgmt.entity.LessonPlan;
import com.asms.lessonmgmt.request.LessonPlanDetails;


public class LessonPlanResponse extends SuccessResponse{
	
	
	
	private List<LessonPlan> lessonPlan;
	
	private List<LessonPlanDetails> lessonPlanDetails;

	public List<LessonPlanDetails> getLessonPlanDetails() {
		return lessonPlanDetails;
	}

	public void setLessonPlanDetails(List<LessonPlanDetails> lessonPlanDetails) {
		this.lessonPlanDetails = lessonPlanDetails;
	}

	public List<LessonPlan> getLessonPlan() {
		return lessonPlan;
	}

	public void setLessonPlan(List<LessonPlan> lessonPlan) {
		this.lessonPlan = lessonPlan;
	}
		
	

}
