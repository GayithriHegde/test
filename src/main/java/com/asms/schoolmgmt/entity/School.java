/**
@{author} mallikarjun.guranna
01-Sep-2017
*/
package com.asms.schoolmgmt.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "school_details")
public class School {
	/**
	 * @{author} mallikarjun.guranna 01-Sep-2017
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;

	

	@Column(name = "school_name")
	private String name;

	@Column(name = "school_affiliation_id")
	private String affiliationId;
	
	@Column(name = "trust_id")
	private int trustId;

	
	
	public int getTrustId() {
		return trustId;
	}

	public void setTrustId(int trustId) {
		this.trustId = trustId;
	}

	@XmlElement
	@OneToMany(mappedBy="schoolObject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<SchoolBoard> schoolBoardObjects;
	
	
	
	
	
	@Column(name = "school_registration_code")
	private String registrationCode;

	@Column(name = "school_website")
	private String website;

	@Column(name = "school_logo")
	private String logo;

	@Column(name = "school_address_line1")
	private String addressLine1;

	@Column(name = "school_address_line2")
	private String addressLine2;

	@Column(name = "school_location")
	private String location;

	@Column(name = "school_country")
	private String country;

	@Column(name = "school_state")
	private String state;

	@Column(name = "school_district")
	private String district;

	@Column(name = "school_subdivision")
	private String subdivision;

	@Column(name = "school_tehsil")
	private String tehsil;

	@Column(name = "school_village")
	private String village;

	@Column(name = "school_pincode")
	private int pincode;

	@Column(name = "school_contact_no1")
	private String contactNo1;

	@Column(name = "school_contact_no2")
	private String contactNo2;

	@Column(name = "school_mobile_no")
	private String mobileNo;

	@Column(name = "school_email_id")
	private String emailId;

	
	@Column(name = "school_total_no_of_students")
	private int totalNoOfStudents;

	@Column(name = "school_contact_person_name")
	private String contactPersonName;

	@Column(name = "school_contact_person_email_id")
	private String contactPersonEmailId;

	@Column(name = "school_gps_latitude")
	private double gpsLatitude;

	@Column(name = "school_gps_longitude")
	private double gpsLongitude;
	
	
	@Column(name = "board")
	private String boardName;
	
	

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAffiliationId() {
		return affiliationId;
	}

	public void setAffiliationId(String affiliationId) {
		this.affiliationId = affiliationId;
	}

	public String getRegistrationCode() {
		return registrationCode;
	}

	public void setRegistrationCode(String registrationCode) {
		this.registrationCode = registrationCode;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getSubdivision() {
		return subdivision;
	}

	public void setSubdivision(String subdivision) {
		this.subdivision = subdivision;
	}

	public String getTehsil() {
		return tehsil;
	}

	public void setTehsil(String tehsil) {
		this.tehsil = tehsil;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getContactNo1() {
		return contactNo1;
	}

	public void setContactNo1(String contactNo1) {
		this.contactNo1 = contactNo1;
	}

	public String getContactNo2() {
		return contactNo2;
	}

	public void setContactNo2(String contactNo2) {
		this.contactNo2 = contactNo2;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public int getTotalNoOfStudents() {
		return totalNoOfStudents;
	}

	public void setTotalNoOfStudents(int totalNoOfStudents) {
		this.totalNoOfStudents = totalNoOfStudents;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getContactPersonEmailId() {
		return contactPersonEmailId;
	}

	public void setContactPersonEmailId(String contactPersonEmailId) {
		this.contactPersonEmailId = contactPersonEmailId;
	}

	public double getGpsLatitude() {
		return gpsLatitude;
	}

	public void setGpsLatitude(double gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}

	public double getGpsLongitude() {
		return gpsLongitude;
	}

	public void setGpsLongitude(double gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
	}

	public Set<SchoolBoard> getSchoolBoardObjects() {
		return schoolBoardObjects;
	}

	public void setSchoolBoardObjects(Set<SchoolBoard> schoolBoardObjects) {
		this.schoolBoardObjects = schoolBoardObjects;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}


	
	

	

}
