package com.asms.calendarMgmt.dao;


import com.asms.Exception.AsmsException;
import java.util.List;

import com.asms.calendarMgmt.Request.EventDetails;
import com.asms.calendarMgmt.entity.*;
import com.asms.usermgmt.entity.User;


public interface CalendarMgmtDao {

	
	public List<Calendar> getEventDetails(String tenantId) throws AsmsException;
	
	public void insertCalendar(Calendar calendar, String schema) throws AsmsException;

	void createEvent(EventDetails EventDetails, int userId, String tenantId) throws AsmsException;

	//public void updateEventDetails(EventDetails eventDetails, String tenant) throws AsmsException;

	public void updateEvent(EventDetails eventDetails, User user, String tenant) throws AsmsException;

	//public void updateUser(UserDetails userDetails, User user, String tenant);

	
}
