package com.asms.schoolmgmt.service;

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
//import com.asms.Exception.ExceptionHandler;
import com.asms.common.helper.AsmsHelper;
import com.asms.common.helper.Constants;
import com.asms.common.response.FailureResponse;
import com.asms.common.response.SuccessResponse;
import com.asms.common.service.BaseService;
import com.asms.multitenancy.dao.MultitenancyDao;
import com.asms.multitenancy.entity.SuperAdmin;
import com.asms.schoolmgmt.dao.SchoolMgmtDao;
import com.asms.schoolmgmt.entity.AcademicYear;
import com.asms.schoolmgmt.entity.AdditionalSubjects;
import com.asms.schoolmgmt.entity.Class;
import com.asms.schoolmgmt.entity.ClassSubjects;
import com.asms.schoolmgmt.entity.Section;
import com.asms.schoolmgmt.entity.SetupSchoolDetails;
import com.asms.schoolmgmt.helper.SchoolValidator;
import com.asms.schoolmgmt.request.GroupDetails;
import com.asms.schoolmgmt.request.SchoolDetails;
import com.asms.schoolmgmt.request.SubjectDetails;
import com.asms.schoolmgmt.request.TeacherDetails;
import com.asms.schoolmgmt.request.TimeTableDetails;
import com.asms.schoolmgmt.request.TimeTableOnchangeDetails;
import com.asms.schoolmgmt.request.UserRequest;
import com.asms.schoolmgmt.response.SchoolSuccessResponse;
import com.asms.usermgmt.auth.PrivilegesManager;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.helper.PrincipalUser;


import com.asms.usermgmt.response.GetUserResponse;
import com.asms.usermgmt.response.RegistrationResponse;

/*
 * SchoolMgmtService.java handles school registration, update , get and delete 
 * functionalities
 */

@Service
@Component
@Path("/school")
public class SchoolMgmtService extends BaseService {

	@Autowired
	private SchoolMgmtDao schoolMgmtDao;

	@Autowired
	private PrivilegesManager privilegesManager;

	@Autowired
	private SchoolValidator schoolValidator;

	@Autowired
	private MultitenancyDao multitenancyDao;

	@Resource(name = "asmsdbProperties")
	private Properties dbProperties;

//	@Autowired
//	private ExceptionHandler exceptionHandler;

	//private static final Logger logger = LoggerFactory.getLogger(SchoolMgmtService.class);

	/*
	 * api : /school/names request type :GET
	 * 
	 * Method : getNamesOfBoard -> This method is used for Drop Down. Input:No
	 * input Output: Response object *
	 * 
	 * 
	 */

	@Path("/boards")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getNamesOfBoard(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse) {

		try {
		//	FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
		

				List<String> boardObject = schoolMgmtDao.getNames();

				GetUserResponse getUserResponse = new GetUserResponse();
				getUserResponse.setBoardNames(boardObject);
				return Response.status(Status.OK).entity(getUserResponse).build();

			

		} catch (AsmsException ex) {
			// construct failure response
			FailureResponse failureResponse = new FailureResponse(ex);
			return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
		}

	}

	// school Registration goes here
	// ----------------------------------------

	@Path("/register")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response register(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			UserRequest userRequest) {
		RegistrationResponse rReponse = new RegistrationResponse();
		ResourceBundle messages;
		try {
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			// validate request
			schoolValidator.validateSchoolDetailsRequest(userRequest, messages, "create");
			// validate user details
			// validator.validateUserDetails(userRequest, messages);
			HttpSession session = hRequest.getSession();
			Object userObject = session.getAttribute("ap_user");
			User user = (User)userObject;
			if (null != user && user.getRoleObject().getRoleName().equalsIgnoreCase(Constants.role_super_admin)) {

				// create schema
				String schema = multitenancyDao.createTenantId(userRequest.getSchoolDetails().getRegistrationCode(),
						userRequest.getSchoolDetails().getName());
				boolean result = multitenancyDao.createSchema(schema);
				
					if (result) {
						SchoolDetails school = userRequest.getSchoolDetails();
						schoolMgmtDao.createSchool(school, schema);
						return Response.status(Status.OK).entity(rReponse).build();
					}
				
				 else {
					FailureResponse failureResponse = new FailureResponse();
					failureResponse.setCode(Integer.parseInt(messages.getString("NOT_AUTHORIZED_CODE")));
					failureResponse.setErrorDescription(messages.getString("NOT_AUTHORIZED"));
					return Response.status(200).entity(failureResponse).build();
				
		
			        }
			}
					 else {
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

	// setup school code goes here
	// ------------------------------------
	@Path("/setup")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response setupSchool(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			UserRequest userRequest, @QueryParam("tenantId") String tenant) {
		RegistrationResponse rReponse = new RegistrationResponse();
		ResourceBundle messages;
		try {
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			// validate request
			schoolValidator.validateSetupSchoolRequest(userRequest, messages, "create");
			// validate user details
			// validator.validateUserDetails(userRequest, messages);
			HttpSession session = hRequest.getSession();
			Object user = session.getAttribute("ap_user");

			PrincipalUser pUser = privilegesManager.isPrivileged((User) user, Constants.admin_category_setup,
					Constants.privileges.create_check.toString());
			if (pUser.isPrivileged()) {
				SetupSchoolDetails setupSchoolDetails = userRequest.getSetupSchoolDetails();
				schoolMgmtDao.setupSchool(setupSchoolDetails, tenant);
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

	/*
	 * api : /school/classes request type :GET
	 * 
	 * Method : getNamesOfBoard -> This method is used for Drop Down.
	 * Input:tenantId input Output: Response object *
	 * 
	 * 
	 */
	@Path("/classes")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getClass(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("tenantId") String tenant) {

		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");

			if (null != user) {

				List<Class> class1 = schoolMgmtDao.getClasses(tenant);

				GetUserResponse getUserResponse = new GetUserResponse();
				getUserResponse.setClasses(class1);
				return Response.status(Status.OK).entity(getUserResponse).build();

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
	 * api : /school/subjects request type :GET
	 * 
	 * Method : getSubjects -> This method is used for Drop Down. Input:boardId
	 * input Output: Response object *
	 * 
	 * 
	 */
	@Path("/subjects")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getSubjects(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("id") int boardId) {
		ResourceBundle messages;
		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			if (boardId == 0) {
				failureResponse.setCode(Integer.parseInt(messages.getString("USERID_NULL_CODE")));
				failureResponse.setErrorDescription(messages.getString("USERID_NULL_MSG"));
				return Response.status(200).entity(failureResponse).build();

			}

			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");

			if (user != null) {
				List<String> list = schoolMgmtDao.getSujects(boardId);
				SchoolSuccessResponse countrySuccessResponse = new SchoolSuccessResponse();
				countrySuccessResponse.setSubjectNames(list);
				return Response.status(Status.OK).entity(countrySuccessResponse).build();

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
	 * api : /school/classSubjects request type :GET
	 * 
	 * Method : getClassSubjectsByClassId -> This method is used for Drop Down.
	 * Input:classId input Output: Response object *
	 * 
	 * 
	 */
	@Path("/classSubjects")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getClassSubjectsByClassId(@Context HttpServletRequest hRequest,
			@Context HttpServletResponse hResponse, @QueryParam("classId") int classId,
			@QueryParam("tenantId") String tenant) {
		ResourceBundle messages;
		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			if (classId == 0) {
				failureResponse.setCode(Integer.parseInt(messages.getString("USERID_NULL_CODE")));
				failureResponse.setErrorDescription(messages.getString("USERID_NULL_MSG"));
				return Response.status(200).entity(failureResponse).build();

			}

			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");

			if (user != null) {
				List<String> list = schoolMgmtDao.getClassSujects(classId, tenant);
				SchoolSuccessResponse countrySuccessResponse = new SchoolSuccessResponse();
				countrySuccessResponse.setClassNames(list);
				return Response.status(Status.OK).entity(countrySuccessResponse).build();

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
	 * api : /school/sections request type :GET
	 * 
	 * Method : getSections -> This method is used for Drop Down. Input:
	 * tenantId input Output: Response object *
	 * 
	 * 
	 */

	@Path("/sections")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getSections(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("tenantId") String tenant) {

		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");

			if (null != user) {

				List<Section> sections = schoolMgmtDao.getSections(tenant);

				SchoolSuccessResponse schoolSuccessResponse = new SchoolSuccessResponse();
				schoolSuccessResponse.setSections(sections);
				return Response.status(Status.OK).entity(schoolSuccessResponse).build();

			} else {
				return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
			}

		} catch (AsmsException ex) {
			// construct failure response
			FailureResponse failureResponse = new FailureResponse(ex);
			return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
		}

	}

	// grouping classes code goes here
	// ------------------------------------
	@Path("/groups/create")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response createGroups(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			List<GroupDetails> details, @QueryParam("tenantId") String tenant) {
		RegistrationResponse rReponse = new RegistrationResponse();
		ResourceBundle messages;
		try {
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			// validate request
			schoolValidator.validateCreateGroupsRequest(details, messages, "create");
			// validate user details
			// validator.validateUserDetails(userRequest, messages);
			HttpSession session = hRequest.getSession();
			Object user = session.getAttribute("ap_user");

			PrincipalUser pUser = privilegesManager.isPrivileged((User) user, Constants.admin_category_setup,
					Constants.privileges.create_check.toString());
			if (pUser.isPrivileged()) {

				schoolMgmtDao.createGroups(details, tenant);
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

	@Path("/schema/update")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response updateSchema(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("schema") String schema) {
		RegistrationResponse rReponse = new RegistrationResponse();
		ResourceBundle messages;
		try {
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();

			HttpSession session = hRequest.getSession();
			Object user = session.getAttribute("ap_user");
			if (user instanceof SuperAdmin) {

				// create schema

				boolean result = multitenancyDao.updateSchema(schema);
				if (result) {

					return Response.status(Status.OK).entity(rReponse).build();
				} else {
					FailureResponse failureResponse = new FailureResponse();
					failureResponse.setCode(Integer.parseInt(messages.getString("NOT_AUTHORIZED_CODE")));
					failureResponse.setErrorDescription(messages.getString("NOT_AUTHORIZED"));
					return Response.status(200).entity(failureResponse).build();
				}
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

	// setup copy school code goes here
	// ------------------------------------
	@Path("/setup/copy")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response setupSchoolCopy(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("academicyr") String academicyear, @QueryParam("tenantId") String tenantId) {
		ResourceBundle messages;
		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();

			HttpSession session = hRequest.getSession();
			Object user = session.getAttribute("ap_user");

			PrincipalUser pUser = privilegesManager.isPrivileged((User) user, Constants.admin_category_setup,
					Constants.privileges.create_check.toString());
			if (pUser.isPrivileged()) {

				schoolValidator.validateSchoolSetupCopyRequest(tenantId, academicyear, messages);
				AcademicYear academicYear = new AcademicYear();
				academicYear.setAcademicYearFromTo(academicyear);

				schoolMgmtDao.setupSchoolCopy(academicyear, tenantId);
				SuccessResponse successResponse = new SuccessResponse();
				successResponse.getStatus();
				successResponse.getCode();
				return Response.status(Status.OK).entity(successResponse).build();

			} else {
				return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
			}
		} catch (AsmsException ex) {
			// construct failure response
			FailureResponse failureResponse = new FailureResponse(ex);
			return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
		}
	}

	@Path("/ClassSubjectBySection")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getsubjects(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("sectionName") String section, @QueryParam("className") String name,
			@QueryParam("tenantId") String tenant) {

		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");

			if (null != user) {
				List<ClassSubjects> sectionName = schoolMgmtDao.getSubjectByName(name, section, tenant);

				GetUserResponse getUserResponse = new GetUserResponse();
				getUserResponse.setClassSubjects(sectionName);
				return Response.status(Status.OK).entity(getUserResponse).build();

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
	 * api : /school/timetable/details request type :GET
	 * 
	 * Method : getTimeTableDetails -> This method is used for generating table.
	 * Input:classname, sectionname,tenantId input Output: Response object *
	 * 
	 * 
	 */
	@Path("/timetable/details")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getTimeTableDetails(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("academicYear") String academicYear, @QueryParam("className") String className,
			@QueryParam("sectionName") String sectionName, @QueryParam("tenantId") String tenant) {

		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");
			PrincipalUser pUser = privilegesManager.isPrivileged((User) user, Constants.academics_category_timeTable,
					Constants.privileges.create_check.toString());

			if (pUser.isPrivileged()) {

				TimeTableDetails details = schoolMgmtDao.getTimeTableDetails(academicYear, className, sectionName,
						tenant);

				SchoolSuccessResponse schoolSuccessResponse = new SchoolSuccessResponse();
				schoolSuccessResponse.setTimeTableDetails(details);
				return Response.status(Status.OK).entity(schoolSuccessResponse).build();

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
	 * this method adds the selected teacher and subject to the timetable for
	 * the given class, section and time on that day
	 * 
	 * 
	 */
	// ------------------------------------
	@Path("/add/details/timetable")
	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response addTimeTableDetails(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			TimeTableOnchangeDetails details, @QueryParam("tenantId") String tenant) {
		RegistrationResponse rReponse = new RegistrationResponse();
		ResourceBundle messages;
		try {
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			// validate request
			schoolValidator.validateTimeTableOnchangeDetails(details, messages);
			// validate user details
			// validator.validateUserDetails(userRequest, messages);
			HttpSession session = hRequest.getSession();
			Object user = session.getAttribute("ap_user");

			PrincipalUser pUser = privilegesManager.isPrivileged((User) user, Constants.academics_category_timeTable,
					Constants.privileges.create_check.toString());
			if (pUser.isPrivileged()) {

				schoolMgmtDao.TimeTableOnChange(details, tenant);
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

	/*
	 * this method adds returns the available teachers to the timetable for the
	 * given class, section and time on that day
	 * 
	 * 
	 */
	// ------------------------------------
	@Path("/onchange/getteachers")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response addTimeTableDetails(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("timeFrom") String timeFrom, @QueryParam("timeTo") String timeTo, @QueryParam("day") String day,
			@QueryParam("class") String className, @QueryParam("section") String sectionName,
			@QueryParam("tenantId") String tenant) {
		SchoolSuccessResponse schoolSuccessResponse = new SchoolSuccessResponse();
		ResourceBundle messages;
		try {
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			// validate request
			schoolValidator.validateTimeTableOnchangeDetails(timeFrom, timeTo, day, className, sectionName, messages);
			// validate user details
			// validator.validateUserDetails(userRequest, messages);
			HttpSession session = hRequest.getSession();
			Object user = session.getAttribute("ap_user");

			PrincipalUser pUser = privilegesManager.isPrivileged((User) user, Constants.academics_category_timeTable,
					Constants.privileges.create_check.toString());
			if (pUser.isPrivileged()) {

				List<TeacherDetails> teachers = schoolMgmtDao.getTeachersOnChange(timeFrom, timeTo, day, className,
						sectionName, tenant);
				schoolSuccessResponse.setTeachers(teachers);
				return Response.status(Status.OK).entity(schoolSuccessResponse).build();
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
	@Path("/AdditionalSubjects")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getsubject(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("sectionName") String section,@QueryParam("className")String name,@QueryParam("tenantId") String tenant) {
		ResourceBundle messages;
		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			// validate request
			schoolValidator.validateAdditionalSubjectsRequest( name, section, messages);
			// validate user details
			// validator.validateUserDetails(userRequest, messages);
			HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");

			if (null != user) {
				 List<AdditionalSubjects> additionalsubjectsName= schoolMgmtDao.getAdditionalSubjects(name, section ,tenant);

				GetUserResponse getUserResponse = new GetUserResponse();
				getUserResponse.setAdditionalSubjects(additionalsubjectsName);
				return Response.status(Status.OK).entity(getUserResponse).build();

			} else {
				return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
			}

		} catch (AsmsException ex) {
			// construct failure response
			FailureResponse failureResponse = new FailureResponse(ex);
			return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
		}
	}
	@Path("/subjectsAndAdditionalsubjects")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getsubjectsAndAdditionalsubjects(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse,
			@QueryParam("sectionName") String section,@QueryParam("className")String name,@QueryParam("tenantId") String tenant) {

		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
		//	if (null != name && null != section) 
			
				
				HttpSession session = hRequest.getSession();
			User user = (User) session.getAttribute("ap_user");

			if (null != user && null != name && null != section) {
				 List<SubjectDetails> SubjectDetailsName= schoolMgmtDao.getsubjectsAndAdditionalsubjects(name, section ,tenant);

				GetUserResponse getUserResponse = new GetUserResponse();
				getUserResponse.setSubjectDetails(SubjectDetailsName);
			
				return Response.status(Status.OK).entity(getUserResponse).build();

				} else {
				return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
			}

			
		
		}catch (AsmsException ex) {
			// construct failure response
			FailureResponse failureResponse = new FailureResponse(ex);
			return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
		}
	}
	


}
