package com.asms.messagemgmt.service;

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

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.asms.Exception.AsmsException;
import com.asms.Exception.ExceptionHandler;
import com.asms.common.helper.AsmsHelper;
import com.asms.common.helper.Constants;
import com.asms.common.response.FailureResponse;
import com.asms.common.service.BaseService;
import com.asms.messagemgmt.dao.MessageMgmtDao;
import com.asms.messagemgmt.entity.Message;
import com.asms.messagemgmt.entity.MessageReceiver;
import com.asms.messagemgmt.helper.MessageValidator;
import com.asms.messagemgmt.request.BroadCasteSearchTypesDetails;
import com.asms.messagemgmt.response.MessageDetails;
import com.asms.messagemgmt.response.MessageSuccessResponse;
import com.asms.usermgmt.auth.PrivilegesManager;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.helper.PrincipalUser;
import com.asms.usermgmt.request.UserBasicDetails;

/*
 * SchoolMgmtService.java handles school registration, update , get and delete 
 * functionalities
 */

@Service
@Component
@Path("/messages")
public class MessageMgmtService extends BaseService {

	@Autowired
	private MessageMgmtDao messageMgmtDao;

	@Autowired
	private MessageValidator messageValidator;

	@Autowired
	private PrivilegesManager privilegesManager;

	@Resource(name = "asmsdbProperties")
	private Properties dbProperties;

	@Autowired
	private ExceptionHandler exceptionHandler;

	//private static final Logger logger = LoggerFactory.getLogger(MessageMgmtService.class);

	/*
	 * api : /broadcastmessages/search request type :POST
	 * 
	 * Method : searchForbroadCasteMessages -> This method is used to search the
	 * users list for broadcasting messages Input:UserRequest Output: Response
	 * object *
	 * 
	 * 
	 */

	@Path("/broadcastmessages/search")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response searchForbroadCasteMessages(@Context HttpServletRequest hRequest,
			@Context HttpServletResponse hResponse, @QueryParam("tenantId") String tenant,
			BroadCasteSearchTypesDetails details) {
		try {
			ResourceBundle messages;
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			if (null == details) {
				throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_NULL_CODE"),
						messages.getString("REQUEST_NULL"));
			}
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");

			PrincipalUser pUser = privilegesManager.isPrivileged((User) user,
					Constants.academics_category_broadcastMessages.toString(),
					Constants.privileges.create_check.toString());
			if (pUser.isPrivileged()) {

				List<UserBasicDetails> userDetails = messageMgmtDao.searchForBroadcastmessages(details, tenant);

				MessageSuccessResponse messageSuccessResponse = new MessageSuccessResponse();
				messageSuccessResponse.setUserBasicDetails(userDetails);
				return Response.status(Status.OK).entity(messageSuccessResponse).build();

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
	 * api : /broadcastmessages/search request type :GET
	 * 
	 * Method : getSentMessages -> This method is used get the sent messages
	 * 
	 * 
	 */

	@Path("/broadcastmessages/get/sent")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getSentMessages(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("tenantId") String tenant) {
		try {
			//ResourceBundle messages;
			// get bundles for error messages
			//messages = AsmsHelper.getMessageFromBundle();

			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");

			PrincipalUser pUser = privilegesManager.isPrivileged((User) user,
					Constants.academics_category_broadcastMessages.toString(),
					Constants.privileges.retrieve_check.toString());
			if (pUser.isPrivileged()) {

				List<MessageDetails> messageDetails = messageMgmtDao.getSentMessages(user.getSerialNo(), tenant);

				MessageSuccessResponse messageSuccessResponse = new MessageSuccessResponse();
				messageSuccessResponse.setMessageDetails(messageDetails);
				return Response.status(Status.OK).entity(messageSuccessResponse).build();

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
	 * api : /broadcastmessages/search request type :GET
	 * 
	 * Method : getSentMessages -> This method is used get the sent messages
	 * 
	 * 
	 */

	@Path("/broadcastmessages/get/received")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getReceivedMessages(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("type") String type, @QueryParam("tenantId") String tenant) {
		try {
			//ResourceBundle messages;
			// get bundles for error messages
			//messages = AsmsHelper.getMessageFromBundle();

			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");

			PrincipalUser pUser = privilegesManager.isPrivileged((User) user,
					Constants.academics_category_broadcastMessages.toString(),
					Constants.privileges.retrieve_check.toString());
			if (pUser.isPrivileged()) {

				List<MessageDetails> messageDetails = messageMgmtDao.getReceivedMessages(user.getUserId(), type,
						tenant);

				MessageSuccessResponse messageSuccessResponse = new MessageSuccessResponse();
				messageSuccessResponse.setMessageDetails(messageDetails);
				return Response.status(Status.OK).entity(messageSuccessResponse).build();

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
	 * api : /school/broadCasteMessages request type :POST
	 * 
	 * Method : createbroadCasteMessages -> This method is used to send the
	 * emails . Input:UserRequest Output: Response object *
	 * 
	 * 
	 */

	@Path("/broadcastemessages")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response createbroadCasteMessages(@Context HttpServletRequest hRequest,
			@Context HttpServletResponse hResponse, @QueryParam("tenantId") String tenant,
			BroadCasteSearchTypesDetails details) {
		try {
			FailureResponse failureResponse = new FailureResponse();
			ResourceBundle messages;
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			if (null == details) {
				throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_NULL_CODE"),
						messages.getString("REQUEST_NULL"));
			}
			messageValidator.validateMessageDetails(details, messages);

			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");

			PrincipalUser pUser = privilegesManager.isPrivileged((User) user,
					Constants.academics_category_broadcastMessages.toString(),
					Constants.privileges.create_check.toString());
			if (pUser.isPrivileged()) {

				messageMgmtDao.createBoradCasteMessage(details, user, tenant);

				MessageSuccessResponse messageSuccessResponse = new MessageSuccessResponse();
				// schoolSuccessResponse.setDetails(emails);
				return Response.status(Status.OK).entity(messageSuccessResponse).build();

			} else {
				return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
			}

		} catch (AsmsException ex) {
			// construct failure response
			FailureResponse failureResponse = new FailureResponse(ex);
			return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
		}
	}

	@Path("/update/status/read")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response updateReadStatus(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("messageId") int messageId, @QueryParam("tenantId") String tenant) {
		MessageReceiver messageReceiver = new MessageReceiver();
		ResourceBundle messages;
		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();

			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");

			PrincipalUser pUser = privilegesManager.isPrivileged((User) user, Constants.academics_category_broadcastMessages,
					Constants.privileges.update_check.toString());
			if (pUser.isPrivileged()) {

				messageMgmtDao.updateReadStatus(messageId, user, tenant);
				return Response.status(Status.OK).entity(messageReceiver).build();
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

	@Path("/unread")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getUnreadMessages(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("tenantId") String tenant) {

		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages

			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");

			if (null != user) {

				List<Message> messages = messageMgmtDao.getUnreadMessages(user, tenant);

				MessageReceiver messageReceiver = new MessageReceiver();
				Message messageObject = null;
				messageReceiver.setMessageObject(messageObject);
				return Response.status(Status.OK).entity(messages).build();

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
