package com.asms.lessonmgmt.helper;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.asms.Exception.AsmsException;
import com.asms.Exception.ExceptionHandler;
import com.asms.lessonmgmt.request.LessonPlanDetails;

@Component
public class LessonPlanValidator {

	/**
	 * @{author} Praveen.Tiwari 9-Oct-2017
	 */

	private ExceptionHandler exceptionHandler;

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(LessonPlanValidator.class);

	/*
	 * Method: validateLessonPlanDetailsRequest -> validate
	 * LessonPlanDetailsRequest for null. Parameters -> UserRequest throws ->
	 * AsmsException
	 */

	@SuppressWarnings("unused")
	public void validateLessonPlanDetailsRequest(LessonPlanDetails details, ResourceBundle messages) throws AsmsException {
		if (null == details) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_NULL_CODE"),
					messages.getString("REQUEST_NULL"));
		}

	

		if (null == details) {
			throw exceptionHandler.constructAsmsException(messages.getString("LESSONPLAN_DETAILS_NULL_CODE"),
					messages.getString("LESSONPLAN_DETAILS_NULL_MSG"));
		}
		if (null == details.getResourcesText() || details.getResourcesText().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("LESSONPLAN_RESOURCESTEXT_NULL_CODE"),
					messages.getString("LESSONPLAN_RESOURCESTEXT_NULL_MSG"));
		}
		if (null == details.getActivitiesPlannedText() || details.getActivitiesPlannedText().isEmpty()) {
			throw exceptionHandler.constructAsmsException(
					messages.getString("LESSONPLAN_ACTIVITIESPLANNEDTEXT_NULL_CODE"),
					messages.getString("LESSONPLAN_ACTIVITIESPLANNEDTEXT_NULL_MSG"));
		}
		if (null == details.getClassHomeAssignmentText() || details.getClassHomeAssignmentText().isEmpty()) {
			throw exceptionHandler.constructAsmsException(
					messages.getString("LESSONPLAN_CLASSHOMEASSIGNMENTTEXT_NULL_CODE"),
					messages.getString("LESSONPLAN_CLASSHOMEASSIGNMENTTEXT_NULL_MSG"));
		}
		if (null == details.getPlannedAssignmentText() || details.getPlannedAssignmentText().isEmpty()) {
			throw exceptionHandler.constructAsmsException(
					messages.getString("LESSONPLAN_PLANNEDASSIGNMENTTEXT_NULL_CODE"),
					messages.getString("LESSONPLAN_PLANNEDASSIGNMENTTEXT_NULL_MSG"));
		}

		if (details.getTotalPeriods() <= 0) {
			throw exceptionHandler.constructAsmsException(messages.getString("LESSONPLAN_TOTALPERIODS_NULL_CODE"),
					messages.getString("LESSONPLAN_TOTALPERIODS_NULL_MSG"));
		}
		if (details.getUnitId() <= 0) {
			throw exceptionHandler.constructAsmsException(messages.getString("LESSONPLAN_UNITID_NULL_CODE"),
					messages.getString("LESSONPLAN_UNITID_NULL_MSG"));

		}
	}
}