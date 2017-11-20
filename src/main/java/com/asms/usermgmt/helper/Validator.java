package com.asms.usermgmt.helper;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.validator.routines.EmailValidator;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asms.Exception.AsmsException;
import com.asms.Exception.ExceptionHandler;
import com.asms.common.helper.Constants;
import com.asms.usermgmt.request.ChangePasswordDetails;
import com.asms.usermgmt.request.TeachingSubjectDetails;
import com.asms.usermgmt.request.UserDetails;
import com.asms.usermgmt.request.UserRequest;
import com.asms.usermgmt.request.management.ManagementDetails;
import com.asms.usermgmt.request.nonTeachingStaff.AddressDetails;
import com.asms.usermgmt.request.nonTeachingStaff.NonTeachingStaffDetails;
import com.asms.usermgmt.request.nonTeachingStaff.StaffDocumentsDetails;
import com.asms.usermgmt.request.nonTeachingStaff.StaffPreviousInformationDetails;
import com.asms.usermgmt.request.nonTeachingStaff.StaffStatutoryDetails;
import com.asms.usermgmt.request.student.ParentDetails;
import com.asms.usermgmt.request.student.StudentAddressDetails;
import com.asms.usermgmt.request.student.StudentDetails;
import com.asms.usermgmt.request.student.StudentDocumentDetails;
import com.asms.usermgmt.request.student.StudentPreviousDetails;
import com.asms.usermgmt.request.teachingStaff.TeachingStaffDetails;

/*
 * Validator.java does the validation of requests
 * 
 */

@Component
public class Validator {

	@Autowired
	private ExceptionHandler exceptionHandler;

	private static final Logger logger = LoggerFactory.getLogger(Validator.class);

	/*
	 * Method: validateRequest -> validates userRequest for null. Parameters ->
	 * UserRequest throws -> AsmsException
	 */

	public void validateRequest(UserRequest request, ResourceBundle messages, String type) throws AsmsException {

		if (null == request) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_NULL_CODE"),
					messages.getString("REQUEST_NULL"));

		}
		if (null == request.getRequestType() || request.getRequestType().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_TYPE_NULL_CODE"),
					messages.getString("REQUEST_TYPE_NULL"));
		}

		if (null == request.getUserRole() || request.getUserRole().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("NEW_USER_ROLE_NULL_CODE"),
					messages.getString("NEW_USER_ROLE_NULL"));

		}
		
	
		if (null == request.getUserDetails()) {
			throw exceptionHandler.constructAsmsException(messages.getString("USER_OBJECT_NULL_CODE"),
					messages.getString("USER_OBJECT_NULL"));
		}
		
//		if (null != request.getUserDetails().getEmail() || request.getUserDetails().getEmail().isEmpty()) {
//		throw exceptionHandler.constructAsmsException(messages.getString("USER_EMAIL_NULL_CODE"),
//				messages.getString("USER_EMAIL_NULL"));
//		}
//		
//		if (null != request.getUserDetails().getUserPassword() || request.getUserDetails().getUserPassword().isEmpty()) {
//		throw exceptionHandler.constructAsmsException(messages.getString("USER_PASSWORD_NULL_CODE"),
//				messages.getString("USER_PASSWORD_NULL_MSG"));
//		}
				
		if (type.equalsIgnoreCase("create")) {
			// email validation starts
			if (null == request.getUserDetails().getEmail() || request.getUserDetails().getEmail().trim().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("USER_EMAIL_NULL_CODE"),
						messages.getString("USER_EMAIL_NULL"));
			} else {
				EmailValidator emailValidator = EmailValidator.getInstance();
				if (!emailValidator.isValid(request.getUserDetails().getEmail())) {
					throw exceptionHandler.constructAsmsException(messages.getString("EMAIL_INVALID_CODE"),
							messages.getString("EMAIL_INVALID"));
				}
			}
		}
		// email validation ends
		// validate role
		/*
		 * if (!request.getUserRole().equalsIgnoreCase(Constants.role_student)
		 * || !request.getUserRole().equalsIgnoreCase(Constants.role_management)
		 * || !request.getUserRole().equalsIgnoreCase(Constants.
		 * role_non_teaching_staff) ||
		 * !request.getUserRole().equalsIgnoreCase(Constants.
		 * role_teaching_staff) ||
		 * !request.getUserRole().equalsIgnoreCase(Constants.role_principal)) {
		 * throw exceptionHandler.constructAsmsException(messages.getString(
		 * "ROLE_INVALID_CODE"), messages.getString("ROLE_INVALID"));
		 * 
		 * }
		 */

	}
	/*
	 * Method : validateUserDetails input: request,messages Call to validate
	 * code method based on role output :void
	 */

	public void validateUserDetails(UserRequest request, ResourceBundle messages) throws AsmsException {
		
		if (request.getUserRole().equalsIgnoreCase(Constants.role_student)) {
			validateStudentDetails(request, messages);
			
		} else if (request.getUserRole().equalsIgnoreCase(Constants.role_management)) {
			validateManagementDetails(request, messages);

		} else if (request.getUserRole().equalsIgnoreCase(Constants.role_teaching_staff)) {
			validateTeachingStaffDetails(request, messages);

		} else if (request.getUserRole().equalsIgnoreCase(Constants.role_non_teaching_staff)) {
			validateNonTeachingStaffDetails(request, messages);
		
		}else {
			logger.error("session id: " + MDC.get("sessionId") + "  " + "invalid role: " + request.getUserRole());
		}

	}
	
	
	public void validateStudentDetails(UserRequest request, ResourceBundle messages) throws AsmsException {
		// student validation starts
		StudentDetails studentDetails = request.getUserDetails().getStudentDetails();

		if (null == studentDetails) {
			throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_NULL_CODE"),
					messages.getString("STUDENT_DETAILS_NULL"));
		} else {
			if (null == studentDetails.getAdmissionNo() || studentDetails.getAdmissionNo().isEmpty()) {
				throw exceptionHandler.constructAsmsException(
						messages.getString("STUDENT_DETAILS_ADMISSION_NO_NULL_CODE"),
						messages.getString("STUDENT_DETAILS_ADMISSION_NO_NULL"));

			}
			if (null == studentDetails.getStudentType() || studentDetails.getStudentType().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_TYPE_NULL_CODE"),
						messages.getString("STUDENT_DETAILS_TYPE_NULL"));
			}

			if (null == studentDetails.getAdmissionDate()) {
				throw exceptionHandler.constructAsmsException(
						messages.getString("STUDENT_DETAILS_ADMISSION_DATE_NULL_CODE"),
						messages.getString("STUDENT_DETAILS_ADMISSION_DATE_NULL"));

			}

			if (null == studentDetails.getStudentClass() || studentDetails.getStudentClass().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_CLASS_NULL_CODE"),
						messages.getString("STUDENT_DETAILS_CLASS_NULL"));

			}
			if (null == studentDetails.getStudentSection() || studentDetails.getStudentSection().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_SECTION_NULL_CODE"),
						messages.getString("STUDENT_DETAILS_SECTION_NULL"));

			}
			if (null == studentDetails.getStudentFirstName() || studentDetails.getStudentFirstName().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_FIRSTNAME_NULL_CODE"),
						messages.getString("STUDENT_DETAILS_FIRSTNAME_NULL"));

			}
			if (null == studentDetails.getStudentLastName() || studentDetails.getStudentLastName().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_LASTNAME_NULL_CODE"),
						messages.getString("STUDENT_DETAILS_LASTNAME_NULL"));

			}

			if (null == studentDetails.getStudentDob()) {
				throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_DOB_NULL_CODE"),
						messages.getString("STUDENT_DETAILS_DOB_NULL"));

			}

			if (null == studentDetails.getStudentGender() || studentDetails.getStudentGender().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_GENDER_NULL_CODE"),
						messages.getString("STUDENT_DETAILS_GENDER_NULL"));

			}
			
			

			if (null == studentDetails.getStudentIdentificationMarks()
					|| studentDetails.getStudentIdentificationMarks().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_IMARK_NULL_CODE"),
						messages.getString("STUDENT_DETAILS_IMARK_NULL"));

			}
			if (studentDetails.getStudentAgeInYears() <= 0) {
				throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_AGE_INVALID_CODE"),
						messages.getString("STUDENT_DETAILS_AGE_INVALID"));

			}
			if (null == studentDetails.getStudentBirthplace() || studentDetails.getStudentBirthplace().isEmpty()) {
				throw exceptionHandler.constructAsmsException(
						messages.getString("STUDENT_DETAILS_BIRTHPLACE_NULL_CODE"),
						messages.getString("STUDENT_DETAILS_BIRTHPLACE_NULL"));

			}
			if (null == studentDetails.getStudentNationality() || studentDetails.getStudentNationality().isEmpty()) {
				throw exceptionHandler.constructAsmsException(
						messages.getString("STUDENT_DETAILS_NATIONALITY_NULL_CODE"),
						messages.getString("STUDENT_DETAILS_NATIONALITY_NULL"));

			}
			if (null == studentDetails.getStudentReligion() || studentDetails.getStudentReligion().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_RELIGION_NULL_CODE"),
						messages.getString("STUDENT_DETAILS_RELIGION_NULL"));

			}
			if (null == studentDetails.getStudentCasteCategory()
					|| studentDetails.getStudentCasteCategory().isEmpty()) {
				throw exceptionHandler.constructAsmsException(
						messages.getString("STUDENT_DETAILS_CASTE_CATEGORY_NULL_CODE"),
						messages.getString("STUDENT_DETAILS_CASTE_CATEGORY_NULL"));

			}
			if (null == studentDetails.getStudentSubCaste() || studentDetails.getStudentSubCaste().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_SUB_CASTE_NULL_CODE"),
						messages.getString("STUDENT_DETAILS_SUB_CASTE_NULL"));

			}
			if (null == studentDetails.getStudentMotherTongue() || studentDetails.getStudentMotherTongue().isEmpty()) {
				throw exceptionHandler.constructAsmsException(
						messages.getString("STUDENT_DETAILS_MOTHERTOUNGUE_NULL_CODE"),
						messages.getString("STUDENT_DETAILS_MOTHERTOUNGUE_NULL"));

			}
			if (null == studentDetails.getStudentPhoto() || studentDetails.getStudentPhoto().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_SECTION_NULL_CODE"),
						messages.getString("STUDENT_DETAILS_SECTION_NULL"));

			}
			
			if (null == studentDetails.getBloodGroup() || studentDetails.getBloodGroup().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_BLOODGROUP_NULL_CODE"),
						messages.getString("STUDENT_DETAILS_BLOODGROUP_NULL_MSG"));
			}

		}

	}

	// ManagementDetails validation starts
	public void validateManagementDetails(UserRequest request, ResourceBundle messages) throws AsmsException {

		ManagementDetails managementDetails = request.getUserDetails().getManagementDetails();

		if (null == managementDetails)
			throw exceptionHandler.constructAsmsException(messages.getString("MGMT_DETAILS_NULL_CODE"),
					messages.getString("MGMT_DETAILS_NULL_CODE"));

		if ((null == managementDetails.getMngmtFirstName()) || (managementDetails.getMngmtFirstName().isEmpty()))
			throw exceptionHandler.constructAsmsException(messages.getString("MGMT_DETAILS_MNGMTFIRSTNAME_NULL_CODE"),
					messages.getString("MGMT_DETAILS_MNGMTFIRSTNAME_NULL_MSG"));

		if ((null == managementDetails.getMngmtMiddleName()) || (managementDetails.getMngmtMiddleName().isEmpty()))
			throw exceptionHandler.constructAsmsException(messages.getString("MGMT_DETAILS_MNGMTMIDDLENAME_NULL_CODE"),
					messages.getString("MGMT_DETAILS_MNGMTMIDDLENAME_NULL_MSG"));

		if ((null == managementDetails.getMngmtLastName()) || (managementDetails.getMngmtLastName().isEmpty()))
			throw exceptionHandler.constructAsmsException(messages.getString("MGMT_DETAILS_MNGMTLASTNAME_NULL_CODE"),
					messages.getString("MGMT_DETAILS_MNGMTLASTNAME_NULL_MSG"));

		if (((null == managementDetails.getMngmtDesignation()) || managementDetails.getMngmtDesignation().isEmpty()))
			throw exceptionHandler.constructAsmsException(messages.getString("MGMT_DETAILS_MNGMTDESIGNATION_NULL_CODE"),
					messages.getString("MGMT_DETAILS_MNGMTDESIGNATION_NULL_MSG"));

		if ((null == managementDetails.getMngmtContactNo()) || (managementDetails.getMngmtContactNo().isEmpty()))
			throw exceptionHandler.constructAsmsException(messages.getString("MGMT_DETAILS_MNGMTCONTACTNO_NULL_CODE"),
					messages.getString("MGMT_DETAILS_MNGMTCONTACTNO_NULL_MSG"));
	}

	// TeachingStaffDetails validation starts
	public void validateTeachingStaffDetails(UserRequest request, ResourceBundle messages) throws AsmsException {
		TeachingStaffDetails teachingStaffDetails = request.getUserDetails().getTeachingStaffDetails();

		if (null == teachingStaffDetails)
			throw exceptionHandler.constructAsmsException(messages.getString("TEACHING_STAFF_DETAILS_NULL_CODE"),
					messages.getString("TEACHING_STAFF_DETAILS_NULL_MSG"));

		if ((null == teachingStaffDetails.getDesignation()) || (teachingStaffDetails.getDesignation().isEmpty()))
			throw exceptionHandler.constructAsmsException(messages.getString("TEACHING_STAFF_DESIGNATION_NULL_CODE"),
					messages.getString("TEACHING_STAFF_DESIGNATION_NULL_MSG"));

		if ((null == teachingStaffDetails.getFirstName()) || (teachingStaffDetails.getFirstName().isEmpty()))
			throw exceptionHandler.constructAsmsException(messages.getString("TEACHING_STAFF_FIRST_NAME_NULL_CODE"),
					messages.getString("TEACHING_STAFF_FIRST_NAME_NULL_MSG"));

		if ((null == teachingStaffDetails.getMiddleName()) || (teachingStaffDetails.getMiddleName().isEmpty()))
			throw exceptionHandler.constructAsmsException(messages.getString("TEACHING_STAFF_MIDDLE_NAME_NULL_CODE"),
					messages.getString("TEACHING_STAFF_NULL_MSG"));

		if (((null == teachingStaffDetails.getLastName()) || teachingStaffDetails.getLastName().isEmpty()))
			throw exceptionHandler.constructAsmsException(messages.getString("TEACHING_STAFF_LAST_NAME_NULL_CODE"),
					messages.getString("TEACHING_STAFF_LAST_NAME_NULL_MSG"));

		if ((null == teachingStaffDetails.getDateOfBirth())
				|| (teachingStaffDetails.getDateOfBirth().toString().isEmpty()))
			throw exceptionHandler.constructAsmsException(messages.getString("TEACHING_STAFF_DOB_NULL_CODE"),
					messages.getString("TEACHING_STAFF_DOB_NULL_MSG"));

		if ((null == teachingStaffDetails.getGender()) || (teachingStaffDetails.getGender().isEmpty()))
			throw exceptionHandler.constructAsmsException(messages.getString("TEACHING_STAFF_GENDER_NULL_CODE"),
					messages.getString("TEACHING_STAFF_GENDER_NULL_MSG"));

		if ((0 == teachingStaffDetails.getAgeInYears()))
			throw exceptionHandler.constructAsmsException(messages.getString("TEACHING_STAFF_AGE_IN_YEARS_NULL_CODE"),
					messages.getString("TEACHING_STAFF_AGE_IN_YEARS_NULL_MSG"));

		if (teachingStaffDetails.getContactNo() == 0)
			throw exceptionHandler.constructAsmsException(messages.getString("TEACHING_STAFF_CONTACT_NO_NULL_CODE"),
					messages.getString("TEACHING_STAFF_CONTACT_NO_NULL_MSG"));

		if ((null == teachingStaffDetails.getQualification()) || (teachingStaffDetails.getQualification().isEmpty()))
			throw exceptionHandler.constructAsmsException(messages.getString("TEACHING_STAFF_QUALIFICATION_NULL_CODE"),
					messages.getString("TEACHING_STAFF_QUALIFICATION_NULL_MSG"));

		if ((null == teachingStaffDetails.getReligion()) || (teachingStaffDetails.getReligion().isEmpty()))
			throw exceptionHandler.constructAsmsException(messages.getString("TEACHING_STAFF_RELIGION_NULL_CODE"),
					messages.getString("TEACHING_STAFF_RELIGION_NULL_MSG"));

		if ((null == teachingStaffDetails.getCasteCategory()) || (teachingStaffDetails.getCasteCategory().isEmpty()))
			throw exceptionHandler.constructAsmsException(messages.getString("TEACHING_STAFF_CASTE_CATEGORY_NULL_CODE"),
					messages.getString("TEACHING_STAFF_CASTE_CATEGORY_NULL_MSG"));

		
		if (null == teachingStaffDetails.getBloodGroup() || teachingStaffDetails.getBloodGroup().isEmpty())
			throw exceptionHandler.constructAsmsException(messages.getString("TEACHING_STAFF_BLOODGROUP_NULL_CODE"),
					messages.getString("TEACHING_STAFF_BLOODGROUP_NULL_MSG"));

		/*
		 * if ((null == teachingStaffDetails.getClassesHandled()) ||
		 * (teachingStaffDetails.getClassesHandled().isEmpty())) throw
		 * exceptionHandler.constructAsmsException(
		 * messages.getString("TEACHING_STAFF_CLASSES_HANDLED_NULL_CODE"),
		 * messages.getString("TEACHING_STAFF_CLASSES_HANDLED_NULL_MSG"));
		 * 
		 * if ((null == teachingStaffDetails.getSubjectsHandled()) ||
		 * (teachingStaffDetails.getSubjectsHandled().isEmpty())) throw
		 * exceptionHandler.constructAsmsException(
		 * messages.getString("TEACHING_STAFF_SUBJECTS_HANDLED_NULL_CODE"),
		 * messages.getString("TEACHING_STAFF_SUBJECTS_HANDLED_NULL_MSG"));
		 */

	

	/*	List<TeachingSubjectDetails> subjectDetailsList = teachingStaffDetails.getTeachingSubjectDetailsList();

		for (int i = 0; i < subjectDetailsList.size(); i++) {

			TeachingSubjectDetails subjectDetails = subjectDetailsList.get(i);

			if (null == subjectDetails)
				throw exceptionHandler.constructAsmsException(messages.getString("TEACHING_SUBJECTS_NULL_CODE"),
						messages.getString("TEACHING_SUBJECTS_NULL_MSG"));

			if (null == subjectDetails.getClassName() || subjectDetails.getClassName().isEmpty())
				throw exceptionHandler.constructAsmsException(messages.getString("CLASSNAME_NULL_CODE"),
						messages.getString("CLASSNAME_NULL_MSG"));

			if (null == subjectDetails.getSubject() || subjectDetails.getSubject().isEmpty())
				throw exceptionHandler.constructAsmsException(messages.getString("SUBJECT_NULL_CODE"),
						messages.getString("SUBJECT_NULL_MSG"));

			if (null == subjectDetails.getSectionName() || subjectDetails.getSectionName().isEmpty())
				throw exceptionHandler.constructAsmsException(messages.getString("SECTION_NULL_CODE"),
						messages.getString("SECTION_NULL_MSG"));
		}*/

	}

	// NonTeachingStaffDetails validation starts
	public void validateNonTeachingStaffDetails(UserRequest request, ResourceBundle messages) throws AsmsException {
		NonTeachingStaffDetails nonTeachingStaffDetails = request.getUserDetails().getNonTeachingStaffDetails();

		if (null == nonTeachingStaffDetails)
			throw exceptionHandler.constructAsmsException(messages.getString("NON_TEACHING_STAFF_DETAILS_NULL_CODE"),
					messages.getString("NON_TEACHING_STAFF_DETAILS_NULL_MSG"));

		if (null == nonTeachingStaffDetails.getDesignation() || nonTeachingStaffDetails.getDesignation().isEmpty())
			throw exceptionHandler.constructAsmsException(
					messages.getString("NON_TEACHING_STAFF_DESIGNATION_NULL_CODE"),
					messages.getString("NON_TEACHING_STAFF_DESIGNATION_NULL_MSG"));

		if (null == nonTeachingStaffDetails.getFirstName() || nonTeachingStaffDetails.getFirstName().isEmpty())
			throw exceptionHandler.constructAsmsException(messages.getString("NON_TEACHING_STAFF_FIRST_NAME_NULL_CODE"),
					messages.getString("NON_TEACHING_STAFF_FIRST_NAME_NULL_MSG"));

		if (null == nonTeachingStaffDetails.getMiddleName() || nonTeachingStaffDetails.getMiddleName().isEmpty())
			throw exceptionHandler.constructAsmsException(
					messages.getString("NON_TEACHING_STAFF_MIDDLE_NAME_NULL_CODE"),
					messages.getString("NON_TEACHING_STAFF_MIDDLE_NAME_NULL_MSG"));

		if (null == nonTeachingStaffDetails.getLastName() || nonTeachingStaffDetails.getLastName().isEmpty())
			throw exceptionHandler.constructAsmsException(messages.getString("NON_TEACHING_STAFF_LAST_NAME_NULL_CODE"),
					messages.getString("NON_TEACHING_STAFF_LAST_NAME_NULL_MSG"));

		if (nonTeachingStaffDetails.getDob().toString().isEmpty())
			throw exceptionHandler.constructAsmsException(messages.getString("NON_TEACHING_STAFF_DOB_NULL_CODE"),
					messages.getString("NON_TEACHING_STAFF_DOB_NULL_MSG"));

		if (null == nonTeachingStaffDetails.getGender() || nonTeachingStaffDetails.getGender().isEmpty())
			throw exceptionHandler.constructAsmsException(messages.getString("NON_TEACHING_STAFF_GENDER_NULL_CODE"),
					messages.getString("NON_TEACHING_STAFF_GENDER_NULL_MSG"));

		if (0 == nonTeachingStaffDetails.getAgeInYears())
			throw exceptionHandler.constructAsmsException(
					messages.getString("NON_TEACHING_STAFF_AGE_IN_YEARS_NULL_CODE"),
					messages.getString("NON_TEACHING_STAFF_AGE_IN_YEARS_NULL_MSG"));

		if (0 == nonTeachingStaffDetails.getContactNo())
			throw exceptionHandler.constructAsmsException(messages.getString("NON_TEACHING_STAFF_CONTACT_NO_NULL_CODE"),
					messages.getString("NON_TEACHING_STAFF_CONTACT_NO_NULL_MSG"));

		if (null == nonTeachingStaffDetails.getQualification() || nonTeachingStaffDetails.getQualification().isEmpty())
			throw exceptionHandler.constructAsmsException(
					messages.getString("NON_TEACHING_STAFF_QUALIFICATION_NULL_CODE"),
					messages.getString("NON_TEACHING_STAFF_QUALIFICATION_NULL_MSG"));

		if (null == nonTeachingStaffDetails.getReligion() || nonTeachingStaffDetails.getReligion().isEmpty())
			throw exceptionHandler.constructAsmsException(messages.getString("NON_TEACHING_STAFF_RELIGION_NULL_CODE"),
					messages.getString("NON_TEACHING_STAFF_RELIGION_NULL_MSG"));

		if (null == nonTeachingStaffDetails.getCasteCategory() || nonTeachingStaffDetails.getCasteCategory().isEmpty())
			throw exceptionHandler.constructAsmsException(
					messages.getString("NON_TEACHING_STAFF_CASTE_CATEGORY_NULL_CODE"),
					messages.getString("NON_TEACHING_STAFF_CASTE_CATEGORY_NULL_MSG"));

		if (null == nonTeachingStaffDetails.getPhoto() || nonTeachingStaffDetails.getPhoto().isEmpty())
			throw exceptionHandler.constructAsmsException(messages.getString("NON_TEACHING_STAFF_PHOTO_NULL_CODE"),
					messages.getString("NON_TEACHING_STAFF_PHOTO_NULL_MSG"));
		
		if (null == nonTeachingStaffDetails.getBloodGroup() || nonTeachingStaffDetails.getBloodGroup().isEmpty())
			throw exceptionHandler.constructAsmsException(messages.getString("NON_TEACHING_STAFF_BLOODGROUP_NULL_CODE"),
					messages.getString("NON_TEACHING_STAFF_BLOODGROUP_NULL_MSG"));

		if (null == nonTeachingStaffDetails.getMaritalStatus() || nonTeachingStaffDetails.getMaritalStatus().isEmpty())
			throw exceptionHandler.constructAsmsException(
					messages.getString("NON_TEACHING_STAFF_MARITAL_STATUS_NULL_CODE"),
					messages.getString("NON_TEACHING_STAFF_MARITAL_STATUS_NULL_MSG"));
	}

	/*
	 * Method: validateAdditionalUserDetails input : request,messages output :
	 * void
	 * 
	 */
	public void validateAdditionalUserDetails(UserRequest request, ResourceBundle messages) throws AsmsException {

		if (null == request.getUserDetails().getUserId() || request.getUserDetails().getUserId().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("USERID_NULL_CODE"),
					messages.getString("USERID_NULL_MSG"));
		}

		if (null == request.getDetailType() || request.getDetailType().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("DETAIL_TYPE_NULL_CODE"),
					messages.getString("DETAIL_TYPE_NULL_MSG"));
		}

		if (request.getUserRole().equalsIgnoreCase(Constants.role_student)) {
			StudentDetails details = request.getUserDetails().getStudentDetails();
			if (null == details) {
				throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_NULL_CODE"),
						messages.getString("STUDENT_DETAILS_NULL"));
			}

			if (request.getDetailType().equalsIgnoreCase(Constants.detail_parent)) {
				validateParentDetails(details.getParentDetails(), messages);
			} else if (request.getDetailType().equalsIgnoreCase(Constants.detail_address)) {
				validateStudentAddressDetails(details.getAddressDetails(), messages);
			} else if (request.getDetailType().equalsIgnoreCase(Constants.detail_documents)) {
				validateStudentDocumentDetails(details.getDocumentDetails(), messages);
			}else if (request.getDetailType().equalsIgnoreCase(Constants.detail_previous_school)) {
				validateStudentPreviousDetails(details.getPreviousDetails(), messages);
			} else {
				logger.error(
						"session id: " + MDC.get("sessionId") + "  " + "invalid details: " + request.getDetailType());
			}

		} else if (request.getUserRole().equalsIgnoreCase(Constants.role_non_teaching_staff)) {

			NonTeachingStaffDetails NonTeachingStaffDetails = request.getUserDetails().getNonTeachingStaffDetails();

			if (null == NonTeachingStaffDetails) {
				throw exceptionHandler.constructAsmsException(
						messages.getString("NON_TEACHING_STAFF_DETAILS_NULL_CODE"),
						messages.getString("NON_TEACHING_STAFF_DETAILS_NULL_MSG"));
			}

			if (request.getDetailType().equalsIgnoreCase(Constants.detail_address)) {
				validateAddressDetails(NonTeachingStaffDetails.getAddressDetails(), messages);
			}

			if (request.getDetailType().equalsIgnoreCase(Constants.documents)) {
				validateStaffDocumentDetails(NonTeachingStaffDetails.getStaffDocumentsDetails(), messages);
			}
			if (request.getDetailType().equalsIgnoreCase(Constants.previous_info)) {
				validateStaffPreviousInformationDetails(NonTeachingStaffDetails.getStaffPreviousInformationDetails(),
						messages);
			}
			if (request.getDetailType().equalsIgnoreCase(Constants.statutory_details)) {
				validateStatutoryDetails(NonTeachingStaffDetails.getStaffStatutoryDetails(), messages);
			}

			else {
				logger.error(
						"session id: " + MDC.get("sessionId") + "  " + "invalid details: " + request.getDetailType());
			}

		} else if (request.getUserRole().equalsIgnoreCase(Constants.role_teaching_staff)) {

			TeachingStaffDetails teachingStaffDetails = request.getUserDetails().getTeachingStaffDetails();

			if (null == teachingStaffDetails) {
				throw exceptionHandler.constructAsmsException(messages.getString("TEACHING_STAFF_DETAILS_NULL_CODE"),
						messages.getString("TEACHING_STAFF_DETAILS_NULL_MSG"));
			}
			if (request.getDetailType().equalsIgnoreCase(Constants.detail_address)) {
				validateAddressDetails(teachingStaffDetails.getAddressDetails(), messages);
			}

			if (request.getDetailType().equalsIgnoreCase(Constants.documents)) {
				validateStaffDocumentDetails(teachingStaffDetails.getStaffDocumentsDetails(), messages);
			}
			if (request.getDetailType().equalsIgnoreCase(Constants.previous_info)) {
				validateStaffPreviousInformationDetails(teachingStaffDetails.getStaffPreviousInformationDetails(),
						messages);
			}
			if (request.getDetailType().equalsIgnoreCase(Constants.statutory_details)) {
				validateStatutoryDetails(teachingStaffDetails.getStaffStatutoryDetails(), messages);
			}

			else {
				logger.error(
						"session id: " + MDC.get("sessionId") + "  " + "invalid details: " + request.getDetailType());
			}
			// validateManagementDetails(request, messages);

		}

	}

	/*
	 * ValidateParentDetails : validate parent info
	 * 
	 * input : ParentDetails
	 * 
	 * output: void
	 * 
	 */

	public void validateParentDetails(ParentDetails details, ResourceBundle messages) throws AsmsException {
		if (null == details) {
			throw exceptionHandler.constructAsmsException(messages.getString("PARENT_DETAILS_NULL_CODE"),
					messages.getString("PARENT_DETAILS_NULL_MSG"));
		}
		if (null == details.getfFirstName() || details.getfFirstName().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("FFNAME_NULL_CODE"),
					messages.getString("FFNAME_NULL_MSG"));
		}

		if (null == details.getfLastName() || details.getfLastName().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("FLNAME_NULL_CODE"),
					messages.getString("FLNAME_NULL_MSG"));
		}
		if (null == details.getfQualification() || details.getfQualification().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("FQUALIFICATION_NULL_CODE"),
					messages.getString("FQUALIFICATION_NULL_MSG"));
		}

		if (null == details.getfOccupation() || details.getfOccupation().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("FOCCUPATION_NULL_CODE"),
					messages.getString("FOCCUPATION_NULL_MSG"));
		}

		// contact number validation needs to be done

		if (null == details.getfEmail() || details.getfEmail().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("FEMAIL_NULL_CODE"),
					messages.getString("FEMAIL_NULL_MSG"));
		}
		EmailValidator emailValidator = EmailValidator.getInstance();
		if (!emailValidator.isValid(details.getfEmail())) {
			throw exceptionHandler.constructAsmsException(messages.getString("EMAIL_INVALID_CODE"),
					messages.getString("EMAIL_INVALID"));
		}

		if (null == details.getmFirstName() || details.getmFirstName().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("MFNAME_NULL_CODE"),
					messages.getString("MFNAME_NULL_MSG"));
		}

		if (null == details.getmLastName() || details.getmLastName().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("MLNAME_NULL_CODE"),
					messages.getString("MLNAME_NULL_MSG"));
		}
		if (null == details.getmQualification() || details.getmQualification().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("MQUALIFICATION_NULL_CODE"),
					messages.getString("MQUALIFICATION_NULL_MSG"));
		}
		if (null == details.getmEmail() || details.getmEmail().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("MEMAIL_NULL_CODE"),
					messages.getString("MEMAIL_NULL_MSG"));
		}
		if (!emailValidator.isValid(details.getmEmail())) {
			throw exceptionHandler.constructAsmsException(messages.getString("EMAIL_INVALID_CODE"),
					messages.getString("EMAIL_INVALID"));
		}
		if (null == details.getFpassword() || details.getFpassword().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("FPASSWORD_NULL_CODE"),
					messages.getString("FPASSWORD_NULL_MSG"));
		}
		if (null == details.getFpassword() || details.getFpassword().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("FEMAIL_ALREADY_EXIST_EXCEPTION_CODE "),
					messages.getString("FEMAIL_ALREADY_EXIST_EXCEPTION_MSG "));
		}
		if (null == details.getMpassword() || details.getMpassword().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("MPASSWORD_NULL_CODE"),
					messages.getString("MPASSWORD_NULL_MSG"));
		}
		if (null == details.getIsNew() || details.getIsNew().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("ISNEW_NULL_CODE"),
					messages.getString("ISNEW_NULL_MSG"));
		}
	}

	/*
	 * Method : validateAddressDetails - > validates addressDetails fields input
	 * : addressDetails, messages ouput : void
	 */
	public void validateAddressDetails(AddressDetails addressDetails, ResourceBundle messages) throws AsmsException {
		if (null == addressDetails) {
			throw exceptionHandler.constructAsmsException(messages.getString("ADDRESS_DETAILS_TYPE_NULL_CODE"),
					messages.getString("ADDRESS_DETAILS_TYPE_NULL_MSG"));
		}
		if (null == addressDetails.getAddressLine1() || addressDetails.getAddressLine1().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("ADDRESS_DETAILS_ADDRESSLINE1_NULL_CODE"),
					messages.getString("ADDRESS_DETAILS_ADDRESSLINE1_NULL_MSG"));
		}

		if (null == addressDetails.getAddressLine2() || addressDetails.getAddressLine2().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("ADDRESS_DETAILS_ADDRESSLINE2_NULL_MSG"),
					messages.getString("ADDRESS_DETAILS_ADDRESSLINE2_NULL_CODE"));
		}
		if (null == addressDetails.getCountry() || addressDetails.getCountry().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("ADDRESS_DETAILS_COUNTRY_NULL_CODE"),
					messages.getString("ADDRESS_DETAILS_COUNTRY_NULL_MSG"));
		}

		if (null == addressDetails.getState() || addressDetails.getState().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("ADDRESS_DETAILS_STATE_NULL_CODE"),
					messages.getString("ADDRESS_DETAILS_STATE_NULL_MSG"));
		}

		if (null == addressDetails.getDistrict() || addressDetails.getDistrict().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("ADDRESS_DETAILS_DISTRICT_CODE"),
					messages.getString("ADDRESS_DETAILS_DISTRICT_MSG"));
		}
		if (null == addressDetails.getSubDivision() || addressDetails.getSubDivision().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("ADDRESS_DETAILS_SUBDIVISION_CODE"),
					messages.getString("ADDRESS_DETAILS_SUBDIVISION_MSG"));
		}
		if (null == addressDetails.getTehsil() || addressDetails.getTehsil().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("ADDRESS_DETAILS_TEHSIL_CODE"),
					messages.getString("ADDRESS_DETAILS_TEHSIL_MSG"));
		}
		if (null == addressDetails.getVillage() || addressDetails.getVillage().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("ADDRESS_DETAILS_VILLAGE_CODE"),
					messages.getString("ADDRESS_DETAILS_VILLAGE_MSG"));
		}

	}

	/*
	 * validateStudentDocumentDetails : validate parent info
	 * 
	 * input : StudentDocumentDetails
	 * 
	 * output: void
	 * 
	 */

	public void validateStudentDocumentDetails(StudentDocumentDetails details, ResourceBundle messages)
			throws AsmsException {
		if (null == details) {
			throw exceptionHandler.constructAsmsException(messages.getString("SDOCUMENTS_DETAILS_NULL_CODE"),
					messages.getString("SDOCUMENTS_DETAILS_NULL_MSG"));
		}
		if (null == details.getAadhaarNo() || details.getAadhaarNo().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SADHAAR_NO_NULL_CODE"),
					messages.getString("SADHAAR_NO_NULL_MSG"));
		}

		if (null == details.getAadhaarCard() || details.getAadhaarCard().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SADHAAR_CARD_NULL_CODE"),
					messages.getString("SADHAAR_CARD_NULL_MSG"));
		}
		if (null == details.getBirthCirtificate() || details.getBirthCirtificate().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SBIRTH_CERTIFICATE_NULL_CODE"),
					messages.getString("SBIRTH_CERTIFICATE_NULL_MSG"));
		}

	}
	/*
	 * Method : validateStaffDocumentDetails - > validates DocumentsDetails
	 * fields input : staffDocumentsDetails, messages ouput : void
	 */

	public void validateStaffDocumentDetails(StaffDocumentsDetails staffDocumentsDetails, ResourceBundle messages)
			throws AsmsException {
		if (null == staffDocumentsDetails) {
			throw exceptionHandler.constructAsmsException(messages.getString("STAFF_DOCUMENTS_TYPE_NULL_CODE"),
					messages.getString("STAFF_DOCUMENTS_TYPE_NULL_MSG"));
		}
		if (null == staffDocumentsDetails.getNt_staff_10th_certificate()
				|| staffDocumentsDetails.getNt_staff_10th_certificate().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(
					messages.getString("STAFF_DOCUMENTS_TEN10THCERTIFICATE_NULL_CODE"),
					messages.getString("STAFF_DOCUMENTS_TEN10THCERTIFICATE_NULL_MSG"));
		}

		if (null == staffDocumentsDetails.getNt_staff_12th_certificate()
				|| staffDocumentsDetails.getNt_staff_12th_certificate().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(
					messages.getString("STAFF_DOCUMENTS_TWELVE12THCERTIFICATE_NULL_CODE"),
					messages.getString("STAFF_DOCUMENTS_TWELVE12THCERTIFICATE_NULL_MSG"));
		}
		if (null == staffDocumentsDetails.getNt_staff_degree_certificate()
				|| staffDocumentsDetails.getNt_staff_degree_certificate().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(
					messages.getString("STAFF_DOCUMENTS_DEGREECERTIFICATE_NULL_CODE"),
					messages.getString("STAFF_DOCUMENTS_DEGREECERTIFICATE_NULL_MSG"));
		}

		if (null == staffDocumentsDetails.getNt_staff_pg_degree_certificate()
				|| staffDocumentsDetails.getNt_staff_pg_degree_certificate().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(
					messages.getString("STAFF_DOCUMENTS_PGDEGREECERTIFICATE_NULL_CODE"),
					messages.getString("STAFF_DOCUMENTS_PGDEGREECERTIFICATE_NULL_MSG"));
		}

		if (null == staffDocumentsDetails.getNt_staff_medical_fitness_certificate()
				|| staffDocumentsDetails.getNt_staff_medical_fitness_certificate().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(
					messages.getString("STAFF_DOCUMENTS_MEDICALFITNESSCERTIFICATE_NULL_CODE"),
					messages.getString("STAFF_DOCUMENTS_MEDICALFITNESSCERTIFICATE_NULL_MSG"));
		}
	}

	/**
	 * @param staffPreviousInformationDetails
	 * @param messages
	 */
	/*
	 * Method : validateStaffPreviousInformationDetails - > validates
	 * PreviousInformationDetails fields input :
	 * staffPreviousInformationDetails, messages ouput : void
	 */
	private void validateStaffPreviousInformationDetails(
			StaffPreviousInformationDetails staffPreviousInformationDetails, ResourceBundle messages)
			throws AsmsException {

		if (null == staffPreviousInformationDetails) {
			throw exceptionHandler.constructAsmsException(
					messages.getString("STAFF_PREVIOUS_INFORMATION_TYPE_NULL_CODE"),
					messages.getString("STAFF_PREVIOUS_INFORMATION_TYPE_MSG"));
		}
		/*
		 * if (staffPreviousInformationDetails.isExperienceFlag()) { throw
		 * exceptionHandler.constructAsmsException(messages.getString(
		 * "STAFF_PREVIOUS_INFORMATION_EXPERIENCEFLAG_NULL_CODE"),
		 * messages.getString(
		 * "STAFF_PREVIOUS_INFORMATION_EXPERIENCEFLAG_NULL_MSG")); }
		 * 
		 * if (null ==
		 * staffPreviousInformationDetails.getLastWorkedOrganisation() ||
		 * staffPreviousInformationDetails.getLastWorkedOrganisation().trim().
		 * isEmpty()) { throw
		 * exceptionHandler.constructAsmsException(messages.getString(
		 * "STAFF_PREVIOUS_INFORMATION_LASTWORKEDORGANISATION_NULL_CODE"),
		 * messages.getString(
		 * "STAFF_PREVIOUS_INFORMATION_LASTWORKEDORGANISATION_NULL_MSG")); } if
		 * (null ==
		 * staffPreviousInformationDetails.getDateOfJoining().toString() ) {
		 * throw exceptionHandler.constructAsmsException(messages.getString(
		 * "STAFF_PREVIOUS_INFORMATION_DATEOFJOINING_NULL_CODE"),
		 * messages.getString(
		 * "STAFF_PREVIOUS_INFORMATION_DATEOFJOINING_NULL_MSG")); }
		 * 
		 * if (null ==
		 * staffPreviousInformationDetails.getRelievingDate().toString() ) {
		 * throw exceptionHandler.constructAsmsException(messages.getString(
		 * "STAFF_PREVIOUS_INFORMATION_RELIEVINGDATE_NULL_CODE"),
		 * messages.getString(
		 * "STAFF_PREVIOUS_INFORMATION_RELIEVINGDATE_NULL_MSG")); }
		 * 
		 * if (null ==
		 * staffPreviousInformationDetails.getExperienceCertificate() ||
		 * staffPreviousInformationDetails.getExperienceCertificate().trim().
		 * isEmpty()) { throw
		 * exceptionHandler.constructAsmsException(messages.getString(
		 * "STAFF_PREVIOUS_INFORMATION_EXPERIENCECERTIFICATE_NULL_CODE"),
		 * messages.getString(
		 * "STAFF_PREVIOUS_INFORMATION_EXPERIENCECERTIFICATE_NULL_MSG")); } if
		 * (null == staffPreviousInformationDetails.getLastDrawnPayslip() ||
		 * staffPreviousInformationDetails.getLastDrawnPayslip().trim().isEmpty(
		 * )) { throw
		 * exceptionHandler.constructAsmsException(messages.getString(
		 * "STAFF_PREVIOUS_INFORMATION_LASTDRAWNPAYSLIP_NULL_CODE"),
		 * messages.getString(
		 * "STAFF_PREVIOUS_INFORMATION_LASTDRAWNPAYSLIP_NULL_MSG")); }
		 */
		if (null == staffPreviousInformationDetails.getResume()
				|| staffPreviousInformationDetails.getResume().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(
					messages.getString("STAFF_PREVIOUS_INFORMATION_RESUME_NULL_CODE"),
					messages.getString("STAFF_PREVIOUS_INFORMATION_RESUME_NULL_MSG"));
		}

	}

	/*
	 * Method : validateStatutoryDetails - > validates StatutoryDetails fields
	 * input : staffStatutoryDetails, messages ouput : void
	 */
	public void validateStatutoryDetails(StaffStatutoryDetails staffStatutoryDetails, ResourceBundle messages)
			throws AsmsException {
		if (null == staffStatutoryDetails) {
			throw exceptionHandler.constructAsmsException(messages.getString("STAFF_STATUTORY_DETAILS_TYPE_NULL_CODE"),
					messages.getString("STAFF_STATUTORY_DETAILS_TYPE_NULL_MSG"));
		}

		if (null == staffStatutoryDetails.getBankName() || staffStatutoryDetails.getBankName().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(
					messages.getString("STAFF_STATUTORY_DETAILS_BANKNAME_NULL_CODE"),
					messages.getString("STAFF_STATUTORY_DETAILS_BANKNAME_NULL_MSG"));
		}
		if (null == staffStatutoryDetails.getBankAccountNo()
				|| staffStatutoryDetails.getBankAccountNo().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(
					messages.getString("STAFF_STATUTORY_DETAILS_BANKACCOUNTNO_NULL_CODE"),
					messages.getString("STAFF_STATUTORY_DETAILS_BANKACCOUNTNO_NULL_MSG"));
		}
		if (null == staffStatutoryDetails.getBankIfscCode()
				|| staffStatutoryDetails.getBankIfscCode().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(
					messages.getString("STAFF_STATUTORY_DETAILS_BANKIFSCCODE_NULL_CODE"),
					messages.getString("STAFF_STATUTORY_DETAILS_BANKIFSCCODE_NULL_MSG"));
		}
		if (null == staffStatutoryDetails.getPanNo() || staffStatutoryDetails.getPanNo().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("STAFF_STATUTORY_DETAILS_PANNO_NULL_CODE"),
					messages.getString("STAFF_STATUTORY_DETAILS_PANNO_NULL_MSG"));
		}
		if (null == staffStatutoryDetails.getPanCard() || staffStatutoryDetails.getPanCard().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(
					messages.getString("STAFF_STATUTORY_DETAILS_PANCARD_NULL_CODE"),
					messages.getString("STAFF_STATUTORY_DETAILS_PANCARD_NULL_MSG"));
		}
		if (null == staffStatutoryDetails.getAadhaarNo() || staffStatutoryDetails.getAadhaarNo().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(
					messages.getString("STAFF_STATUTORY_DETAILS_AADHAARNO_NULL_CODE"),
					messages.getString("STAFF_STATUTORY_DETAILS_AADHAARNO_NULL_MSG"));
		}
		if (null == staffStatutoryDetails.getAadhaarCard() || staffStatutoryDetails.getAadhaarCard().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(
					messages.getString("STAFF_STATUTORY_DETAILS_AADHAARCARD_NULL_CODE"),
					messages.getString("STAFF_STATUTORY_DETAILS_AADHAARCARD_NULL_MSG"));
		}
		if (null == staffStatutoryDetails.getPfNo() || staffStatutoryDetails.getPfNo().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("STAFF_STATUTORY_DETAILS_PFNO_NULL_CODE"),
					messages.getString("STAFF_STATUTORY_DETAILS_PFNO_NULL_MSG"));
		}

	}

	/*
	 * validateSiblingDetails : validate sibling info
	 * 
	 * input : SiblingDetails
	 * 
	 * output: void
	 * 
	 */

	/*public void validateSiblingDetails(SiblingDetails details, ResourceBundle messages) throws AsmsException {
		if (null == details) {
			throw exceptionHandler.constructAsmsException(messages.getString("SIBLING_DETAILS_NULL_CODE"),
					messages.getString("SIBLING_DETAILS_NULL_MSG"));
		}
		if (null == details.getName() || details.getName().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SIBLING_NAME_NULL_CODE"),
					messages.getString("SIBLING_NAME_NULL_MSG"));
		}

		if (null == details.getGender() || details.getGender().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SIBLING_GENDER_NULL_CODE"),
					messages.getString("SIBLING_GENDER_NULL_MSG"));
		}
		if (null == details.getSiblingClass() || details.getSiblingClass().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SIBLING_CLASS_NULL_CODE"),
					messages.getString("SIBLING_CLASS_NULL_MSG"));
		}

		if (null == details.getSchool() || details.getSchool().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SIBLING_SCHOOL_NULL_CODE"),
					messages.getString("SIBLING_SCHOOL_NULL_MSG"));
		}
		if (null == details.getSectionName() || details.getSectionName().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SECTION_NAME_NULL_CODE"),
					messages.getString("SECTION_NAME_NULL_CODE_MSG"));
		}
		

	}*/

	/*
	 * validateStudentPreviousDetails
	 * 
	 * input : StudentPreviousDetails
	 * 
	 * output: void
	 * 
	 */

	public void validateStudentPreviousDetails(StudentPreviousDetails details, ResourceBundle messages)
			throws AsmsException {
		if (null == details) {
			throw exceptionHandler.constructAsmsException(messages.getString("SPREVIOUS_DETAILS_NULL_CODE"),
					messages.getString("SPREVIOUS_DETAILS_NULL_MSG"));
		}
		if (null == details.getSchool() || details.getSchool().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SPREVIOUS_DETAILS_SCHOOL_NAME_NULL_CODE"),
					messages.getString("SPREVIOUS_DETAILS_SCHOOL_NAME_NULL_MSG"));
		}

		if (null == details.getStudiedFrom()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SPREVIOUS_DETAILS_STUDIEDFROM_NULL_CODE"),
					messages.getString("SPREVIOUS_DETAILS_STUDIEDFROM_NULL_MSG"));
		}
		if (null == details.getStudiedTo()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SPREVIOUS_DETAILS_STUDIEDTO_NULL_CODE"),
					messages.getString("SPREVIOUS_DETAILS_STUDIEDTO_NULL_MSG"));
		}

		if (details.getNoOfYears() < 0) {
			throw exceptionHandler.constructAsmsException(messages.getString("SPREVIOUS_DETAILS_YEARS_INVALID_CODE"),
					messages.getString("SPREVIOUS_DETAILS_YEARS_INVALID_MSG"));
		}

		if (details.getTotalMarks() <= 0) {
			throw exceptionHandler.constructAsmsException(messages.getString("SPREVIOUS_DETAILS_TMARKS_INVALID_CODE"),
					messages.getString("SPREVIOUS_DETAILS_TMARKS_INVALID_MSG"));
		}

		if (details.getObtainedMarks() < 0) {
			throw exceptionHandler.constructAsmsException(messages.getString("SPREVIOUS_DETAILS_OMARKS_INVALID_CODE"),
					messages.getString("SPREVIOUS_DETAILS_OMARKS_INVALID_MSG"));
		}
		if (null == details.getPreviousClass() || details.getPreviousClass().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SPREVIOUS_DETAILS_SCHOOL_NAME_NULL_CODE"),
					messages.getString("SPREVIOUS_DETAILS_SCHOOL_NAME_NULL_MSG"));
		}

	}

	/*
	 * validateStudentAddressDetails : validate student address info
	 * 
	 * input : ParentDetails
	 * 
	 * output: void
	 * 
	 */

	public void validateStudentAddressDetails(StudentAddressDetails details, ResourceBundle messages)
			throws AsmsException {
		if (null == details) {
			throw exceptionHandler.constructAsmsException(messages.getString("ADDRESS_DETAILS_NULL_CODE"),
					messages.getString("ADDRESS_DETAILS_NULL_MSG"));
		}
		if (null == details.getpAddressLine1() || details.getpAddressLine1().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("PADDRESS1_NULL_CODE"),
					messages.getString("PADDRESS1_NULL_MSG"));
		}

		if (null == details.getpAddressLine2() || details.getpAddressLine2().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("PADDRESS2_NULL_CODE"),
					messages.getString("PADDRESS2_NULL_MSG"));
		}
		if (null == details.getpCountry() || details.getpCountry().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("PCOUNTRY_NULL_CODE"),
					messages.getString("PCOUNTRY_NULL_CODE"));
		}

		if (null == details.getpState() || details.getpState().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("STATE_NULL_CODE"),
					messages.getString("STATE_NULL_MSG"));
		}

		if (null == details.getpDistrict() || details.getpDistrict().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("DISTRICT_NULL_CODE"),
					messages.getString("DISTRICT_NULL_MSG"));
		}

		if (null == details.getpPincode() || details.getpPincode().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("PIN_NULL_CODE"),
					messages.getString("PIN_NULL_MSG"));
		}

		if (null == details.getpLocation() || details.getpLocation().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("LOCATION_NULL_CODE"),
					messages.getString("LOCATION_NULL_MSG"));
		}
		if (!details.isParmanentAddressSameAsCurrent()) {

			if (null == details.getcAddressLine1() || details.getcAddressLine1().trim().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("PADDRESS1_NULL_CODE"),
						messages.getString("PADDRESS1_NULL_MSG"));
			}

			if (null == details.getcAddressLine2() || details.getcAddressLine2().trim().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("PADDRESS2_NULL_CODE"),
						messages.getString("PADDRESS2_NULL_MSG"));
			}
			if (null == details.getcCountry() || details.getcCountry().trim().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("PCOUNTRY_NULL_CODE"),
						messages.getString("PCOUNTRY_NULL_CODE"));
			}

			if (null == details.getcState() || details.getcState().trim().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("STATE_NULL_CODE"),
						messages.getString("STATE_NULL_MSG"));
			}

			if (null == details.getcDistrict() || details.getcDistrict().trim().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("DISTRICT_NULL_CODE"),
						messages.getString("DISTRICT_NULL_MSG"));
			}

			if (null == details.getcPincode() || details.getcPincode().trim().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("PIN_NULL_CODE"),
						messages.getString("PIN_NULL_MSG"));
			}

			if (null == details.getcLocation() || details.getcLocation().trim().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("LOCATION_NULL_CODE"),
						messages.getString("LOCATION_NULL_MSG"));
			}

		}

	}

	// ChangePasswordDetails validation starts
	public void ChangePasswordDetails(UserRequest request, ResourceBundle messages) throws AsmsException {
		ChangePasswordDetails changePasswordDetails = request.getUserDetails().getChangePasswordDetails();
		if (null == changePasswordDetails)
			throw exceptionHandler.constructAsmsException(messages.getString("CHANGEPASSWORD_DETAILS_NULL_CODE"),
					messages.getString("CHANGEPASSWORD_DETAILS_NULL_MSG"));

		if (null == changePasswordDetails.getCurrentpassword()) {
			throw exceptionHandler.constructAsmsException(messages.getString("CURRENTPASSWORD_DETAILS_NULL_CODE"),
					messages.getString("CURRENTPASSWORD_DETAILS_NULL_MSG"));
		}
		if (null == changePasswordDetails.getNewpassword()) {
			throw exceptionHandler.constructAsmsException(messages.getString("NEWPASSWORD_DETAILS_NULL_CODE"),
					messages.getString("NEWPASSWORD_DETAILS_NULL_MSG"));
		}
		if (null == changePasswordDetails.getConfirmpassword()) {
			throw exceptionHandler.constructAsmsException(messages.getString("CONFIRMPASSWORD_DETAILS_NULL_CODE"),
					messages.getString("CONFIRMPASSWORD_DETAILS_NULL_MSG"));
		}
	}

	public void validateChangePasswordDetails(ChangePasswordDetails changePasswordDetails, ResourceBundle messages)
			throws AsmsException {

		if (null == changePasswordDetails)
			throw exceptionHandler.constructAsmsException(messages.getString("CHANGEPASSWORD_DETAILS_NULL_CODE"),
					messages.getString("CHANGEPASSWORD_DETAILS_NULL_MSG"));

		if (null == changePasswordDetails.getCurrentpassword()
				|| changePasswordDetails.getCurrentpassword().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("CURRENTPASSWORD_DETAILS_NULL_CODE"),
					messages.getString("CURRENTPASSWORD_DETAILS_NULL_MSG"));
		}
		if (null == changePasswordDetails.getNewpassword() || changePasswordDetails.getNewpassword().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("NEWPASSWORD_DETAILS_NULL_CODE"),
					messages.getString("NEWPASSWORD_DETAILS_NULL_MSG"));
		}
		if (null == changePasswordDetails.getConfirmpassword()
				|| changePasswordDetails.getConfirmpassword().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("CONFIRMPASSWORD_DETAILS_NULL_CODE"),
					messages.getString("CONFIRMPASSWORD_DETAILS_NULL_MSG"));
		}
		
		if (!changePasswordDetails.getNewpassword().equals(changePasswordDetails.getConfirmpassword())) {
			throw exceptionHandler.constructAsmsException(
					messages.getString("CURRENTPASSWORD_IS_SAMEAS_NEWPASSWORD_NULL_CODE"),
					messages.getString("CURRENTPASSWORD_IS_SAMEAS_NEWPASSWORD_NULL_MSG"));
		}
	}
	
	
	public void validateSerchForUserPrivileges(String role, String subrole, String id, ResourceBundle messages) throws AsmsException {

		if (null == role || role.isEmpty()) {
			throw exceptionHandler.constructAsmsException(
					messages.getString("ROLE_NULL_CODE"),
					messages.getString("ROLE_NULL_MSG"));
		}
		
		if(Constants.role_student.equalsIgnoreCase(role)){
			if(null == id || id.isEmpty()){
				throw exceptionHandler.constructAsmsException(
						messages.getString("STUDENT_ID_NULL_CODE"),
						messages.getString("STUDENT_ID_NULL_MSG"));
			}
		}
		
	}
	
	public void validateUserPrivileges(UserDetails details, ResourceBundle messages) throws AsmsException {

		if (null == details) {
			throw exceptionHandler.constructAsmsException(messages.getString("USER_OBJECT_NULL_CODE"),
					messages.getString("USER_OBJECT_NULL"));
		}
		if (null == details.getUserId() || details.getUserId().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("USERID_NULL_CODE"),
					messages.getString("USERID_NULL_MSG"));
		}
		
	}

public void validateUserDetails(UserDetails userDetails, ResourceBundle messages) throws AsmsException {
	if (null == userDetails) {
		throw exceptionHandler.constructAsmsException(messages.getString("USER_OBJECT_NULL_CODE"),
				messages.getString("USER_OBJECT_NULL"));
	}
	
	if (null == userDetails.getEmail() || userDetails.getEmail().isEmpty()) {
		throw exceptionHandler.constructAsmsException(messages.getString("USER_EMAIL_NULL_CODE"),
			messages.getString("USER_EMAIL_NULL_MGS"));
			
	} 
	if (null == userDetails.getUserPassword() || userDetails.getUserPassword() .isEmpty()) {
		throw exceptionHandler.constructAsmsException(messages.getString("USER_PASSWORD_NULL_CODE"),
			messages.getString("USER_PASSWORD_NULL_MSG"));
	
	}
}

}