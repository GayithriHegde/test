package com.asms.usermgmt.response;

import java.util.List;

import com.asms.common.response.SuccessResponse;
import com.asms.multitenancy.entity.Nationality;
import com.asms.multitenancy.entity.Trust;
import com.asms.schoolmgmt.entity.AdditionalSubjects;
import com.asms.schoolmgmt.entity.Class;
import com.asms.schoolmgmt.entity.ClassSubjects;
import com.asms.schoolmgmt.request.SubjectDetails;
import com.asms.usermgmt.entity.StudentType;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.entity.student.Student;
import com.asms.usermgmt.request.UserBasicDetails;
import com.asms.usermgmt.request.UserDetails;
import com.asms.usermgmt.request.AkacartUserDetails.AkacartUserDetails;
import com.asms.usermgmt.request.student.StudentDetails;
import com.asms.usermgmt.service.CasteTypes;
import com.asms.usermgmt.service.QualificationType;
import com.asms.usermgmt.service.ReligionTypes;

/*
 * RegistrationResponse.java is to return user registration response
 */

public class GetUserResponse extends SuccessResponse {

	private UserDetails user;

	private List<User> users;

	private List<StudentType> studentTypes;

	private List<String> boardNames;

	private List<Class> classes;

	private List<UserDetails> userDetails;
	
	private List<UserBasicDetails> userBasicDetails;
	
	private List<Student> students;
	
	private AkacartUserDetails akacartUserDetails;
	
	List<String> siblingSerchResults;
	
	private StudentDetails studentDetais;

	public List<UserBasicDetails> getUserBasicDetails() {
		return userBasicDetails;
	}

	public void setUserBasicDetails(List<UserBasicDetails> userBasicDetails) {
		this.userBasicDetails = userBasicDetails;
	}

	private List<Integer> listOfStateId;
	
	private List<StudentDetails> studentDetails;
	
	private List<QualificationType> qualificationTypes;

	private List<ReligionTypes> religionTypes;
	
	private List<CasteTypes> casteTypes;
	
	private List<Trust> trusts;
	
	
	
	private List<String> emails;
	
//	private Section section;
	
//	private BroadCasteSearchTypesDetails broadCasteSearchTypesDetails2;
	
	private  List<ClassSubjects> classSubjects;
	
	private List<SubjectDetails> subjectDetails;
	
	private List<Nationality> nationalities;
	
	public List<Nationality> getNationalities() {
		return nationalities;
	}

	public void setNationalities(List<Nationality> nationalities) {
		this.nationalities = nationalities;
	}

	public List<SubjectDetails> getSubjectDetails() {
		return subjectDetails;
	}

	public void setSubjectDetails(List<SubjectDetails> subjectDetails) {
		this.subjectDetails = subjectDetails;
	}

	public List<AdditionalSubjects> getAdditionalSubjects() {
		return additionalSubjects;
	}

	public void setAdditionalSubjects(List<AdditionalSubjects> additionalSubjects) {
		this.additionalSubjects = additionalSubjects;
	}

	private  List<AdditionalSubjects> additionalSubjects;


	public List<ClassSubjects> getClassSubjects() {
		return classSubjects;
	}

	public void setClassSubjects(List<ClassSubjects> classSubjects) {
		this.classSubjects = classSubjects;
	}

	public UserDetails getUser() {
		return user;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<StudentType> getStudentTypes() {
		return studentTypes;
	}

	public void setStudentTypes(List<StudentType> studentTypes) {
		this.studentTypes = studentTypes;
	}

	public List<String> getBoardNames() {
		return boardNames;
	}

	public void setBoardNames(List<String> boardNames) {
		this.boardNames = boardNames;
	}

	

	public List<Class> getClasses() {
		return classes;
	}

	public void setClasses(List<Class> classes) {
		this.classes = classes;
	}

	public List<Integer> getListOfStateId() {
		return listOfStateId;
	}

	public void setListOfStateId(List<Integer> listOfStateId) {
		this.listOfStateId = listOfStateId;
	}

	public List<UserDetails> getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(List<UserDetails> userDetails) {
		this.userDetails = userDetails;
	}

	public List<StudentDetails> getStudentDetails() {
		return studentDetails;
	}

	public void setStudentDetails(List<StudentDetails> studentDetails) {
		this.studentDetails = studentDetails;
	}

	public List<QualificationType> getQualificationTypes() {
		return qualificationTypes;
	}

	public void setQualificationTypes(List<QualificationType> qualificationTypes) {
		this.qualificationTypes = qualificationTypes;
	}

	public List<ReligionTypes> getReligionTypes() {
		return religionTypes;
	}

	public void setReligionTypes(List<ReligionTypes> religionTypes) {
		this.religionTypes = religionTypes;
	}

	public List<CasteTypes> getCasteTypes() {
		return casteTypes;
	}

	public void setCasteTypes(List<CasteTypes> casteTypes) {
		this.casteTypes = casteTypes;
	}

	public List<Trust> getTrusts() {
		return trusts;
	}

	public void setTrusts(List<Trust> trusts) {
		this.trusts = trusts;
	}



	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public AkacartUserDetails getAkacartUserDetails() {
		return akacartUserDetails;
	}

	public void setAkacartUserDetails(AkacartUserDetails akacartUserDetails) {
		this.akacartUserDetails = akacartUserDetails;
	}

	public List<String> getSiblingSerchResults() {
		return siblingSerchResults;
	}

	public void setSiblingSerchResults(List<String> siblingSerchResults) {
		this.siblingSerchResults = siblingSerchResults;
	}

	public StudentDetails getStudentDetais() {
		return studentDetais;
	}

	public void setStudentDetais(StudentDetails studentDetais) {
		this.studentDetais = studentDetais;
	}

	
	

}
