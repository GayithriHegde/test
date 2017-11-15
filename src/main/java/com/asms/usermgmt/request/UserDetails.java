package com.asms.usermgmt.request;
import java.util.Set;

import com.asms.lessonmgmt.request.LessonPlanDetails;
import com.asms.lessonmgmt.request.PeriodLessonPlansDetails;
import com.asms.usermgmt.entity.Privilege;
//import com.asms.usermgmt.entity.nonTeachingStaff.Address;
//import com.asms.usermgmt.entity.student.StudentAddress;
//import com.asms.usermgmt.entity.student.StudentPreviousInfo;
import com.asms.usermgmt.request.management.ManagementDetails;
//import com.asms.usermgmt.request.nonTeachingStaff.AddressDetails;
import com.asms.usermgmt.request.nonTeachingStaff.NonTeachingStaffDetails;
//import com.asms.usermgmt.request.nonTeachingStaff.StaffDocumentsDetails;
//import com.asms.usermgmt.request.nonTeachingStaff.StaffPreviousInformationDetails;
//import com.asms.usermgmt.request.nonTeachingStaff.StaffStatutoryDetails;
//import com.asms.usermgmt.request.student.ParentDetails;
//import com.asms.usermgmt.request.student.SiblingDetails;
import com.asms.usermgmt.request.student.StudentDetails;
//import com.asms.usermgmt.request.student.StudentDocumentDetails;
//import com.asms.usermgmt.request.teachingStaff.StaffDocumentsDetails1;
//import com.asms.usermgmt.request.teachingStaff.StaffPreviousInformationDetails1;
//import com.asms.usermgmt.request.teachingStaff.StaffStatutoryDetails1;
import com.asms.usermgmt.request.teachingStaff.TeachingStaffDetails;

/* UserDetails class represents the user create request object
 * for user management operations 
 * 
 */

public class UserDetails {

	private String userId;	
	private String accountStatus;
	
	private String admissionForYear;
	
	private String email;	
	
	private String userPassword;	
	
	private String role;	
	
	private String subRole;
	
	private AdminDetails adminDetails;
	
	private StudentDetails studentDetails;
	
	
	private ManagementDetails managementDetails;
	
	private TeachingStaffDetails teachingStaffDetails;
	
	private NonTeachingStaffDetails nonTeachingStaffDetails;
	
	
	private ChangePasswordDetails changePasswordDetails;
	
	private Set<Privilege> privileges;
	
	private LessonPlanDetails lessonPlanDetails;
	
	private PeriodLessonPlansDetails periodLessonPlansDetails;

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSubRole() {
		return subRole;
	}

	public void setSubRole(String subRole) {
		this.subRole = subRole;
	}


	public StudentDetails getStudentDetails() {
		return studentDetails;
	}

	public void setStudentDetails(StudentDetails studentDetails) {
		this.studentDetails = studentDetails;
	}

	public ManagementDetails getManagementDetails() {
		return managementDetails;
	}

	public void setManagementDetails(ManagementDetails managementDetails) {
		this.managementDetails = managementDetails;
	}

	public TeachingStaffDetails getTeachingStaffDetails() {
		return teachingStaffDetails;
	}

	public void setTeachingStaffDetails(TeachingStaffDetails teachingStaffDetails) {
		this.teachingStaffDetails = teachingStaffDetails;
	}

	public NonTeachingStaffDetails getNonTeachingStaffDetails() {
		return nonTeachingStaffDetails;
	}

	public void setNonTeachingStaffDetails(NonTeachingStaffDetails nonTeachingStaffDetails) {
		this.nonTeachingStaffDetails = nonTeachingStaffDetails;
	}

	

	public ChangePasswordDetails getChangePasswordDetails() {
		return changePasswordDetails;
	}

	public void setChangePasswordDetails(ChangePasswordDetails changePasswordDetails) {
		this.changePasswordDetails = changePasswordDetails;
	}

	public AdminDetails getAdminDetails() {
		return adminDetails;
	}

	public void setAdminDetails(AdminDetails adminDetails) {
		this.adminDetails = adminDetails;
	}

	public LessonPlanDetails getLessonPlanDetails() {
		return lessonPlanDetails;
	}

	public void setLessonPlanDetails(LessonPlanDetails lessonPlanDetails) {
		this.lessonPlanDetails = lessonPlanDetails;
	}

	public PeriodLessonPlansDetails getPeriodLessonPlansDetails() {
		return periodLessonPlansDetails;
	}

	public void setPeriodLessonPlansDetails(PeriodLessonPlansDetails periodLessonPlansDetails) {
		this.periodLessonPlansDetails = periodLessonPlansDetails;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getAdmissionForYear() {
		return admissionForYear;
	}

	public void setAdmissionForYear(String admissionForYear) {
		this.admissionForYear = admissionForYear;
	}


}
