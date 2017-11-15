package com.asms.curriculumplan.helper;

import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asms.Exception.AsmsException;
import com.asms.Exception.ExceptionHandler;
import com.asms.curriculumplan.request.CurriculumPlanDetails;

/*
 * CurriculumPlanValidator.java does the validation of requests
 * 
 */

@Component
public class CurriculumPlanValidator {

	@Autowired
	private ExceptionHandler exceptionHandler;

	//private static final Logger logger = LoggerFactory.getLogger(CurriculumPlanValidator.class);

	/*
	 * Method : validate - > validates CurriculumPlanDetails fields input :
	 * CurriculumPlanDetails, messages output : void
	 */
	public void validate(CurriculumPlanDetails details, ResourceBundle messages) throws AsmsException {
		if (null == details) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_NULL_CODE"),
					messages.getString("REQUEST_NULL"));
		}
		if (details.getId() <= 0) {

			if (null == details.getClassName() || details.getClassName().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("CLASSNAME_NULL_CODE"),
						messages.getString("CLASSNAME_NULL_MSG"));
			}

			if (null == details.getSectionName() || details.getSectionName().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("SECTION_NULL_CODE"),
						messages.getString("SECTION_NULL_MSG"));
			}
			if (null == details.getSubject() || details.getSubject().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("SUBJECT_NULL_CODE"),
						messages.getString("SUBJECT_NULL_MSG"));
			}
		}
		if (null == details.getUnitNo() || details.getUnitName().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("UNIT_NO_INVALID_CODE"),
					messages.getString("UNIT_NO_INVALID_MSG"));
		}
		if (null == details.getUnitName() || details.getUnitName().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("UNIT_NAME_NULL_CODE"),
					messages.getString("UNIT_NAME_NULL_MSG"));
		}

		if (details.getMarks() <= 0) {
			throw exceptionHandler.constructAsmsException(messages.getString("MARKS_INVALID_CODE"),
					messages.getString("MARKS_INVALID_MSG"));
		}

		if (null == details.getNoOfPeriods() || details.getNoOfPeriods().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("NO_PERIODS_INVALID_CODE"),
					messages.getString("NO_PERIODS_INVALID_MSG"));
		}
		if (null == details.getPlannedForMonth() || details.getPlannedForMonth().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("PLANNED_MONTH_NULL_CODE"),
					messages.getString("PLANNED_MONTH_NULL_MSG"));
		}

	}

}