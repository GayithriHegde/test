package com.asms.studentAdmission.service;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
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
import com.asms.studentAdmission.dao.AdmissionRprtDao;
import com.asms.studentAdmission.helper.ValidateAdmission;
import com.asms.studentAdmission.request.UserReq;
import com.asms.studentAdmission.response.AdmissionResponse;
import com.asms.usermgmt.auth.PrivilegesManager;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.helper.PrincipalUser;

@Service
@Component
@Path("/admission") // this is the entry point of the UserManagement Service
public class AdmissionService {
	
	@Autowired
	private AdmissionRprtDao admissionRprtDao;

	@Autowired
	private PrivilegesManager privilegesManager;

	@Autowired
	private ValidateAdmission validateAdmission;
	
	@Path("/create")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response register(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			UserReq userRequest, @QueryParam("domain") String domain) {
		AdmissionResponse admissionResponse= new AdmissionResponse();
		ResourceBundle messages;
		try {
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			// validate request
			validateAdmission.validateRequestAdmissionDetails(userRequest, messages);
			// validate user details
			
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			// authorize

			// check if logged in user has got rights to create user
			PrincipalUser pUser = privilegesManager.isPrivileged(user, Constants.admin_category_userManagement,
					Constants.privileges.create_check.toString());
			if (pUser.isPrivileged()) {
				
				admissionRprtDao.createAdmission(userRequest.getAdmissionDetails(), user, domain);
				admissionResponse.setCode(200);
				admissionResponse.setMessage("success");
				return Response.status(Status.OK).entity(admissionResponse).build();

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
	
	
	@Path("admissionEnquiry/create")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response admissionEnquiryCreate(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			UserReq userRequest, @QueryParam("domain") String domain) {
		AdmissionResponse admissionResponse= new AdmissionResponse();
		ResourceBundle messages;
		try {
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			// validate request
			validateAdmission.validateRequestAdmissionEnquiryDetails(userRequest.getAdmissionEnquiryDetails(), messages);
			// validate user details
			
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			// authorize

			// check if logged in user has got rights to create user
			PrincipalUser pUser = privilegesManager.isPrivileged(user, Constants.admin_category_userManagement,
					Constants.privileges.create_check.toString());
			if (pUser.isPrivileged()) {
				
				admissionRprtDao.createAdmissionEnquiry(userRequest.getAdmissionEnquiryDetails(), user, domain);
				admissionResponse.setCode(200);
				admissionResponse.setMessage("success");
				return Response.status(Status.OK).entity(admissionResponse).build();

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
	
	
	
	@Path("applicationStatus/create")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response applicationStatus(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			UserReq userRequest, @QueryParam("domain") String domain) {
		AdmissionResponse admissionResponse= new AdmissionResponse();
		ResourceBundle messages;
		try {
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			// validate request
			validateAdmission.validateRequestApplicationStatusDetails(userRequest.getApplicationStatusDetails(), messages);
			// validate user details
			
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			// authorize

			// check if logged in user has got rights to create user
			PrincipalUser pUser = privilegesManager.isPrivileged(user, Constants.admin_category_userManagement,
					Constants.privileges.create_check.toString());
			if (pUser.isPrivileged()) {
				
				admissionRprtDao.createApplicationStatus(userRequest.getApplicationStatusDetails(), user, domain);
				admissionResponse.setCode(200);
				admissionResponse.setMessage("success");
				return Response.status(Status.OK).entity(admissionResponse).build();

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
