package com.asms.calendarMgmt.Request;

import com.asms.calendarMgmt.entity.Calendar;

public class UserDetails {
	
	private Calendar calendar;
	
	private EventDetails eventDetails;
	
	private String userId;	
	
	
	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public EventDetails getEventDetails() {
		return eventDetails;
	}

	public void setEventDetails(EventDetails eventDetails) {
		this.eventDetails = eventDetails;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	

}
