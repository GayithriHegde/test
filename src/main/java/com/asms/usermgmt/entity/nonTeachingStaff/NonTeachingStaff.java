/**
@{author} mallikarjun.guranna
09-Aug-2017
*/
package com.asms.usermgmt.entity.nonTeachingStaff;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.asms.usermgmt.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
@Entity
@Table(name = "non_teaching_staff")
@PrimaryKeyJoinColumn(name = "serial_no", referencedColumnName = "serial_no")
public class NonTeachingStaff extends User{
	/**
	@{author} mallikarjun.guranna
	09-Aug-2017
	*/

	@XmlElement
	@OneToOne(mappedBy="nTeachingObject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Address address;
	
	@XmlElement
	@OneToOne(mappedBy="nTeachingObject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private StaffDocuments staffDocuments;
	
	@XmlElement
	@OneToOne(mappedBy="nTeachingObject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private StaffPreviousInformation staffPreviousInformation;
	
	@XmlElement
	@OneToOne(mappedBy="nTeachingObject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private StaffStatutory StaffStatutory;
	
	
	
	@Column(name = "nt_staff_designation")
	private String designation;
	
	@Column(name = "nt_staff_first_name")
	private String firstName;
	
	@Column(name = "nt_staff_middle_name")
	private String middleName;
	
	@Column(name = "nt_staff_last_name")
	private String lastName;
	
	@Column(name = "nt_staff_dob")
	private Date dob;
	@Column(name = "nt_staff_gender")
	private String gender;
	@Column(name = "nt_staff_age_in_years")
	private int ageInYears;
	
	@Column(name = "nt_staff_contact_no")
	private long contactNo;
	
	@Column(name = "nt_staff_qualification")
	private String qualification;
	
	@Column(name = "nt_staff_religion")
	private String religion;
	
	@Column(name = "nt_staff_caste_category")
	private String casteCategory;
	
	@Column(name = "nt_staff_photo")
	private String photo;
	
	@Column(name = "nt_staff_marital_status")
	private String maritalStatus;
	
	@Column(name = "nt_staff_spouse_name")
	private String spouseName;
	
	@Column(name = "nt_staff_spouse_contact_no")
	private long spouseContactNo;
	
	@Column(name = "nt_staff_created_by_wadmin")
	private String createdByWadmin;
	
	@Column(name = "nt_staff_creation_time")
	private Date creationTime;
	
	@Column(name = "nt_staff_status")
	private String status;
	
	@Column(name ="blood_group")
	private String bloodGroup;
	
	
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
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	public long getSpouseContactNo() {
		return spouseContactNo;
	}
	public void setSpouseContactNo(long spouseContactNo) {
		this.spouseContactNo = spouseContactNo;
	}
	public String getCreatedByWadmin() {
		return createdByWadmin;
	}
	public void setCreatedByWadmin(String createdByWadmin) {
		this.createdByWadmin = createdByWadmin;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date date) {
		this.creationTime = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@JsonIgnore
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public StaffDocuments getStaffDocuments() {
		return staffDocuments;
	}
	public void setStaffDocuments(StaffDocuments staffDocuments) {
		this.staffDocuments = staffDocuments;
	}
	public StaffPreviousInformation getStaffPreviousInformation() {
		return staffPreviousInformation;
	}
	public void setStaffPreviousInformation(StaffPreviousInformation staffPreviousInformation) {
		this.staffPreviousInformation = staffPreviousInformation;
	}
	public StaffStatutory getStaffStatutory() {
		return StaffStatutory;
	}
	public void setStaffStatutory(StaffStatutory staffStatutory) {
		StaffStatutory = staffStatutory;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
 
	
	
	
	
}
