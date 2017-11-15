package com.asms.feemgmt.service;

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
import com.asms.feemgmt.dao.FeeMgmtDao;
import com.asms.feemgmt.entity.FeeCategory;
import com.asms.feemgmt.entity.FeeMaster;
import com.asms.feemgmt.entity.PaymentMode;
import com.asms.feemgmt.helper.FeeValidator;
import com.asms.feemgmt.request.FeeCategoryDetails;
import com.asms.feemgmt.request.FeeMasterDetails;
import com.asms.feemgmt.request.UserRequest;
import com.asms.feemgmt.response.FeeSuccessResponse;
import com.asms.usermgmt.auth.PrivilegesManager;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.helper.PrincipalUser;

/*
 * FeeMgmtService.java handles fee structure,payments, update and delete 
 * functionalities
 */

@Service
@Component
@Path("/fees") // this is the entry point of the FeeMgmt Service
public class FeeMgmtService extends BaseService{
	
	@Autowired
	private PrivilegesManager privilegesManager;

	
	@Autowired
	private FeeMgmtDao feeMgmtDao;

	
	@Resource(name = "asmsdbProperties")
	private Properties dbProperties;
	

	@Autowired
	private FeeValidator feeValidator;

	
	
	/*
	 * api : /fees/feeCategory request type :GET
	 * 
	 * Method : getFeeCategory -> This method is used for Drop Down based on display. Input:No
	 * input Output: Response object *
	 * 
	 * 
	 */
	@Path("/feeCategory")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getFeeCategory(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
	 @QueryParam("tenantId") String tenant) {

		ResourceBundle messages;
		try {
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			
			PrincipalUser pUser = privilegesManager.isPrivileged((User) user, Constants.fee_category_feeStructure,
					Constants.privileges.retrieve_check.toString());

			if (pUser.isPrivileged()) {

				List<FeeCategory> feeCategories = feeMgmtDao.getFeeCategory(tenant);

				FeeSuccessResponse feeSuccessResponse = new FeeSuccessResponse();
				feeSuccessResponse.setFeeCategories(feeCategories);
				
				return Response.status(Status.OK).entity(feeSuccessResponse).build();

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
	
	
	
	
	
	/*
	 * api : /fees/category/create request type :POST
	 * 
	 * Method : createNewFee -> This method does the creation of FeeCategory.
	 *  Input : UserRequest object output: Response object *
	 * 
	 * 
	 */
	@Path("/category/create")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response createNewFee(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			UserRequest userRequest, @QueryParam("tenantId") String tenant)
	{
		
		ResourceBundle messages;
		try {
			// get bundles for error messages
		   messages = AsmsHelper.getMessageFromBundle();
		   
			// validate request
		   feeValidator.validateFeeCategoryDetails(userRequest, messages, "create");
			
			
			
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			
			PrincipalUser pUser = privilegesManager.isPrivileged((User) user, Constants.fee_category_feeStructure,
					Constants.privileges.create_check.toString());
			if (pUser.isPrivileged()) {
			   FeeCategoryDetails feeCategoryDetails = userRequest.getCategoryDetails();
			   FeeSuccessResponse feeSuccessResponse = new FeeSuccessResponse();
			   feeMgmtDao.insertFee(feeCategoryDetails, tenant);
			   
				return Response.status(Status.OK).entity(feeSuccessResponse).build();
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
	
	
	
	/*
	 * api : /fees/category/create request type :POST
	 * 
	 * Method : createSetupFee -> This method does the creation of FeeMaster.
	 *  Input : UserRequest object output: Response object *
	 * 
	 * 
	 */
	@Path("/feeSetup/create")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response createSetupFee(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			UserRequest userRequest, @QueryParam("tenantId") String tenant)
	{
		
		ResourceBundle messages;
		try {
			// get bundles for error messages
		   messages = AsmsHelper.getMessageFromBundle();
		   
			// validate request
		   feeValidator.validateFeeMasterDetails(userRequest, messages,"create");
			
			
			
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			
			PrincipalUser pUser = privilegesManager.isPrivileged((User) user, Constants.fee_category_feeStructure,
					Constants.privileges.create_check.toString());
			if (pUser.isPrivileged()) {
			   FeeMasterDetails feeMasterDetails = userRequest.getFeeMasterDetails();
			   FeeSuccessResponse feeSuccessResponse = new FeeSuccessResponse();
			   feeMgmtDao.setupFee(feeMasterDetails, tenant);
			   
				return Response.status(Status.OK).entity(feeSuccessResponse).build();
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
	
	
	
	/*
	 * api : /fees/feeSetup request type :GET
	 * 
	 * Method : viewSetupFee -> This method is used to view set up table. Input:No
	 * input Output: Response object *
	 * 
	 * 
	 */
	@Path("/feeSetup")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response viewSetupFee(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
	 @QueryParam("tenantId") String tenant) {

		ResourceBundle messages;
		try {
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			
			PrincipalUser pUser = privilegesManager.isPrivileged((User) user, Constants.fee_category_feeStructure,
					Constants.privileges.retrieve_check.toString());

			if (pUser.isPrivileged()) {

				List<FeeMaster> feeMasters = feeMgmtDao.viewSetupFee(tenant);

				FeeSuccessResponse feeSuccessResponse = new FeeSuccessResponse();
				feeSuccessResponse.setFeeMasters(feeMasters);
				
				return Response.status(Status.OK).entity(feeSuccessResponse).build();

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
	
	
	

	/*
	 * api : /fees/paymentMode request type :GET
	 * 
	 * Method : getPaymentMode -> This method is used for Drop Down. Input:No
	 * input Output: Response object *
	 * 
	 * 
	 */
	@Path("/paymentMode")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getPaymentMode(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
	 @QueryParam("tenantId") String tenant) {

		ResourceBundle messages;
		try {
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			
			PrincipalUser pUser = privilegesManager.isPrivileged((User) user, Constants.fee_category_feeStructure,
					Constants.privileges.retrieve_check.toString());

			if (pUser.isPrivileged()) {

				List<PaymentMode> paymentModes = feeMgmtDao.getPaymentMode(tenant);

				FeeSuccessResponse feeSuccessResponse = new FeeSuccessResponse();
				feeSuccessResponse.setPaymentModes(paymentModes);
				
				return Response.status(Status.OK).entity(feeSuccessResponse).build();

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
