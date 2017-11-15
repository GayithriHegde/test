package com.asms.reportsGeneration.service;
/*
 * ReportsGenerationService.java manages report generation(gives reports regarding admission enqry report,
 * curriculam report fee mgmt report etc.. 
 * 
 */

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
import com.asms.Exception.ExceptionHandler;
import com.asms.common.helper.AsmsHelper;
import com.asms.common.response.FailureResponse;
import com.asms.common.service.BaseService;
import com.asms.reportsGeneration.dao.ReportsGenDao;
import com.asms.reportsGeneration.helper.ReportGenValidator;
import com.asms.reportsGeneration.request.CurriculamDetails;
import com.asms.reportsGeneration.request.RequestForReports;
import com.asms.schoolmgmt.helper.ValidateAcademicYear;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.response.RegistrationResponse;

@Service
@Component
@Path("/reports")
// this is the entry point of the ReportsGeneration Service class
public class ReportsGenerationService extends BaseService {

	@Autowired
	private ReportsGenDao reportsGenDao;

	// @Autowired
	// private PrivilegesManager privilegesManager;

	@Autowired
	private ReportGenValidator reportGenValidator;

	@Autowired
	private ExceptionHandler exceptionHandler;

	@Path("/curriculamplan/create")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response register(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			RequestForReports requestForReports, @QueryParam("tenantId") String tenant) {
		RegistrationResponse rReponse = new RegistrationResponse();
		ResourceBundle messages;

		CurriculamDetails curriculamDetails = null;
		try {
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			// validate request

			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			// authorize

			// check if logged in user has got rights to create user
			/*
			 * PrincipalUser pUser = privilegesManager.isPrivileged(user,
			 * Constants.admin_category_userManagement,
			 * Constants.privileges.create_check.toString()); if (pUser.isPrivileged()) {
			 */
			if (user != null) {
				curriculamDetails = requestForReports.getCurriculamDetails();

				reportGenValidator.validateRequest(curriculamDetails, messages);

				reportsGenDao.getCurriculamReport(curriculamDetails, user, tenant);

				return Response.status(Status.OK).entity(rReponse).build();

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

	@Path("/allusers")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response listOfStaffMembers(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("year") String year, @QueryParam("tenantId") String tenant) {
		RegistrationResponse rReponse = new RegistrationResponse();
		ResourceBundle messages;
		try {
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			// validate request

			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			// authorize

			// check if logged in user has got rights to create user
			
			if (user != null) {
				if (null == year || year.isEmpty() == true) {
					throw exceptionHandler.constructAsmsException(messages.getString("year_not_valid_null_code"),
							messages.getString("year_not_valid_null_msg"));
				}

				if (!(ValidateAcademicYear.validateAcademicYear(year))) {
					throw exceptionHandler.constructAsmsException(messages.getString("Invalid_year_null_code"),
							messages.getString("Invalid_year_null_msg"));
				}

				reportsGenDao.getAllUsers(year, tenant);
				
				return Response.status(Status.OK).entity(rReponse).build();

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
