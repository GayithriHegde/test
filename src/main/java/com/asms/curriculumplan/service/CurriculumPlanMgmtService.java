package com.asms.curriculumplan.service;

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

import com.asms.Exception.AsmsException;
import com.asms.common.helper.AsmsHelper;
import com.asms.common.helper.Constants;
import com.asms.common.response.FailureResponse;
import com.asms.common.service.BaseService;
import com.asms.curriculumplan.dao.CPlanMgmtDao;
import com.asms.curriculumplan.entity.CurriculumPlan;
import com.asms.curriculumplan.entity.Unit;
import com.asms.curriculumplan.helper.CurriculumPlanValidator;
import com.asms.curriculumplan.request.CurriculumPlanDetails;
import com.asms.curriculumplan.response.CPlanSuccessResponse;
import com.asms.usermgmt.auth.PrivilegesManager;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.helper.PrincipalUser;

/*
 * CurriculumPlanMgmtService.java handles saving curriculum plan, update , get and delete 
 * functionalities
 */

@Service
@Component
@Path("/curriculum-plans")
public class CurriculumPlanMgmtService extends BaseService {

	

	@Autowired
	private CurriculumPlanValidator validator;
	
	@Autowired
	private CPlanMgmtDao cplanMgmtDao;

	@Autowired
	private PrivilegesManager privilegesManager;

	@Resource(name = "asmsdbProperties")
	private Properties dbProperties;


	/*
	 * api : /create request type :POST
	 * 
	 * Method : create -> This method saves the curriculum plan entity
	 * 
	 * 
	 */

	@Path("/create")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response create(@Context HttpServletRequest hRequest,
			@Context HttpServletResponse hResponse, @QueryParam("tenantId") String tenant,
			CurriculumPlanDetails details) {
		try {
			ResourceBundle messages;
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			validator.validate(details, messages);
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");

			PrincipalUser pUser = privilegesManager.isPrivileged((User) user,
					Constants.academics_category_workPlan.toString(),
					Constants.privileges.create_check.toString());
			if (pUser.isPrivileged()) {

				List<Unit> units = cplanMgmtDao.createCPlan(details, user.getUserId(), tenant);

				CPlanSuccessResponse cpSuccessResponse = new CPlanSuccessResponse();
				cpSuccessResponse.setUnits(units);
				return Response.status(Status.OK).entity(cpSuccessResponse).build();

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
	 * api : /get request type :GET
	 * 
	 * Method : getCurriculumPlans -> This method is used get the curriculum plans
	 * for student and parent login
	 * 
	 * 
	 */

	@Path("/get")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getCurriculumPlans(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("tenantId") String tenant) {
		try {
			
			

			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");

			PrincipalUser pUser = privilegesManager.isPrivileged((User) user,
					Constants.academics_category_broadcastMessages.toString(),
					Constants.privileges.retrieve_check.toString());
			if (pUser.isPrivileged()) {

				List<CurriculumPlan> cPlans = cplanMgmtDao.getCPlans(user, tenant);

				CPlanSuccessResponse cPlanSuccessResponse = new CPlanSuccessResponse();
				cPlanSuccessResponse.setcPlans(cPlans);
				return Response.status(Status.OK).entity(cPlanSuccessResponse).build();

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
