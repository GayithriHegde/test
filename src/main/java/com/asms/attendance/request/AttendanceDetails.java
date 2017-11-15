/**
mallikarjun.guranna
Oct 6, 2017
*/
package com.asms.attendance.request;

import java.util.List;

public class AttendanceDetails {
	
	
private List<StudentAttendanceDetails> studentAttendanceDetails;
	
	private List<StaffAttendanceDetails> staffAttendanceDetails;

	public List<StudentAttendanceDetails> getStudentAttendanceDetails() {
		return studentAttendanceDetails;
	}

	public void setStudentAttendanceDetails(List<StudentAttendanceDetails> studentAttendanceDetails) {
		this.studentAttendanceDetails = studentAttendanceDetails;
	}

	public List<StaffAttendanceDetails> getStaffAttendanceDetails() {
		return staffAttendanceDetails;
	}

	public void setStaffAttendanceDetails(List<StaffAttendanceDetails> staffAttendanceDetails) {
		this.staffAttendanceDetails = staffAttendanceDetails;
	}

}
