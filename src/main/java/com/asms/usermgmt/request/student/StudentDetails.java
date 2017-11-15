package com.asms.usermgmt.request.student;

//import java.util.List;

import com.asms.usermgmt.entity.student.StudentAddress;
import com.asms.usermgmt.entity.student.StudentPreviousInfo;

/* StudentDetails class represents the student create object
 * for user management operations 
 * 
 */

public class StudentDetails {

	private String schoolId;

	private String studentId;

	private String admissionNo;

	private String studentType;

	private String admissionDate;

	private String studentClass;

	private String studentSection;

	private String studentFirstName;

	private String studentMiddleName;

	private String studentLastName;

	private String studentDob;

	private String studentGender;

	private String studentIdentificationMarks;

	private int studentAgeInYears;

	private String studentBirthplace;

	private String studentNationality;

	private String studentReligion;

	private String studentCasteCategory;

	private String studentSubCaste;

	private String studentMotherTongue;

	private String studentCreatedByWadmin;

	private String studentRegisteredOn;

	private String studentPhoto;
	
	private long emergencyContactNo;
	
	private String status;
	
	private String bloodGroup;
	
	private ParentDetails parentDetails;
	
	private StudentAddressDetails addressDetails;
	
	private StudentDocumentDetails documentDetails;
	
	private SiblingDetails siblingDetails;
	
	private StudentPreviousDetails previousDetails;
	
	private StudentDocumentDetails studentDocumentDetails;
	
	private StudentPreviousInfo studentPreviousInfo;
	
	private StudentAddress studentAddress;
	

	public ParentDetails getParentDetails() {
		return parentDetails;
	}

	public void setParentDetails(ParentDetails parentDetails) {
		this.parentDetails = parentDetails;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getAdmissionNo() {
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}

	public String getStudentType() {
		return studentType;
	}

	public void setStudentType(String studentType) {
		this.studentType = studentType;
	}

	
	public String getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}

	public String getStudentSection() {
		return studentSection;
	}

	public void setStudentSection(String studentSection) {
		this.studentSection = studentSection;
	}

	public String getStudentFirstName() {
		return studentFirstName;
	}

	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}

	public String getStudentMiddleName() {
		return studentMiddleName;
	}

	public void setStudentMiddleName(String studentMiddleName) {
		this.studentMiddleName = studentMiddleName;
	}

	public String getStudentLastName() {
		return studentLastName;
	}

	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}

	

	public String getStudentGender() {
		return studentGender;
	}

	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}

	public String getStudentIdentificationMarks() {
		return studentIdentificationMarks;
	}

	public void setStudentIdentificationMarks(String studentIdentificationMarks) {
		this.studentIdentificationMarks = studentIdentificationMarks;
	}

	public int getStudentAgeInYears() {
		return studentAgeInYears;
	}

	public void setStudentAgeInYears(int studentAgeInYears) {
		this.studentAgeInYears = studentAgeInYears;
	}

	public String getStudentBirthplace() {
		return studentBirthplace;
	}

	public void setStudentBirthplace(String studentBirthplace) {
		this.studentBirthplace = studentBirthplace;
	}

	public String getStudentNationality() {
		return studentNationality;
	}

	public void setStudentNationality(String studentNationality) {
		this.studentNationality = studentNationality;
	}

	public String getStudentReligion() {
		return studentReligion;
	}

	public void setStudentReligion(String studentReligion) {
		this.studentReligion = studentReligion;
	}

	public String getStudentCasteCategory() {
		return studentCasteCategory;
	}

	public void setStudentCasteCategory(String studentCasteCategory) {
		this.studentCasteCategory = studentCasteCategory;
	}

	public String getStudentSubCaste() {
		return studentSubCaste;
	}

	public void setStudentSubCaste(String studentSubCaste) {
		this.studentSubCaste = studentSubCaste;
	}

	public String getStudentMotherTongue() {
		return studentMotherTongue;
	}

	public void setStudentMotherTongue(String studentMotherTongue) {
		this.studentMotherTongue = studentMotherTongue;
	}

	public String getStudentCreatedByWadmin() {
		return studentCreatedByWadmin;
	}

	public void setStudentCreatedByWadmin(String studentCreatedByWadmin) {
		this.studentCreatedByWadmin = studentCreatedByWadmin;
	}

	

	public String getStudentPhoto() {
		return studentPhoto;
	}

	public void setStudentPhoto(String studentPhoto) {
		this.studentPhoto = studentPhoto;
	}

	public long getEmergencyContactNo() {
		return emergencyContactNo;
	}

	public void setEmergencyContactNo(long emergencyContactNo) {
		this.emergencyContactNo = emergencyContactNo;
	}

	public StudentAddressDetails getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(StudentAddressDetails addressDetails) {
		this.addressDetails = addressDetails;
	}

	public StudentDocumentDetails getDocumentDetails() {
		return documentDetails;
	}

	public void setDocumentDetails(StudentDocumentDetails documentDetails) {
		this.documentDetails = documentDetails;
	}

	public StudentPreviousDetails getPreviousDetails() {
		return previousDetails;
	}

	public void setPreviousDetails(StudentPreviousDetails previousDetails) {
		this.previousDetails = previousDetails;
	}

	public String getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getStudentDob() {
		return studentDob;
	}

	public void setStudentDob(String studentDob) {
		this.studentDob = studentDob;
	}

	public String getStudentRegisteredOn() {
		return studentRegisteredOn;
	}

	public void setStudentRegisteredOn(String studentRegisteredOn) {
		this.studentRegisteredOn = studentRegisteredOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public SiblingDetails getSiblingDetails() {
		return siblingDetails;
	}

	public void setSiblingDetails(SiblingDetails siblingDetails) {
		this.siblingDetails = siblingDetails;
	}

	public StudentDocumentDetails getStudentDocumentDetails() {
		return studentDocumentDetails;
	}

	public void setStudentDocumentDetails(StudentDocumentDetails studentDocumentDetails) {
		this.studentDocumentDetails = studentDocumentDetails;
	}

	public StudentPreviousInfo getStudentPreviousInfo() {
		return studentPreviousInfo;
	}

	public void setStudentPreviousInfo(StudentPreviousInfo studentPreviousInfo) {
		this.studentPreviousInfo = studentPreviousInfo;
	}

	public StudentAddress getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(StudentAddress studentAddress) {
		this.studentAddress = studentAddress;
	}


	
	
	
	
	
	

}
