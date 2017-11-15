package com.asms.lessonmgmt.service;

import java.util.List;
import java.util.ResourceBundle;

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

import com.asms.Exception.AsmsException;
import com.asms.common.helper.AsmsHelper;
import com.asms.common.helper.Constants;
import com.asms.common.response.FailureResponse;
import com.asms.lessonmgmt.dao.LessonPlanMgmtDao;
import com.asms.lessonmgmt.entity.LessonPlan;
import com.asms.lessonmgmt.helper.LessonPlanValidator;
import com.asms.lessonmgmt.request.LessonPlanDetails;
import com.asms.lessonmgmt.response.LessonPlanResponse;
import com.asms.usermgmt.auth.PrivilegesManager;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.helper.PrincipalUser;

/*
 * LessonMgmtService.java handles lesson plans registration, update , get and delete 
 * functionalities
 */



@Service
@Component
@Path("/lessonplans")
public class LessonPlanMgmtService {
	
	@Autowired
	private LessonPlanMgmtDao lessonPlanMgmtDao;
	
	@Autowired
	private PrivilegesManager privilegesManager;
	
	@Autowired
	private LessonPlanValidator lessonPlanValidator;
	
	@Path("/LessonPlanDetails/create")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response createplan(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse , 
			LessonPlanDetails lessonPlanDetails,  @QueryParam("tenantId") String tenant) {
		LessonPlanResponse  lplanRespons= new LessonPlanResponse();
		ResourceBundle messages;
		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			lessonPlanValidator.validateLessonPlanDetailsRequest(lessonPlanDetails, messages);

			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");


			PrincipalUser pUser = privilegesManager.isPrivileged((User) user, Constants.academics_category_workPlan,
					Constants.privileges.create_check.toString());
			if (pUser.isPrivileged()) {


			  lessonPlanMgmtDao.createplan(lessonPlanDetails,  tenant);;
				return Response.status(Status.OK).entity(lplanRespons).build();
			} 
			
			else {
				return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
			}

		} catch (AsmsException ex) {
			// construct failure response
			FailureResponse failureResponse = new FailureResponse(ex);
			return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
		
	    }
	}

	/*
	 * api : /lessonPlanDetails/LessonPlans request type :GET
	 * 
	 * Method : getLessonPlans -> This method is used for Drop Down.
	 * Input:tenantId input Output: Response object *
	 * 
	 * 
	 */
	
	
	@Path("/LessonPlans")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getLessonPlans(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("tenantId") String tenant, @QueryParam("userId")String userid) {

		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			
				
			
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");

			if (null != user  ) {

				List<LessonPlan> lessonPlan = lessonPlanMgmtDao.getLessonPlans(tenant, userid);

				LessonPlanResponse lessonPlanResponse = new LessonPlanResponse();
				lessonPlanResponse.setLessonPlan(lessonPlan);
				return Response.status(Status.OK).entity(lessonPlanResponse).build();


			} else {
				return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
			}

		
		} catch (AsmsException ex) {
			// construct failure response
			FailureResponse failureResponse = new FailureResponse(ex);
			return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
		}
}
}



