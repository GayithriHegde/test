package com.asms.messagemgmt.request;

import java.util.List;

import com.asms.usermgmt.request.UserBasicDetails;

public class BroadCasteSearchTypesDetails {

	
	
	private boolean allStudents;
	
	private boolean allParents;
	
	private boolean allManagement;
	
	private boolean allNonTeaching;
	
	private boolean allTeachingStaff;
	
	private boolean allUsers;
	
	private String className;
	
	private String section;
	
	private List<UserBasicDetails> users;
	
	private List<UserBasicDetails> students;
	
	private String isParent;  //if the message is intended for parents of selected students
	private String subject;

	private String message;
	
	private String selectAll;
	
	private String classSelectAll;
	
	public BroadCasteSearchTypesDetails(){
		isParent = "No";
		selectAll = "Yes";
		classSelectAll = "Yes";
	}
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public boolean isAllStudents() {
		return allStudents;
	}
	public void setAllStudents(boolean allStudents) {
		this.allStudents = allStudents;
	}
	public boolean isAllParents() {
		return allParents;
	}
	public void setAllParents(boolean allParents) {
		this.allParents = allParents;
	}
	public boolean isAllManagement() {
		return allManagement;
	}
	public void setAllManagement(boolean allManagement) {
		this.allManagement = allManagement;
	}
	public boolean isAllNonTeaching() {
		return allNonTeaching;
	}
	public void setAllNonTeaching(boolean allNonTeaching) {
		this.allNonTeaching = allNonTeaching;
	}
	
	
	public boolean isAllTeachingStaff() {
		return allTeachingStaff;
	}
	public void setAllTeachingStaff(boolean allTeachingStaff) {
		this.allTeachingStaff = allTeachingStaff;
	}
	public boolean isAllUsers() {
		return allUsers;
	}
	public void setAllUsers(boolean allUsers) {
		this.allUsers = allUsers;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public List<UserBasicDetails> getUsers() {
		return users;
	}
	public void setUsers(List<UserBasicDetails> users) {
		this.users = users;
	}
	public List<UserBasicDetails> getStudents() {
		return students;
	}
	public void setStudents(List<UserBasicDetails> students) {
		this.students = students;
	}
	
	
	
	public String getIsParent() {
		return isParent;
	}
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}


	public String getSelectAll() {
		return selectAll;
	}


	public void setSelectAll(String selectAll) {
		this.selectAll = selectAll;
	}


	public String getClassSelectAll() {
		return classSelectAll;
	}


	public void setClassSelectAll(String classSelectAll) {
		this.classSelectAll = classSelectAll;
	}
	
	
	
}
