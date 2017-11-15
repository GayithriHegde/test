package com.asms.calendarMgmt.service;

import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import com.asms.calendarMgmt.Request.UserRequest;
import com.asms.calendarMgmt.dao.CalendarMgmtDao;
import com.asms.calendarMgmt.helper.EventValidator;
import com.asms.calendarMgmt.response.CalendarResponse;
import com.asms.calendarMgmt.entity.Calendar;
import com.asms.Exception.AsmsException;
import com.asms.common.helper.AsmsHelper;
import com.asms.common.helper.Constants;
import com.asms.common.response.FailureResponse;
import com.asms.usermgmt.auth.PrivilegesManager;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.helper.PrincipalUser;



/*
 * CalendarMgmtService.java handles calendar eventcreate, update , get and delete 
 * functionalities
 */

@Service
@Component
@Path("/calendar")
public class CalendarMgmtService {
	
	
	@Resource(name = "asmsdbProperties")
	private Properties dbProperties;
	
	@Autowired
	private PrivilegesManager privilegesManager;
	
	@Autowired
	private CalendarMgmtDao calendarMgmtDao;
	
	
	@Autowired
	private EventValidator eventvalidator;
	
//	@Autowired
//	private ExceptionHandler exceptionHandler;

	@Path("/event")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response addevent(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse, 
			UserRequest userRequest, @QueryParam("tenantId") String tenant) {

		
		CalendarResponse calendarResponse = new CalendarResponse();
		ResourceBundle messages;
		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			eventvalidator.validateCalendar(userRequest, messages);
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			// authorize

			// check if logged in user has got rights to create user
			PrincipalUser pUser = privilegesManager.isPrivileged(user, Constants.academics_category_calendar,
					Constants.privileges.create_check.toString());
			if (pUser.isPrivileged()) {
				
				calendarMgmtDao.createEvent(userRequest.getEventDetails(), user.getSerialNo(), tenant);
				
				return Response.status(Status.OK).entity(calendarResponse).build();

			} else {
				
				failureResponse.setCode(Integer.parseInt(messages.getString("NOT_AUTHORIZED_CODE")));
				failureResponse.setErrorDescription(messages.getString("NOT_AUTHORIZED"));
				return Response.status(200).entity(failureResponse).build();
			}

		} catch (AsmsException ex) {
			// construct failure response
			FailureResponse failureResponse = new FailureResponse(ex);
			return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
		}
	
	}
	
	
@Path("/getevent")
@GET
@Consumes("application/json")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public Response getevent(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse, @QueryParam("tenantId") String tenant) {
	try {

		FailureResponse failureResponse = new FailureResponse();
		// get bundles for error messages
		HttpSession session = hRequest.getSession();
		User user = (User) session.getAttribute("ap_user");
		if (null != user) {

			//CalendarMgmtDao calendarMgmtDao = null;
			List<Calendar> eventDetails = calendarMgmtDao.getEventDetails(tenant);

			CalendarResponse calendarResponse = new CalendarResponse();
			calendarResponse.setEventDetails(eventDetails);
			return Response.status(Status.OK).entity(calendarResponse).build();
		} else {
			return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
		}

	} catch (AsmsException ex) {
		// construct failure response
		FailureResponse failureResponse = new FailureResponse(ex);
		return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
	}

}

	/*
	 * api : /calendar/EventDetails request type :GET
	 * 
	 * Method : EventDetails -> This method is used for Drop Down. Input:No
	 * input Output: Response object * 
	 */




@Path("/update")
@POST
@Consumes("application/json")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")

public Response updateevent(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse, 
		UserRequest userRequest, @QueryParam("tenantId") String tenant) {
			
			CalendarResponse cReponse = new CalendarResponse();
			ResourceBundle messages;
			try {
				// get bundles for error messages
				messages = AsmsHelper.getMessageFromBundle();
				// validate request
				// validator.validateRequest(userRequest, messages, "update");
				// validate user details
				// validator.validateUserDetails(userRequest, messages);
				HttpSession session = hRequest.getSession();
				User user = (User) session.getAttribute("ap_user");
				// authorize

				// check if logged in user has got rights to create user
				PrincipalUser pUser = privilegesManager.isPrivileged(user, Constants.academics_category_calendar,
						Constants.privileges.update_check.toString());
				if (pUser.isPrivileged()) {
					
					
					calendarMgmtDao.updateEvent(userRequest.getEventDetails(),user, tenant);
					cReponse.setMessage("Updated Successfully");
					return Response.status(Status.OK).entity(cReponse).build();

				} else {
					FailureResponse failureResponse = new FailureResponse();
					failureResponse.setCode(Integer.parseInt(messages.getString("NOT_AUTHORIZED_CODE")));
					failureResponse.setErrorDescription(messages.getString("NOT_AUTHORIZED"));
					return Response.status(200).entity(failureResponse).build();
				}

			} catch (AsmsException ex) {
				// construct failure response
				FailureResponse failureResponse = new FailureResponse(ex);
				return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
			}
		}
	
		

}


