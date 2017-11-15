package com.asms.usermgmt.entity.student;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/* Class name : Parent
 * 
 * Parent class is the entity class for parent_details table in DB
 */

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "parent_details")
public class Parent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="student_id")  
	private Student studentObject;
	
	

	@Column(name = "father_first_name")
	private String fFirstName;

	@Column(name = "father_middle_name")
	private String fMiddleName;
	
	@Column(name = "father_last_name")
	private String fLastName;
	
	@Column(name = "father_qualification")
	private String fQualification;
	
	@Column(name = "father_occupation")
	private String fOccupation;
	
	@Column(name = "father_contact_no")
	private long fContactNumber;
	
	@Column(name = "father_email_id")
	private String fEmail;
	
	@Column(name = "father_password")
	private String fpassword;
	
	
	@Column(name = "father_annual_income")
	private int fIncome;

	@Column(name = "mother_first_name")
	private String mFirstName;

	@Column(name = "mother_middle_name")
	private String mMiddleName;

	@Column(name = "mother_last_name")
	private String mLastName;
	
	@Column(name = "mother_qualification")
	private String mQualification;
	
	@Column(name = "mother_occupation")
	private String mOccupation;
	
	@Column(name = "mother_contact_no")
	private long mContactNumber;
	
	@Column(name = "mother_email_id")
	private String mEmail;
	
	@Column(name = "mother_password")
	private String mpassword;
	
	@Column(name = "mother_annual_income")
	private int mIncome;
	
	@Column(name = "parent_created_by_wadmin")
	private String createdBy;
	
	@Column(name = "parent_creation_time")
	private Date createdOn;
	
	@Column(name = "isNew")
	private String isNew;
	
	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	
	@JsonIgnore
	public Student getStudentObject() {
		return studentObject;
	}

	public void setStudentObject(Student studentObject) {
		this.studentObject = studentObject;
	}


	

	public String getfFirstName() {
		return fFirstName;
	}

	public void setfFirstName(String fFirstName) {
		this.fFirstName = fFirstName;
	}

	public String getfMiddleName() {
		return fMiddleName;
	}

	public void setfMiddleName(String fMiddleName) {
		this.fMiddleName = fMiddleName;
	}

	public String getfLastName() {
		return fLastName;
	}

	public void setfLastName(String fLastName) {
		this.fLastName = fLastName;
	}

	public String getfQualification() {
		return fQualification;
	}

	public void setfQualification(String fQualification) {
		this.fQualification = fQualification;
	}

	public String getfOccupation() {
		return fOccupation;
	}

	public void setfOccupation(String fOccupation) {
		this.fOccupation = fOccupation;
	}

	public long getfContactNumber() {
		return fContactNumber;
	}

	public void setfContactNumber(long fContactNumber) {
		this.fContactNumber = fContactNumber;
	}

	public String getfEmail() {
		return fEmail;
	}

	public void setfEmail(String fEmail) {
		this.fEmail = fEmail;
	}

	public int getfIncome() {
		return fIncome;
	}

	public void setfIncome(int fIncome) {
		this.fIncome = fIncome;
	}

	public String getmFirstName() {
		return mFirstName;
	}

	public void setmFirstName(String mFirstName) {
		this.mFirstName = mFirstName;
	}

	public String getmMiddleName() {
		return mMiddleName;
	}

	public void setmMiddleName(String mMiddleName) {
		this.mMiddleName = mMiddleName;
	}

	public String getmLastName() {
		return mLastName;
	}

	public void setmLastName(String mLastName) {
		this.mLastName = mLastName;
	}

	public String getmQualification() {
		return mQualification;
	}

	public void setmQualification(String mQualification) {
		this.mQualification = mQualification;
	}

	public String getmOccupation() {
		return mOccupation;
	}

	public void setmOccupation(String mOccupation) {
		this.mOccupation = mOccupation;
	}

	public long getmContactNumber() {
		return mContactNumber;
	}

	public void setmContactNumber(long mContactNumber) {
		this.mContactNumber = mContactNumber;
	}

	public String getmEmail() {
		return mEmail;
	}

	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	public int getmIncome() {
		return mIncome;
	}

	public void setmIncome(int mIncome) {
		this.mIncome = mIncome;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getFpassword() {
		return fpassword;
	}

	public void setFpassword(String fpassword) {
		this.fpassword = fpassword;
	}

	public String getMpassword() {
		return mpassword;
	}

	public void setMpassword(String mpassword) {
		this.mpassword = mpassword;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
	
	

}
