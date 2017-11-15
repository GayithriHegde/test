/**
mallikarjun.guranna
Oct 6, 2017
*/
package com.asms.attendance.helper;

import java.util.ResourceBundle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asms.Exception.AsmsException;
import com.asms.Exception.ExceptionHandler;
import com.asms.attendance.request.StaffAttendanceDetails;
import com.asms.attendance.request.StudentAttendanceDetails;
import com.asms.schoolmgmt.helper.ValidateAcademicYear;

@Component
public class ValidateAttendance {

	@Autowired
	private ExceptionHandler exceptionHandler;

	//private static final Logger logger = LoggerFactory.getLogger(ValidateAttendance.class);

	public void validateStudentAttendanceValues(StudentAttendanceDetails studentAttendanceDetails,
			ResourceBundle messages, String type) throws AsmsException {

		if (null == type) {
			throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_ATTENDANCE_TYPE_NULL_CODE"),
					messages.getString("STUDENT_ATTENDANCE_TYPE_NULL_MSG"));
		}
		if (null == studentAttendanceDetails) {
			throw exceptionHandler.constructAsmsException(messages.getString("ATTENDREQUEST_REQ_NULL_CODE"),
					messages.getString("ATTENDREQUEST_REQ_NULL_MSG"));
		}

		if (null != studentAttendanceDetails.getAcademicYear()) {
			if (!ValidateAcademicYear.validateAcademicYear(studentAttendanceDetails.getAcademicYear()))
				throw exceptionHandler.constructAsmsException(
						messages.getString("STUDENT_ATTENDANCE_ACADEMICYEAR_INVALID_CODE"),
						messages.getString("STUDENT_ATTENDANCE_ACADEMICYEAR_INVALID_MSG"));
		} else {

			throw exceptionHandler.constructAsmsException(
					messages.getString("STUDENT_ATTENDANCE_ACADEMICYEAR_NULL_CODE"),
					messages.getString("STUDENT_ATTENDANCE_ACADEMICYEAR_NULL_MSG"));

		}
		if (null == studentAttendanceDetails.getSectionName()
				|| studentAttendanceDetails.getSectionName().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_ATTENDANCE_SECTION_NULL_CODE"),
					messages.getString("STUDENT_ATTENDANCE_SECTION_NULL_MSG"));
		}

		if (null == studentAttendanceDetails.getClassName()
				|| studentAttendanceDetails.getClassName().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_ATTENDANCE_CLASS_NAME_NULL_CODE"),
					messages.getString("STUDENT_ATTENDANCE_CLASS_NAME_NULL_MSG"));
		}
		if (null == studentAttendanceDetails.getDate()
				|| studentAttendanceDetails.getDate().toString().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_ATTENDANCE_DATE_NULL_CODE"),
					messages.getString("STUDENT_ATTENDANCE_DATE_NULL_MSG"));
		}

		if (null == studentAttendanceDetails.getName() || studentAttendanceDetails.getName().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_ATTENDANCE_NAME_NULL_CODE"),
					messages.getString("STUDENT_ATTENDANCE_NAME_MSG"));
		}
		/*if (null == studentAttendanceDetails.getStatus() || studentAttendanceDetails.getStatus().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_ATTENDANCE_STATUS_NULL_CODE"),
					messages.getString("STUDENT_ATTENDANCE_STATUS_NULL_MSG"));
		}*/

	}

	public void validateStaffAttendanceValues(StaffAttendanceDetails staffAttendanceDetails, ResourceBundle messages,
			String type) throws AsmsException {

		if (null == type) {
			throw exceptionHandler.constructAsmsException(messages.getString("STAFF_ATTENDANCE_TYPE_NULL_CODE"),
					messages.getString("STAFF_ATTENDANCE_TYPE_NULL_MSG"));
		}
		if (null == staffAttendanceDetails) {
			throw exceptionHandler.constructAsmsException(messages.getString("ATTENDREQUEST_REQ_NULL_CODE"),
					messages.getString("ATTENDREQUEST_REQ_NULL_MSG"));
		}

		if (null != staffAttendanceDetails.getAcademicYear()) {
			if (!ValidateAcademicYear.validateAcademicYear(staffAttendanceDetails.getAcademicYear()))
				throw exceptionHandler.constructAsmsException(
						messages.getString("STAFF_ATTENDANCE_ACADEMICYEAR_INVALID_CODE"),
						messages.getString("STAFF_ATTENDANCE_ACADEMICYEAR_INVALID_MSG"));
		} else {

			throw exceptionHandler.constructAsmsException(messages.getString("STAFF_ATTENDANCE_ACADEMICYEAR_NULL_CODE"),
					messages.getString("STAFF_ATTENDANCE_ACADEMICYEAR_NULL_MSG"));

		}

		if (null == staffAttendanceDetails.getDate() || staffAttendanceDetails.getDate().toString().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("STAFF_ATTENDANCE_DATE_NULL_CODE"),
					messages.getString("STAFF_ATTENDANCE_DATE_NULL_MSG"));
		}

		if (null == staffAttendanceDetails.getName() || staffAttendanceDetails.getName().isEmpty() == true) {
			throw exceptionHandler.constructAsmsException(messages.getString("STAFF_ATTENDANCE_NAME_NULL_CODE"),
					messages.getString("STAFF_ATTENDANCE_NAME_MSG"));
		}

	}

}
