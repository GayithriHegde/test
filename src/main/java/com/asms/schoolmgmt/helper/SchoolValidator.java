/**
@{author} mallikarjun.guranna
01-Sep-2017
*/
package com.asms.schoolmgmt.helper;

import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.commons.validator.routines.EmailValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asms.Exception.AsmsException;
import com.asms.Exception.ExceptionHandler;
import com.asms.common.helper.AsmsHelper;


import com.asms.schoolmgmt.entity.SetupSchoolDetails;
import com.asms.schoolmgmt.request.GroupDetails;
import com.asms.schoolmgmt.request.SchoolDetails;
import com.asms.schoolmgmt.request.TimeTableOnchangeDetails;
import com.asms.schoolmgmt.request.UserRequest;

@Component
public class SchoolValidator {
	/**
	 * @{author} mallikarjun.guranna 01-Sep-2017
	 */

	@Autowired
	private ExceptionHandler exceptionHandler;

	//private static final Logger logger = LoggerFactory.getLogger(SchoolValidator.class);

	/*
	 * Method: validateSchoolDetailsRequest -> validates SchoolDetailsRequest
	 * for null. Parameters -> UserRequest throws -> AsmsException
	 */

	public void validateSchoolDetailsRequest(UserRequest request, ResourceBundle messages, String type)
			throws AsmsException {

		if (null == request) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_NULL_CODE"),
					messages.getString("REQUEST_NULL"));
		}

		if (null == request.getRequestType() || request.getRequestType().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_TYPE_NULL_CODE"),
					messages.getString("REQUEST_TYPE_NULL"));
		}

		SchoolDetails details = request.getSchoolDetails();

		if (null == details) {
			throw exceptionHandler.constructAsmsException(messages.getString("SCHOOL_DETAILS_NULL_CODE"),
					messages.getString("SCHOOL_DETAILS_NULL_MSG"));
		}

	
		if (null == details.getName() || details.getName().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SCHOOL_NAME_NULL_CODE"),
					messages.getString("SCHOOL_NAME_NULL_MSG"));
		}

		if (null == details.getAffiliationId() || details.getAffiliationId().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SCHOOL_AID_NULL_CODE"),
					messages.getString("SCHOOL_AID_NULL_MSG"));
		}

		if (null == details.getRegistrationCode() || details.getRegistrationCode().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SCHOOL_RCODE_NULL_CODE"),
					messages.getString("SCHOOL_RCODE_NULL_MSG"));
		}

		if (null == details.getAddressLine1() || details.getAddressLine1().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SCHOOL_ADD1_NULL_CODE"),
					messages.getString("SCHOOL_ADD1_NULL_MSG"));
		}

		if (null == details.getAddressLine2() || details.getAddressLine2().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SCHOOL_ADD2_NULL_CODE"),
					messages.getString("SCHOOL_ADD2_NULL_MSG"));
		}

		if (null == details.getLocation() || details.getLocation().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SCHOOL_LOCATION_NULL_CODE"),
					messages.getString("SCHOOL_LOCATION_NULL_CODE"));
		}

		if (null == details.getCountry() || details.getCountry().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("COUNTRY_NULL_CODE"),
					messages.getString("COUNTRY_NULL_MSG"));
		}

		if (null == details.getState() || details.getState().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("STATE_NULL_CODE"),
					messages.getString("STATE_NULL_MSG"));
		}

		if (null == details.getDistrict() || details.getDistrict().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("DISTRICT_NULL_CODE"),
					messages.getString("DISTRICT_NULL_MSG"));
		}

		if (null == details.getTehsil() || details.getTehsil().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("TALUK_NULL_CODE"),
					messages.getString("TALUK_NULL_MSG"));
		}
		if (null == details.getPincode() || details.getPincode().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("PIN_NULL_CODE"),
					messages.getString("PIN_NULL_MSG"));
		}

		if (null == details.getContactNo1() || details.getContactNo1().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("PIN_NULL_CODE"),
					messages.getString("PIN_NULL_MSG"));
		}

		if (null == details.getMobileNo() || details.getMobileNo().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("PIN_NULL_CODE"),
					messages.getString("PIN_NULL_MSG"));
		}

		if (null == details.getEmailId() || details.getEmailId().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SCHOOL_SCHOOL_EMAIL_NULL_CODE"),
					messages.getString("SCHOOL_SCHOOL_EMAIL_NULL_MSG"));
		} else {
			EmailValidator emailValidator = EmailValidator.getInstance();
			if (!emailValidator.isValid(details.getEmailId())) {
				throw exceptionHandler.constructAsmsException(messages.getString("EMAIL_INVALID_CODE"),
						messages.getString("EMAIL_INVALID"));
			}
		}

		if (details.getTotalNoOfStudents() <= 0) {
			throw exceptionHandler.constructAsmsException(messages.getString("SCHOOL_SCHOOL_TOTAL_STUDENT_NO_INVALID"),
					messages.getString("SCHOOL_SCHOOL_TOTAL_STUDENT_NO_INVALID_MSG"));
		}

		if (null == details.getContactPersonName() || details.getContactPersonName().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SCHOOL_SCHOOL_CONTACT_NAME_NULL_CODE"),
					messages.getString("SCHOOL_SCHOOL_CONTACT_NAME_NULL_MSG"));
		}

		if (null == details.getContactPersonEmailId() || details.getContactPersonEmailId().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SCHOOL_SCHOOL_CONTACT_EMAIL_NULL_CODE"),
					messages.getString("SCHOOL_SCHOOL_CONTACT_EMAIL_NULL_MSG"));
		} else {
			EmailValidator emailValidator = EmailValidator.getInstance();
			if (!emailValidator.isValid(details.getContactPersonEmailId())) {
				throw exceptionHandler.constructAsmsException(messages.getString("EMAIL_INVALID_CODE"),
						messages.getString("EMAIL_INVALID"));
			}
		}

		if (null == details.getBoardDetails() || details.getBoardDetails().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SCHOOL_BOARD_NULL_CODE"),
					messages.getString("SCHOOL_BOARD_NULL_MSG"));
		}
		
		if (null == details.getSubDomain() || details.getSubDomain().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SCHOOL_SUB_DOMAIN_NULL_CODE"),
					messages.getString("SCHOOL_SUB_DOMAIN_NULL_MSG"));
		}

		// validate for lattitude

	}

	// validate Setup School Request
	// ---------------------------------------------
	/**
	 * method: validateSetupSchoolRequest input: request,messages and req type
	 * return type : void validates user request for setup school details
	 * 
	 */
	public void validateSetupSchoolRequest(UserRequest request, ResourceBundle messages, String type)
			throws AsmsException {

		if (null == request) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_NULL_CODE"),
					messages.getString("REQUEST_NULL"));
		}

		if (null == request.getRequestType() || request.getRequestType().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_TYPE_NULL_CODE"),
					messages.getString("REQUEST_TYPE_NULL"));
		}

		SetupSchoolDetails setupSchoolDetails = request.getSetupSchoolDetails();

		if (null == setupSchoolDetails) {
			throw exceptionHandler.constructAsmsException(messages.getString("SETUP_SCHOOL_TYPE_NULL_CODE"),
					messages.getString("SETUP_SCHOOL_TYPE_NULL_MSG"));
		}

		if (null == setupSchoolDetails.getCurrentAcademicYear()
				|| setupSchoolDetails.getCurrentAcademicYear().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SETUP_SCHOOL_ACADEMIC_YEAR_NULL_CODE"),
					messages.getString("SETUP_SCHOOL_ACADEMIC_YEAR_NULL_MSG"));
		}

	}

	/**
	 * method: validateCreateGroupsRequest input: details,messages and req type
	 * return type : void validates user request for creating groups
	 * 
	 */
	public void validateCreateGroupsRequest(List<GroupDetails> details, ResourceBundle messages, String type)
			throws AsmsException {
		// .matches("[0-9]+"

		if (null == details) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_NULL_CODE"),
					messages.getString("REQUEST_NULL"));
		}

		for (GroupDetails gd : details) {

			if (null == gd.getStartTime() || gd.getStartTime().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("START_TIME_NULL_CODE"),
						messages.getString("START_TIME_NULL_MSG"));
			}

			if (null == gd.getEndTime() || gd.getEndTime().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("END_TIME_NULL_CODE"),
						messages.getString("END_TIME_NULL_MSG"));
			}

			// check time is in hh:mm format
			StringTokenizer time = new StringTokenizer(gd.getStartTime(), ":");
			while (time.hasMoreElements()) {

				if (time.nextElement().toString().matches("[0-9]+")) {
					// valid
				} else {
					throw exceptionHandler.constructAsmsException(messages.getString("INVALID_TIME_CODE"),
							messages.getString("INVALID_TIME_MSG"));
				}
			}

			time = new StringTokenizer(gd.getEndTime(), ":");
			while (time.hasMoreElements()) {
				if (time.nextElement().toString().matches("[0-9]+")) {
					// valid
				} else {
					throw exceptionHandler.constructAsmsException(messages.getString("INVALID_TIME_CODE"),
							messages.getString("INVALID_TIME_MSG"));
				}
			}

			String duration = gd.getPeriodDuration();
			if (null == duration || duration.isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("PERIOD_DURATION_NULL_CODE"),
						messages.getString("PERIOD_DURATION_NULL_MSG"));
			}

			if (!duration.matches("[0-9]+")) {
				throw exceptionHandler.constructAsmsException(messages.getString("PERIOD_DURATION_INVALID_CODE"),
						messages.getString("PERIOD_DURATION_INVALID_MSG"));
			}
			if (Integer.parseInt(duration) < 0) {
				throw exceptionHandler.constructAsmsException(messages.getString("PERIOD_DURATION_INVALID_CODE"),
						messages.getString("PERIOD_DURATION_INVALID_MSG"));
			}

			// check break time format
			/*
			 * for (Breaks b : gd.getBreaks()) { while (time.hasMoreElements())
			 * { StringTokenizer time1 = new
			 * StringTokenizer(time.nextElement().toString(), ":"); while
			 * (time1.hasMoreElements()) { if
			 * (time1.nextElement().toString().matches("[0-9]+")) { // valid }
			 * else { throw
			 * exceptionHandler.constructAsmsException(messages.getString(
			 * "INVALID_TIME_CODE"), messages.getString("INVALID_TIME_MSG")); }
			 * } }
			 * 
			 * }
			 */

		}

	}

	public void validateSchoolSetupCopyRequest(String tenantId, String acdemicyr, ResourceBundle messages)
			throws AsmsException {

		if (null == acdemicyr || acdemicyr.isEmpty() == true) {

			throw exceptionHandler.constructAsmsException(
					messages.getString("SCHOOL_SETUP_COPY_ACADEMIC_YEAR_NULL_CODE"),
					messages.getString("SCHOOL_SETUP_COPY_ACADEMIC_YEAR_NULL_MSG"));
		}

		if (null != acdemicyr && acdemicyr.isEmpty() == false) {
			if (!AsmsHelper.validateAcademicYear(acdemicyr)) {
				throw exceptionHandler.constructAsmsException(
						messages.getString("SCHOOL_SETUP_COPY_CURRENT_ACADEMIC_YEAR"),
						messages.getString("SCHOOL_SETUP_COPY_CURRENT_ACADEMIC_YEAR_MSG"));
			}

		}

		if (null == tenantId || tenantId.isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("SCHOOL_SETUP_COPY_TENANT_ID_NULL_CODE"),
					messages.getString("SCHOOL_SETUP_COPY_TENANT_ID_NULL_MSG"));
		}

	}

	/*public void validateSubjectDetailsRequest(UserRequest request, ResourceBundle messages) throws AsmsException {
		if (null == request) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_NULL_CODE"),
					messages.getString("REQUEST_NULL"));
		}

		if (null == request.getRequestType() || request.getRequestType().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_TYPE_NULL_CODE"),
					messages.getString("REQUEST_TYPE_NULL"));
		}

		SubjectDetails subjectDetails = request.getSubjectDetails();

		if (null == subjectDetails) {
			throw exceptionHandler.constructAsmsException(messages.getString("SUBJECT_DETAILS_NULL_CODE"),
					messages.getString("SUBJECT_DETAILS_NULL_MSG"));
		}

		if (null == subjectDetails.getName() || subjectDetails.getName().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("	SUBJECT_NAME_NULL_CODE"),
					messages.getString("SUBJECT_NAME_NULL_MSG"));
		}

	}

	public void validateSectiontDetailsRequest(UserRequest request, ResourceBundle messages) throws AsmsException {
		if (null == request) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_NULL_CODE"),
					messages.getString("REQUEST_NULL"));
		}

		if (null == request.getRequestType() || request.getRequestType().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_TYPE_NULL_CODE"),
					messages.getString("REQUEST_TYPE_NULL"));
		}

		SectionDetails sectionDetails = request.getSectionDetails();

		if (null == sectionDetails) {
			throw exceptionHandler.constructAsmsException(messages.getString("SECTION_DETAILS_NULL_CODE"),
					messages.getString("SECTION_DETAILS_NULL_MSG"));
		}

		if (null == sectionDetails.getName() || sectionDetails.getName().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("	SECTION_NAME_NULL_CODE"),
					messages.getString("SECTION_NAME_NULL_MSG"));
		}

		if (null == sectionDetails.getAdditionalSubjectsDetails()
				|| sectionDetails.getAdditionalSubjectsDetails().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("	ADDITIONAL_SUBJECT_NULL_CODE"),
					messages.getString("ADDITIONAL_SUBJECT_NULL_MSG"));
		}

		if (null == sectionDetails.getSubjectDetails() || sectionDetails.getSubjectDetails().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("	SUBJECT_DETAILS_NULL_CODE"),
					messages.getString("SUBJECT_DETAILS_NULL_MSG"));
		}

	}*/

	public void validateTimeTableOnchangeDetails(TimeTableOnchangeDetails details, ResourceBundle messages)
			throws AsmsException {
		if (null == details) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_NULL_CODE"),
					messages.getString("REQUEST_NULL"));
		}

		if (null == details.getClassName() || details.getClassName().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("CLASSNAME_NULL_CODE"),
					messages.getString("CLASSNAME_NULL_MSG"));
		}

		if (null == details.getSectionName() || details.getSectionName().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SECTION_NULL_CODE"),
					messages.getString("SECTION_NULL_MSG"));
		}

		if (null == details.getTimeFrom() || details.getTimeFrom().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("PSTART_TIME_NULL_CODE"),
					messages.getString("PSTART_TIME_NULL_MSG"));
		}

		if (null == details.getTimeTo() || details.getTimeTo().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("PEND_TIME_NULL_CODE"),
					messages.getString("PEND_TIME_NULL_CODE"));
		}
		if (details.getTeacherId() <= 0) {
			throw exceptionHandler.constructAsmsException(messages.getString("TEACHER_ID_INVALID_CODE"),
					messages.getString("TEACHER_ID_INVALID_MSG"));
		}
		/*
		 * if(null ==details.getDay() || details.getDay().isEmpty() ||
		 * !details.getDay().equalsIgnoreCase("Monday") ||
		 * !details.getDay().equalsIgnoreCase("Tuesday")
		 * ||!details.getDay().equalsIgnoreCase("Wednesday") ||
		 * !details.getDay().equalsIgnoreCase("Thursday") ||
		 * !details.getDay().equalsIgnoreCase("Friday") ||
		 * !details.getDay().equalsIgnoreCase("Saturday")){ throw
		 * exceptionHandler.constructAsmsException(messages.getString(
		 * "TEACHER_ID_INVALID_CODE"),
		 * messages.getString("TEACHER_ID_INVALID_MSG")); }
		 */

	}

	public void validateTimeTableOnchangeDetails(String from, String to, String day, String className, String section,
			ResourceBundle messages) throws AsmsException {

		if (null == className || className.isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("CLASSNAME_NULL_CODE"),
					messages.getString("CLASSNAME_NULL_MSG"));
		}

		if (null == section || section.isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SECTION_NULL_CODE"),
					messages.getString("SECTION_NULL_MSG"));
		}

		if (null == from || from.isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("PSTART_TIME_NULL_CODE"),
					messages.getString("PSTART_TIME_NULL_MSG"));
		}

		if (null == to || to.isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("PEND_TIME_NULL_CODE"),
					messages.getString("PEND_TIME_NULL_CODE"));
		}
		/*
		 * if(null ==details.getDay() || details.getDay().isEmpty() ||
		 * !details.getDay().equalsIgnoreCase("Monday") ||
		 * !details.getDay().equalsIgnoreCase("Tuesday")
		 * ||!details.getDay().equalsIgnoreCase("Wednesday") ||
		 * !details.getDay().equalsIgnoreCase("Thursday") ||
		 * !details.getDay().equalsIgnoreCase("Friday") ||
		 * !details.getDay().equalsIgnoreCase("Saturday")){ throw
		 * exceptionHandler.constructAsmsException(messages.getString(
		 * "TEACHER_ID_INVALID_CODE"),
		 * messages.getString("TEACHER_ID_INVALID_MSG")); }
		 */
	}


public void validateAdditionalSubjectsRequest( String className, String section, ResourceBundle messages) throws AsmsException {
	

	if (null == className || className.isEmpty()) {
		throw exceptionHandler.constructAsmsException(messages.getString("CLASSNAME_NULL_CODE"),
				messages.getString("CLASSNAME_NULL_MSG"));
	}

	if (null == section || section.isEmpty()) {
		throw exceptionHandler.constructAsmsException(messages.getString("SECTION_NULL_CODE"),
				messages.getString("SECTION_NULL_MSG"));

}
}
}
