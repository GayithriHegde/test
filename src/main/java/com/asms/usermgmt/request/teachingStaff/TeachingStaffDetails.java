/**
@{author} mallikarjun.guranna
08-Aug-2017
*/
package com.asms.usermgmt.request.teachingStaff;

import java.util.List;

import com.asms.usermgmt.request.TeachingSubjectDetails;
import com.asms.usermgmt.request.nonTeachingStaff.AddressDetails;
import com.asms.usermgmt.request.nonTeachingStaff.StaffDocumentsDetails;
import com.asms.usermgmt.request.nonTeachingStaff.StaffPreviousInformationDetails;
import com.asms.usermgmt.request.nonTeachingStaff.StaffStatutoryDetails;

public class TeachingStaffDetails {
	/**
	@{author} mallikarjun.guranna
	08-Aug-2017
	*/

	private String schoolId;
	private String designation;
	private String bloodGroup;
	private String firstName;
	private String middleName;
	private String lastName;
	private String dateOfBirth;
	private String gender;
	private int ageInYears;
	private long contactNo;
	private String qualification;
	private String religion;
	private String casteCategory;
	private String photo;
	private String maritalStatus;
	private String createdByWadmin;
	private String creationTime;
	private String acStatus;
	
	private List<TeachingSubjectDetails> teachingSubjectDetailsList;
	
	private AddressDetails addressDetails;
	
	private StaffDocumentsDetails staffDocumentsDetails;
	
	private StaffPreviousInformationDetails staffPreviousInformationDetails;
	
	private StaffStatutoryDetails staffStatutoryDetails;
	
	private AddressDetails1 addressDetails1;
	
	private StaffDocumentsDetails1 staffDocumentsDetails1;
	
	private StaffPreviousInformationDetails1 staffPreviousInformationDetails1;
	
	private StaffStatutoryDetails1 statutoryDetails1;
	

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAgeInYears() {
		return ageInYears;
	}

	public void setAgeInYears(int ageInYears) {
		this.ageInYears = ageInYears;
	}

	public long getContactNo() {
		return contactNo;
	}

	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getCasteCategory() {
		return casteCategory;
	}

	public void setCasteCategory(String casteCategory) {
		this.casteCategory = casteCategory;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	

	public String getCreatedByWadmin() {
		return createdByWadmin;
	}

	public void setCreatedByWadmin(String createdByWadmin) {
		this.createdByWadmin = createdByWadmin;
	}

	

	public String getAcStatus() {
		return acStatus;
	}

	public void setAcStatus(String acStatus) {
		this.acStatus = acStatus;
	}

	public List<TeachingSubjectDetails> getTeachingSubjectDetailsList() {
		return teachingSubjectDetailsList;
	}

	public void setTeachingSubjectDetailsList(List<TeachingSubjectDetails> teachingSubjectDetailsList) {
		this.teachingSubjectDetailsList = teachingSubjectDetailsList;
	}

	public AddressDetails getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(AddressDetails addressDetails) {
		this.addressDetails = addressDetails;
	}

	public StaffDocumentsDetails getStaffDocumentsDetails() {
		return staffDocumentsDetails;
	}

	public void setStaffDocumentsDetails(StaffDocumentsDetails staffDocumentsDetails) {
		this.staffDocumentsDetails = staffDocumentsDetails;
	}

	public StaffPreviousInformationDetails getStaffPreviousInformationDetails() {
		return staffPreviousInformationDetails;
	}

	public void setStaffPreviousInformationDetails(StaffPreviousInformationDetails staffPreviousInformationDetails) {
		this.staffPreviousInformationDetails = staffPreviousInformationDetails;
	}

	public StaffStatutoryDetails getStaffStatutoryDetails() {
		return staffStatutoryDetails;
	}

	public void setStaffStatutoryDetails(StaffStatutoryDetails staffStatutoryDetails) {
		this.staffStatutoryDetails = staffStatutoryDetails;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public AddressDetails1 getAddressDetails1() {
		return addressDetails1;
	}

	public void setAddressDetails1(AddressDetails1 addressDetails1) {
		this.addressDetails1 = addressDetails1;
	}

	public StaffDocumentsDetails1 getStaffDocumentsDetails1() {
		return staffDocumentsDetails1;
	}

	public void setStaffDocumentsDetails1(StaffDocumentsDetails1 staffDocumentsDetails1) {
		this.staffDocumentsDetails1 = staffDocumentsDetails1;
	}

	public StaffPreviousInformationDetails1 getStaffPreviousInformationDetails1() {
		return staffPreviousInformationDetails1;
	}

	public void setStaffPreviousInformationDetails1(StaffPreviousInformationDetails1 staffPreviousInformationDetails1) {
		this.staffPreviousInformationDetails1 = staffPreviousInformationDetails1;
	}

	public StaffStatutoryDetails1 getStatutoryDetails1() {
		return statutoryDetails1;
	}

	public void setStatutoryDetails1(StaffStatutoryDetails1 statutoryDetails1) {
		this.statutoryDetails1 = statutoryDetails1;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}


	

	
	
}
