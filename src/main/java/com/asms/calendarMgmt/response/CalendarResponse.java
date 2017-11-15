package com.asms.calendarMgmt.response;

import com.asms.common.response.SuccessResponse;


import java.util.List;

import com.asms.calendarMgmt.entity.*;

public class CalendarResponse extends SuccessResponse {
	
	private Calendar calendar;
	
	private List<Calendar> eventDetails;
	
	private String message;
	

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public List<Calendar> getEventDetails() {
		return eventDetails;
	}

	public void setEventDetails(List<Calendar> eventDetails) {
		this.eventDetails = eventDetails;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

	
	
}
