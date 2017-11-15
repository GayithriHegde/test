package com.asms.examination.service;

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
import com.asms.examination.dao.ExaminationDao;
import com.asms.examination.entity.ExaminationDetails;
import com.asms.examination.helper.ExamValidator;
import com.asms.examination.request.ExamRequestDetails;
import com.asms.examination.request.ExaminationRequestDetails;
import com.asms.examination.request.MarksDetails;
import com.asms.examination.request.MarksMasterDetails;
import com.asms.examination.request.UserRequest;
import com.asms.examination.response.ExamSuccessResponse;
import com.asms.usermgmt.auth.PrivilegesManager;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.helper.PrincipalUser;


/* Service class name: ExaminationService 
 * purpose : To manage examination module.
 * This class is the Web service end point.
 * The root path for ExaminationService is  API is /examination
 */
@Service
@Component
@Path("/examination")
public class ExaminationService extends BaseService{
	
	
	@Autowired
	private ExamValidator examValidator;

	
	
	@Autowired
	private PrivilegesManager privilegesManager;


	
	
	
	@Autowired
	private ExaminationDao examinationDao;

	
	@Resource(name = "asmsdbProperties")
	private Properties dbProperties;
	
	
	/*
	 * api : /examination/create/exam request type :POST
	 * 
	 * Method : createExam -> This method does the creation of Examination.
	 *  Input : UserRequest object output: Response object *
	 * 
	 * 
	 */
	@Path("/create/exam")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response createExam(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			UserRequest userRequest, @QueryParam("tenantId") String tenant)
	{
		
		ResourceBundle messages;
		try {
			// get bundles for error messages
		   messages = AsmsHelper.getMessageFromBundle();
		   
			// validate request
			examValidator.validateExamDetails(userRequest, messages, "create");
			
			
			
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			
			PrincipalUser pUser = privilegesManager.isPrivileged((User) user, Constants.academics_category_mrksSheetAndReportCard,
					Constants.privileges.create_check.toString());
			if (pUser.isPrivileged()) {
			   ExamRequestDetails examRequestDetails = userRequest.getExamRequestDetails();
			   ExamSuccessResponse examSuccessResponse = new ExamSuccessResponse();
			   examinationDao.insertExam(examRequestDetails, tenant);
			   
				return Response.status(Status.OK).entity(examSuccessResponse).build();
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
	 * api : /examination/create/examinationDetails request type :POST
	 * 
	 * Method : createExaminationDetails -> This method does the creation of ExaminationDetails.
	 *  Input : UserRequest object, examId. output: Response object *
	 * 
	 * 
	 */
	@Path("/create/examinationDetails")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response createExaminationDetails(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			UserRequest userRequest,@QueryParam("examId")int examinationId, @QueryParam("tenantId") String tenant)
	{
		//RegistrationResponse rReponse = new RegistrationResponse();
		ResourceBundle messages;
		try {
			// get bundles for error messages
		   messages = AsmsHelper.getMessageFromBundle();
		   
			// validate request
			examValidator.validateExaminationDetailsRequest(userRequest, messages, "create");
			
			
			
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			
			PrincipalUser pUser = privilegesManager.isPrivileged((User) user, Constants.academics_category_mrksSheetAndReportCard,
					Constants.privileges.create_check.toString());
			if (pUser.isPrivileged()) {
			  ExaminationRequestDetails examRequestDetails = userRequest.getExaminationRequestDetails();
			   ExamSuccessResponse examSuccessResponse = new ExamSuccessResponse();
			   examinationDao.insertExaminationDetails(examRequestDetails,examinationId,tenant);
			   
				return Response.status(Status.OK).entity(examSuccessResponse).build();
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
	 * api : /examination/exam/details request type :GET
	 * 
	 * Method : getExaminationDetails -> This method is used to get examinationDetails based on className.
	 *  Input : className, examId. output: Response object *
	 * 
	 * 
	 */
	@Path("/exam/details")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getExaminationDetails(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse
			, @QueryParam("className") String className, @QueryParam("tenantId") String tenant) {

		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			PrincipalUser pUser = privilegesManager.isPrivileged((User) user, Constants.academics_category_mrksSheetAndReportCard,
					Constants.privileges.retrieve_check.toString());

			if (pUser.isPrivileged()) {

				List<ExaminationDetails> examinationDetails = examinationDao.getExaminationDetails(className,tenant);

				ExamSuccessResponse examSuccessResponse = new ExamSuccessResponse();
				examSuccessResponse.setExaminationDetails(examinationDetails);
				
				return Response.status(Status.OK).entity(examSuccessResponse).build();

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
	 * api : /examination/examNames request type :GET
	 * 
	 * Method : getExaminationNames -> This method is used to get examinationNames based on className.
	 *  Input : className. output: Response object *
	 * 
	 * 
	 */
	@Path("/examNames")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getExaminationNames(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse
			, @QueryParam("className") String className, @QueryParam("tenantId") String tenant) {

		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			PrincipalUser pUser = privilegesManager.isPrivileged((User) user, Constants.academics_category_mrksSheetAndReportCard,
					Constants.privileges.retrieve_check.toString());

			if (pUser.isPrivileged()) {

				List<String> examinationNames = examinationDao.getExamNames(className, tenant);

				ExamSuccessResponse examSuccessResponse = new ExamSuccessResponse();
				examSuccessResponse.setExamNames(examinationNames);
				
				return Response.status(Status.OK).entity(examSuccessResponse).build();

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
	 * api : /examination/create/marksMaster request type :POST
	 * 
	 * Method : createMarksMaster -> This method does the creation of MarksMaster.
	 *  Input : UserRequest object output: Response object *
	 * 
	 * 
	 */
	@Path("/create/marksMaster")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response createMarksMaster(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			UserRequest userRequest, @QueryParam("tenantId") String tenant)
	{
		//RegistrationResponse rReponse = new RegistrationResponse();
		ResourceBundle messages;
		try {
			// get bundles for error messages
		   messages = AsmsHelper.getMessageFromBundle();
		   
			// validate request
			examValidator.validateMarksMasterRequest(userRequest, messages, "create");
			
			
			
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			
			PrincipalUser pUser = privilegesManager.isPrivileged((User) user, Constants.academics_category_mrksSheetAndReportCard,
					Constants.privileges.create_check.toString());
			if (pUser.isPrivileged()) {
			 MarksMasterDetails marksMasterDetails = userRequest.getMarksMasterDetails();
			   ExamSuccessResponse examSuccessResponse = new ExamSuccessResponse();
			   examinationDao.insertMarksMaster(marksMasterDetails, tenant);
			   
				return Response.status(Status.OK).entity(examSuccessResponse).build();
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
	 * api : /examination/create/marks request type :POST
	 * 
	 * Method : createMarks -> This method does the creation of Marks.
	 *  Input : UserRequest object,  marksMasterId. output: Response object *
	 * 
	 * 
	 */
	@Path("/create/marks")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response createMarks(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			UserRequest userRequest,@QueryParam("marksMasterId")int marksMasterId, @QueryParam("tenantId") String tenant)
	{
		//RegistrationResponse rReponse = new RegistrationResponse();
		ResourceBundle messages;
		try {
			// get bundles for error messages
		   messages = AsmsHelper.getMessageFromBundle();
		   
			// validate request
			examValidator.validateMarksRequest(userRequest, messages,"create");
			
			
			
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			
			PrincipalUser pUser = privilegesManager.isPrivileged((User) user, Constants.academics_category_mrksSheetAndReportCard,
					Constants.privileges.create_check.toString());
			if (pUser.isPrivileged()) {
			 MarksDetails marksDetails = userRequest.getMarksDetails();
			   ExamSuccessResponse examSuccessResponse = new ExamSuccessResponse();
			   examinationDao.insertMarks(marksDetails, marksMasterId,tenant);
			   
				return Response.status(Status.OK).entity(examSuccessResponse).build();
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
	 * api : /examination/pdf/exam_timeTable    request type :GET
	 * 
	 * Method : createPdf -> This method does the creation of pdf for exam Time Table.
	 *  Input : String  examId  . output: Response object *
	 * 
	 * 
	 */
	@Path("/pdf/exam_timeTable")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response createPdf(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse
			, @QueryParam("examId") String examId, @QueryParam("tenantId") String tenant) {
		
		
		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			PrincipalUser pUser = privilegesManager.isPrivileged((User) user, Constants.academics_category_mrksSheetAndReportCard,
					Constants.privileges.retrieve_check.toString());

			if (pUser.isPrivileged()) {
				
			    examinationDao.createPdf(examId, tenant);

				ExamSuccessResponse examSuccessResponse = new ExamSuccessResponse();
				
				return Response.status(Status.OK).entity(examSuccessResponse).build();

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
	 * api : /examination/excel/exam_timeTable    request type :GET
	 * 
	 * Method : createExcelSheet -> This method does the creation of excelSheet for exam Time Table.
	 *  Input : String  examId  . output: Response object *
	 * 
	 * 
	 */
	@Path("/excel/exam_timeTable")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response createExcelSheet(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse
			, @QueryParam("examId") String examId, @QueryParam("tenantId") String tenant) {
		
		
		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			PrincipalUser pUser = privilegesManager.isPrivileged((User) user, Constants.academics_category_mrksSheetAndReportCard,
					Constants.privileges.retrieve_check.toString());

			if (pUser.isPrivileged()) {
				
			    examinationDao.createExcel(examId, tenant);

				ExamSuccessResponse examSuccessResponse = new ExamSuccessResponse();
				
				return Response.status(Status.OK).entity(examSuccessResponse).build();

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
	 * api : /examination/pdf/marks_sheet    request type :GET
	 * 
	 * Method : createMarksPdf -> This method does the creation of pdf for marks sheet(Report Card).
	 *  Input : String   marksMasterId . output: Response object *
	 * 
	 * 
	 */
	@Path("/pdf/marks_sheet")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response createMarksPdf(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse
			, @QueryParam("marksMasterId") String marksMasterId, @QueryParam("tenantId") String tenant) {
		
		
		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			PrincipalUser pUser = privilegesManager.isPrivileged((User) user, Constants.academics_category_mrksSheetAndReportCard,
					Constants.privileges.retrieve_check.toString());

			if (pUser.isPrivileged()) {
				
			    examinationDao.createPdfForMarksSheet(marksMasterId, tenant);

				ExamSuccessResponse examSuccessResponse = new ExamSuccessResponse();
				
				return Response.status(Status.OK).entity(examSuccessResponse).build();

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
	 * api : /examination/excel/marks_sheet    request type :GET
	 * 
	 * Method : createExcelForReportCard -> This method does the creation of excelSheet for marks sheet(Report Card).
	 *  Input : String   marksMasterId . output: Response object *
	 * 
	 * 
	 */
	@Path("/excel/marks_sheet")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response createExcelForReportCard(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse
			, @QueryParam("marksMasterId") String marksMasterId, @QueryParam("tenantId") String tenant) {
		
		
		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			PrincipalUser pUser = privilegesManager.isPrivileged((User) user, Constants.academics_category_mrksSheetAndReportCard,
					Constants.privileges.retrieve_check.toString());

			if (pUser.isPrivileged()) {
				
			    examinationDao.createExcelForReportCard(marksMasterId, tenant);

				ExamSuccessResponse examSuccessResponse = new ExamSuccessResponse();
				
				return Response.status(Status.OK).entity(examSuccessResponse).build();

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
