/**
	 * @{author} karishma.k 09-oct-2017
	 */

package com.asms.calendarMgmt.helper;

import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asms.Exception.AsmsException;
import com.asms.Exception.ExceptionHandler;
import com.asms.schoolmgmt.helper.ValidateAcademicYear;
import com.asms.calendarMgmt.Request.*;

@Component

public class EventValidator {

	/**
	 * @{author} karishma.k 09-oct-2017
	 */

	@Autowired
	private ExceptionHandler exceptionHandler;

	//private Set<Calendar> calendar;

	public void validateCalendar(UserRequest request, ResourceBundle messages) throws AsmsException {
		// Calendar calendar = request.getUserDetails().getCalendar();

		if (null == request) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_NULL_CODE"),
					messages.getString("REQUEST_NULL"));
		}

		if (null == request.getRequestType() || request.getRequestType().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_TYPE_NULL_CODE"),
					messages.getString("REQUEST_TYPE_NULL"));
		}

		EventDetails eventDetails = request.getEventDetails();

		if (null == eventDetails)
			throw exceptionHandler.constructAsmsException(messages.getString("EVENT_DETAILS_NULL_CODE"),
					messages.getString("EVENT_DETAILS_NULL_MSG"));

		if (null == eventDetails.getFromDate() || eventDetails.getFromDate().isEmpty())
			throw exceptionHandler.constructAsmsException(messages.getString("FROMDATE_DETAILS_NULL_CODE"),
					messages.getString("FROMDATE_DETAILS_NULL_MSG"));
		
		if(eventDetails.getId() < 0) {
			throw exceptionHandler.constructAsmsException(messages.getString("FROMDATE_DETAILS_NULL_CODE"),
					messages.getString("FROMDATE_DETAILS_NULL_MSG"));
		}
		if(null == eventDetails.getRecurrencePattern() || eventDetails.getRecurrencePattern().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("FROMDATE_DETAILS_NULL_CODE"),
					messages.getString("FROMDATE_DETAILS_NULL_MSG"));

		}
		
		if (null == eventDetails.getTodate() || eventDetails.getTodate().isEmpty())
			throw exceptionHandler.constructAsmsException(messages.getString("TODATE_DETAILS_NULL_CODE"),
					messages.getString("TODATE_DETAILS_NULL_MSG"));

		if (null == eventDetails.getFromTime() || eventDetails.getFromTime().isEmpty())
			throw exceptionHandler.constructAsmsException(messages.getString("FROMTIME_DETAILS_NULL_CODE"),
					messages.getString("FROMTIME_DETAILS_NULL_MSG"));

		if (null == eventDetails.getToTime() || eventDetails.getToTime().isEmpty())
			throw exceptionHandler.constructAsmsException(messages.getString("TIMETO_DETAILS_NULL_CODE"),
					messages.getString("TIMETO_DETAILS_NULL_MSG"));

		if (null != eventDetails.getAcademicYear() || eventDetails.getAcademicYear().isEmpty() == false) {
			if ( ! (ValidateAcademicYear.validateAcademicYear(eventDetails.getAcademicYear()))) {
				throw exceptionHandler.constructAsmsException(messages.getString("ACADEMIC_YEAR_INVALID_NULL_CODE"),
						messages.getString("ACADEMIC_YEAR_INVALID_NULL_MSG"));
			}

			
		}else {
			throw exceptionHandler.constructAsmsException(messages.getString("ACADEMICYEAR_DETAILS_NULL_CODE"),
					messages.getString("ACADEMICYEAR_DETAILS_NULL_MSG"));
		}

		if (null == eventDetails.getName() || eventDetails.getName().isEmpty())
			throw exceptionHandler.constructAsmsException(messages.getString("NAME_DETAILS_NULL_CODE"),
					messages.getString("NAME_DETAILS_NULL_MSG"));

	}

	// public Set<Calendar> getCalendar() {
	// return calendar;
	// }
	//
	//
	//
	// public void setCalendar(Set<Calendar> calendar) {
	// this.calendar = calendar;
	// }

}
