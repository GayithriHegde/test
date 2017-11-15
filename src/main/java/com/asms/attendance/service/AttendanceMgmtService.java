/**
mallikarjun.guranna
Oct 6, 2017
*/
package com.asms.attendance.service;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.asms.Exception.AsmsException;
import com.asms.Exception.ExceptionHandler;
import com.asms.attendance.dao.AttendanceDao;
import com.asms.attendance.helper.ValidateAttendance;
import com.asms.attendance.request.AttendRequest;
import com.asms.attendance.request.StaffAttendanceDetails;
import com.asms.attendance.request.StudentAttendanceDetails;
import com.asms.attendance.response.GetAttendanceResponse;
import com.asms.common.helper.AsmsHelper;
import com.asms.common.helper.Constants;
import com.asms.common.response.FailureResponse;
import com.asms.common.service.BaseService;
import com.asms.usermgmt.auth.PrivilegesManager;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.helper.PrincipalUser;
import com.asms.usermgmt.request.UserBasicDetails;
import com.asms.usermgmt.response.GetUserResponse;

/*
 * AttendanceMgmtService.java handles  attendance management activities like
 *  get list of student based on className and sectionName functionalities
 * 
 */
//this is the entry point of the AttendanceMgmtService Service

@Service
@Component
@Path("/attendance")
public class AttendanceMgmtService extends BaseService {
	
	@Autowired
	private AttendanceDao attendanceDao;

	@Autowired
	private ExceptionHandler exceptionHandler;
	
	@Autowired
	private PrivilegesManager privilegesManager;
	@Autowired
	private ValidateAttendance validateAttendance;
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(AttendanceMgmtService.class);

	@Path("/studentAttendance/create")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response addStudentAttendance(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			AttendRequest attendRequest, @QueryParam("tenantId") String tenant) {

		try
		{
		// get bundles for error messages
		HttpSession session = hRequest.getSession();
		User user = (User) session.getAttribute("ap_user");
		
		ResourceBundle messages = AsmsHelper.getMessageFromBundle();
		
		
		
		PrincipalUser pUser = privilegesManager.isPrivileged(user, Constants.academics_category_attendance,
				Constants.privileges.create_check.toString());
		if (pUser.isPrivileged()) {
			
			List<StudentAttendanceDetails> studentAttendanceDetails= (List<StudentAttendanceDetails>) attendRequest.getAttendanceDetails().getStudentAttendanceDetails();
			
			for(StudentAttendanceDetails oneStudentAttendanceDetails :  studentAttendanceDetails)
			{
			//validates
			validateAttendance.validateStudentAttendanceValues(oneStudentAttendanceDetails, messages, attendRequest.getRequestType());
			
			attendanceDao.insertStudentAttendanceDetails(oneStudentAttendanceDetails, tenant);
			//GetUserResponse getUserResponse = new GetUserResponse();
			}
			return Response.status(Status.OK).entity("Success Student Attendance updated").build();
			

			//List<StudentType> userObject = userMgmtDao.getAll();

			
		} else {
			FailureResponse failureResponse = new FailureResponse();
			failureResponse.setCode(Integer.parseInt(messages.getString("NOT_AUTHORIZED_CODE")));
			failureResponse.setErrorDescription(messages.getString("NOT_AUTHORIZED"));
			return Response.status(200).entity(failureResponse).build();
		} 
		}catch (AsmsException ex) {
			// construct failure response
			FailureResponse failureResponse = new FailureResponse(ex);
			return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
		}

	
		
		
		
				
	}
	
	
	//this method level api inserts attendance values into db
	@Path("/staffAttendance/create")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response addStaffAttendance(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			AttendRequest attendRequest, @QueryParam("tenantId") String tenant) {
		try
		{
		// get bundles for error messages
		HttpSession session = hRequest.getSession();
		User user = (User) session.getAttribute("ap_user");
		
		ResourceBundle messages = AsmsHelper.getMessageFromBundle();
		
		
		
		PrincipalUser pUser = privilegesManager.isPrivileged(user, Constants.academics_category_attendance,
				Constants.privileges.create_check.toString());
		if (pUser.isPrivileged()) {
			
			
			List<StaffAttendanceDetails> studentAttendanceDetailsList = attendRequest.getAttendanceDetails().getStaffAttendanceDetails();
			for(StaffAttendanceDetails oneStaffAttendanceDetails: studentAttendanceDetailsList)
			{
				
			//validates
			validateAttendance.validateStaffAttendanceValues(oneStaffAttendanceDetails, messages, attendRequest.getRequestType());
			
			attendanceDao.insertStaffAttendanceDetails(oneStaffAttendanceDetails, tenant);
			//GetUserResponse getUserResponse = new GetUserResponse();
			}
			return Response.status(Status.OK).entity("Success").build();
			

			//List<StudentType> userObject = userMgmtDao.getAll();

			
		} else {
			FailureResponse failureResponse = new FailureResponse();
			failureResponse.setCode(Integer.parseInt(messages.getString("NOT_AUTHORIZED_CODE")));
			failureResponse.setErrorDescription(messages.getString("NOT_AUTHORIZED"));
			return Response.status(200).entity(failureResponse).build();
		} 
		}catch (AsmsException ex) {
			// construct failure response
			FailureResponse failureResponse = new FailureResponse(ex);
			return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
		}	
		
		
		
	}
	
	//this method level api searches list of students based on classname and section name
	@Path("/student/search")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getStudents(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("className") String className, @QueryParam("sectionName") String sectionName,
			@QueryParam("tenantId") String tenant) {
		try {

			
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			
			if(className == null || className.isEmpty() == true) {
				throw exceptionHandler.constructAsmsException(messages.getString("CLASSNAME_NULL_CODE"),
						messages.getString("CLASSNAME_NULL_MSG"));
			}
			if(sectionName == null || sectionName.isEmpty() == true) {
				throw exceptionHandler.constructAsmsException(messages.getString("SECTION_NULL_CODE"),
						messages.getString("SECTION_NULL_MSG"));
			}
			
			PrincipalUser pUser = privilegesManager.isPrivileged(user, Constants.academics_category_attendance,
					Constants.privileges.retrieve_check.toString());
			if (pUser.isPrivileged()) {
				
				
			
				
				List<UserBasicDetails> list = attendanceDao.search(className, sectionName, tenant);
				GetUserResponse getUserResponse = new GetUserResponse();
				getUserResponse.setUserBasicDetails(list);
				return Response.status(Status.OK).entity(getUserResponse.getUserBasicDetails()).build();
				

				//List<StudentType> userObject = userMgmtDao.getAll();

				
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
	
	//this method level api gives list of staff members name
	@Path("/staff/search")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getStaff(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("role") String roleName,
			@QueryParam("tenantId") String tenant) {
		try {

			
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			
			if(roleName == null || roleName.isEmpty() == true) {
				throw exceptionHandler.constructAsmsException(messages.getString("ROLE_NAME_NULL_CODE"),
						messages.getString("ROLE_NAME_NULL_MSG"));
			}
			if(tenant == null || tenant.isEmpty() == true) {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_ID_NULL_CODE"),
						messages.getString("TENANT_ID_NULL_MSG"));
			}
			
			PrincipalUser pUser = privilegesManager.isPrivileged(user, Constants.academics_category_attendance,
					Constants.privileges.retrieve_check.toString());
			if (pUser.isPrivileged()) {
				
				
			
				
				List<UserBasicDetails> list = attendanceDao.getStaffMemberByRoleName(roleName, tenant);
				GetUserResponse getUserResponse = new GetUserResponse();
				getUserResponse.setUserBasicDetails(list);
				return Response.status(Status.OK).entity(getUserResponse.getUserBasicDetails()).build();
				

				//List<StudentType> userObject = userMgmtDao.getAll();

				
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
	//this method level api gives list of students attendance details for view only
	@Path("/student/view")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response viewStudents(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("className") String className, @QueryParam("sectionName") String sectionName,
			@QueryParam("tenantId") String tenant) {
		try {

			
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			
			if(className == null || className.isEmpty() == true) {
				throw exceptionHandler.constructAsmsException(messages.getString("CLASSNAME_NULL_CODE"),
						messages.getString("CLASSNAME_NULL_MSG"));
			}
			if(sectionName == null || sectionName.isEmpty() == true) {
				throw exceptionHandler.constructAsmsException(messages.getString("SECTION_NULL_CODE"),
						messages.getString("SECTION_NULL_MSG"));
			}
			
			PrincipalUser pUser = privilegesManager.isPrivileged(user, Constants.academics_category_attendance,
					Constants.privileges.retrieve_check.toString());
			if (pUser.isPrivileged()) {
				
				
			
				
				List<UserBasicDetails> list = attendanceDao.search(className, sectionName, tenant);
				GetUserResponse getUserResponse = new GetUserResponse();
				getUserResponse.setUserBasicDetails(list);
				return Response.status(Status.OK).entity(getUserResponse.getUserBasicDetails()).build();
				

				//List<StudentType> userObject = userMgmtDao.getAll();

				
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
	
	
	
	
	//this method level api gives list of staff attendance details for view only
	@Path("/staff/view")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response viewStaff(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("role") String roleName,
			@QueryParam("tenantId") String tenant) {
		try {

			
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			
			if(roleName == null || roleName.isEmpty() == true) {
				throw exceptionHandler.constructAsmsException(messages.getString("ROLE_NAME_NULL_CODE"),
						messages.getString("ROLE_NAME_NULL_MSG"));
			}
			if(tenant == null || tenant.isEmpty() == true) {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_ID_NULL_CODE"),
						messages.getString("TENANT_ID_NULL_MSG"));
			}
			
			PrincipalUser pUser = privilegesManager.isPrivileged(user, Constants.academics_category_attendance,
					Constants.privileges.retrieve_check.toString());
			if (pUser.isPrivileged()) {
				
				
			
				
				List<UserBasicDetails> list = attendanceDao.getStaffMemberByRoleName(roleName, tenant);
				GetUserResponse getUserResponse = new GetUserResponse();
				getUserResponse.setUserBasicDetails(list);
				return Response.status(Status.OK).entity(getUserResponse.getUserBasicDetails()).build();
				

				//List<StudentType> userObject = userMgmtDao.getAll();

				
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
	
	//get single student attendance record based on studentId
	@Path("/student/get")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getOneStudentAttendance(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("studentId") String studentId,
			@QueryParam("tenantId") String tenant) {
		try {

			
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			
			if(studentId == null || studentId.isEmpty() == true) {
				throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_ID_NULL_CODE"),
						messages.getString("STUDENT_ID_NULL_MSG"));
			}
			
			
			PrincipalUser pUser = privilegesManager.isPrivileged(user, Constants.academics_category_attendance,
					Constants.privileges.retrieve_check.toString());
			if (pUser.isPrivileged()) {
				
				
			
				
				List<StudentAttendanceDetails> studentAttendanceDetailsList = attendanceDao.getAttendanceOfStudent(studentId, tenant);
				GetAttendanceResponse getAttendanceResponse = new GetAttendanceResponse();
				getAttendanceResponse.getStudentAttendance();
				return Response.status(Status.OK).entity(studentAttendanceDetailsList).build();
				

				//List<StudentType> userObject = userMgmtDao.getAll();

				
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
	//get single satff attendance record based on staffId
		@Path("/staff/get")
		@GET
		@Consumes("application/json")
		@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
		public Response getOneStaffAttendance(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
				@QueryParam("staffId") String staffId,
				@QueryParam("tenantId") String tenant) {
			try {

				
				// get bundles for error messages
				HttpSession session = hRequest.getSession();
				User user = (User) session.getAttribute("ap_user");
				
				ResourceBundle messages = AsmsHelper.getMessageFromBundle();
				
				if(staffId == null || staffId.isEmpty() == true) {
					throw exceptionHandler.constructAsmsException(messages.getString("STAFF_ID_NULL_CODE"),
							messages.getString("STAFF_ID_NULL_MSG"));
				}
				if(tenant == null || tenant.isEmpty() == true) {
					throw exceptionHandler.constructAsmsException(messages.getString("TENANT_ID_NULL_CODE"),
							messages.getString("TENANT_ID_NULL_MSG"));
				}
				
				PrincipalUser pUser = privilegesManager.isPrivileged(user, Constants.academics_category_attendance,
						Constants.privileges.retrieve_check.toString());
				if (pUser.isPrivileged()) {
					
					
				
					
					List<StaffAttendanceDetails> staffAttendanceDetailsList = attendanceDao.getAttendanceOfStaff(staffId, tenant);
					GetAttendanceResponse getAttendanceResponse = new GetAttendanceResponse();
					getAttendanceResponse.getStaffAttendanceList();
					return Response.status(Status.OK).entity(staffAttendanceDetailsList).build();
					//List<StudentType> userObject = userMgmtDao.getAll();

					
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
	//update attendance section goes here
	
	@Path("/studentAttendance/update")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response updateStudentAttendance(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			AttendRequest attendRequest, @QueryParam("tenantId") String tenant) {

		try
		{
		// get bundles for error messages
		HttpSession session = hRequest.getSession();
		User user = (User) session.getAttribute("ap_user");
		
		ResourceBundle messages = AsmsHelper.getMessageFromBundle();
		
		
		
		PrincipalUser pUser = privilegesManager.isPrivileged(user, Constants.academics_category_attendance,
				Constants.privileges.update_check.toString());
		if (pUser.isPrivileged()) {
			
			//List<StudentAttendanceDetails> studentAttendanceDetails= (List<StudentAttendanceDetails>) attendRequest.getAttendanceDetails().getStudentAttendanceDetails();
			
			
			
			//attendanceDao.updateStudentAttendanceDetails(studentAttendanceDetails, tenant);
			//GetUserResponse getUserResponse = new GetUserResponse();

			List<StudentAttendanceDetails> studentAttendanceDetailsList = attendRequest.getAttendanceDetails().getStudentAttendanceDetails();
			for(StudentAttendanceDetails oneStudentAttendanceDetails: studentAttendanceDetailsList)
			{
				
			//validates
			validateAttendance.validateStudentAttendanceValues(oneStudentAttendanceDetails, messages, attendRequest.getRequestType());
			
			attendanceDao.insertStudentAttendanceDetails(oneStudentAttendanceDetails, tenant);
			//GetUserResponse getUserResponse = new GetUserResponse();
			}
			return Response.status(Status.OK).entity("Success Student Attendance updated").build();
			

			//List<StudentType> userObject = userMgmtDao.getAll();

			
		} else {
			FailureResponse failureResponse = new FailureResponse();
			failureResponse.setCode(Integer.parseInt(messages.getString("NOT_AUTHORIZED_CODE")));
			failureResponse.setErrorDescription(messages.getString("NOT_AUTHORIZED"));
			return Response.status(200).entity(failureResponse).build();
		} 
		}catch (AsmsException ex) {
			// construct failure response
			FailureResponse failureResponse = new FailureResponse(ex);
			return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
		}

	
		
		
		
				
	}
	
	
	
	@Path("/staffAttendance/update")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response updateStaffAttendance(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			AttendRequest attendRequest, @QueryParam("tenantId") String tenant) {
		try
		{
		// get bundles for error messages
		HttpSession session = hRequest.getSession();
		User user = (User) session.getAttribute("ap_user");
		
		ResourceBundle messages = AsmsHelper.getMessageFromBundle();
		
		
		
		PrincipalUser pUser = privilegesManager.isPrivileged(user, Constants.academics_category_attendance,
				Constants.privileges.update_check.toString());
		if (pUser.isPrivileged()) {
			
			
			List<StaffAttendanceDetails> staffAttendanceDetailsList = attendRequest.getAttendanceDetails().getStaffAttendanceDetails();
			for(StaffAttendanceDetails oneStaffAttendanceDetails: staffAttendanceDetailsList)
			{
				
			//validates
			validateAttendance.validateStaffAttendanceValues(oneStaffAttendanceDetails, messages, attendRequest.getRequestType());
			
			attendanceDao.insertStaffAttendanceDetails(oneStaffAttendanceDetails, tenant);
			//GetUserResponse getUserResponse = new GetUserResponse();
			}
			return Response.status(Status.OK).entity("Success").build();
			

			//List<StudentType> userObject = userMgmtDao.getAll();

			
		} else {
			FailureResponse failureResponse = new FailureResponse();
			failureResponse.setCode(Integer.parseInt(messages.getString("NOT_AUTHORIZED_CODE")));
			failureResponse.setErrorDescription(messages.getString("NOT_AUTHORIZED"));
			return Response.status(200).entity(failureResponse).build();
		} 
		}catch (AsmsException ex) {
			// construct failure response
			FailureResponse failureResponse = new FailureResponse(ex);
			return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
		}	
		
		
		
	}
	
	

}
