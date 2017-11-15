/**
mallikarjun.guranna
Oct 6, 2017
*/
package com.asms.attendance.dao;

import java.util.List;

import com.asms.Exception.AsmsException;
import com.asms.attendance.request.StaffAttendanceDetails;
import com.asms.attendance.request.StudentAttendanceDetails;
import com.asms.usermgmt.request.UserBasicDetails;



public interface AttendanceDao {
	//this method returns list of students based on className and sectionName
	public List<UserBasicDetails> search(String className, String sectionName, String schema) throws AsmsException;
	
	public List<UserBasicDetails> getStaffMemberByRoleName(String roleName,String schema) throws AsmsException;
	
	public void insertStudentAttendanceDetails(StudentAttendanceDetails studentAttendanceDetails, String schema) throws AsmsException;

	public void insertStaffAttendanceDetails(StaffAttendanceDetails staffAttendanceDetails, String schema) throws AsmsException;
	
	public List<StudentAttendanceDetails> getAttendanceOfStudent(String studentId, String tenant)  throws AsmsException;
	
	
	public List<StaffAttendanceDetails> getAttendanceOfStaff(String staffId, String tenant) throws AsmsException;
}
