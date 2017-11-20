package com.asms.usermgmt.entity.teachingStaff;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.asms.schoolmgmt.entity.TimeTable;
import com.asms.usermgmt.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Class name : TeachingStaff
 * This class is the Hibernate mapping Entity/model class to map teaching_staff table in DB
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "teaching_staff")
@PrimaryKeyJoinColumn(name = "serial_no", referencedColumnName = "serial_no")
public class TeachingStaff extends User {

	
	
	@XmlElement
	@OneToOne(mappedBy="teachingObject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Address1 address;
	
	@XmlElement
	@OneToOne(mappedBy="teachingObject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private StaffDocuments1 staffDocuments;
	
	
	
	@XmlElement
	@OneToOne(mappedBy="teachingObject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private StaffPreviousInformation1 staffPreviousInformation;
	
	@XmlElement
	@OneToOne(mappedBy="teachingObject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private StaffStatutory1 StaffStatutory;
	



	@OneToMany(mappedBy="teachingObject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<TimeTable> timeTables = new HashSet<TimeTable>();
	
	
	@Column(name = "t_staff_id")
	private String staffId;

	@Column(name = "t_staff_designation")
	private String designation;

	@Column(name = "t_staff_first_name")
	private String firstName;

	@Column(name = "t_staff_middle_name")
	private String middleName;

	@Column(name = "t_staff_last_name")
	private String lastName;
	
	@Column(name = "t_staff_dob")
	private Date Dob;

	@Column(name = "t_staff_gender")
	private String Gender;

	@Column(name = "t_staff_age_in_years")
	private int ageInYears;

	@Column(name = "t_staff_contact_no")
	private long contactNo;

	@Column(name = "t_staff_qualification")
	private String qualification;

	@Column(name = "t_staff_religion")
	private String religion;

	@Column(name = "t_staff_caste_category")
	private String casteCategory;

	@Column(name = "t_staff_photo")
	private String photo;

	@Column(name = "t_staff_marital_status")
	private String maritalStatus;

	@Column(name = "t_staff_spouse_name")
	private String spouseName;

	@Column(name = "t_staff_spouse_contact_no")
	private int spouseContactNo;

	@Column(name = "t_staff_created_by_wadmin")
	private String createdByWadmin;

	@Column(name = "t_staff_creation_time")
	private Date creationTime;

	@Column(name = "t_staff_status")
	private String acStatus;
	
	@Column(name ="blood_group")
	private String bloodGroup;
	
	@XmlElement
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "teachingObject", fetch = FetchType.EAGER)
	private List<TeachingSubjects> teachingSubjects = new ArrayList<TeachingSubjects>();
	
	

	@JsonIgnore
	public List<TeachingSubjects> getTeachingSubjects() {
		return teachingSubjects;
	}

	public void setTeachingSubjects(List<TeachingSubjects> teachingSubjects) {
		this.teachingSubjects = teachingSubjects;
	}
	@JsonIgnore
	public Address1 getAddress() {
		return address;
	}

	public void setAddress(Address1 address) {
		this.address = address;
	}

	public StaffDocuments1 getStaffDocuments() {
		return staffDocuments;
	}

	public void setStaffDocuments(StaffDocuments1 staffDocuments) {
		this.staffDocuments = staffDocuments;
	}

	public StaffPreviousInformation1 getStaffPreviousInformation() {
		return staffPreviousInformation;
	}

	public void setStaffPreviousInformation(StaffPreviousInformation1 staffPreviousInformation) {
		this.staffPreviousInformation = staffPreviousInformation;
	}

	public StaffStatutory1 getStaffStatutory() {
		return StaffStatutory;
	}

	public void setStaffStatutory(StaffStatutory1 staffStatutory) {
		StaffStatutory = staffStatutory;
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

	public Date getDob() {
		return Dob;
	}

	public void setDob(Date dob) {
		this.Dob = dob;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		this.Gender = gender;
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

	public int getSpouseContactNo() {
		return spouseContactNo;
	}

	public void setSpouseContactNo(int spouseContactNo) {
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

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public String getAcStatus() {
		return acStatus;
	}

	public void setAcStatus(String acStatus) {
		this.acStatus = acStatus;
	}
	
	@JsonIgnore
	public Set<TimeTable> getTimeTables() {
		return timeTables;
	}

	public void setTimeTables(Set<TimeTable> timeTables) {
		this.timeTables = timeTables;
	}

	

	public String getBlood_group() {
		return bloodGroup;
	}

	public void setBlood_group(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	

}
