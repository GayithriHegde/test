package com.asms.attendance.response;

import java.util.List;

import com.asms.attendance.entity.StaffAttendance;
import com.asms.attendance.entity.StudentAttendance;
import com.asms.common.response.SuccessResponse;

public class GetAttendanceResponse extends SuccessResponse{
	
	private StudentAttendance studentAttendance;
	private List<StudentAttendance> studentAttendanceList;
	
	private StaffAttendance staffAttendance;
	private List<StaffAttendance> staffAttendanceList;
	
	
	public StudentAttendance getStudentAttendance() {
		return studentAttendance;
	}
	public void setStudentAttendance(StudentAttendance studentAttendance) {
		this.studentAttendance = studentAttendance;
	}
	public List<StudentAttendance> getStudentAttendanceList() {
		return studentAttendanceList;
	}
	public void setStudentAttendanceList(List<StudentAttendance> studentAttendanceList) {
		this.studentAttendanceList = studentAttendanceList;
	}
	public StaffAttendance getStaffAttendance() {
		return staffAttendance;
	}
	public void setStaffAttendance(StaffAttendance staffAttendance) {
		this.staffAttendance = staffAttendance;
	}
	public List<StaffAttendance> getStaffAttendanceList() {
		return staffAttendanceList;
	}
	public void setStaffAttendanceList(List<StaffAttendance> staffAttendanceList) {
		this.staffAttendanceList = staffAttendanceList;
	}

}
