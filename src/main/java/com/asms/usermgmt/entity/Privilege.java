package com.asms.usermgmt.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




/*
 * Entity Class: Country
 * 
 * This class is the Hibernate mapping entity class for activities_master table in DB
 * 
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
@Entity
@Table(name = "user_previliges")
public class Privilege
{
	/**
	 * 
	 */
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="serial_no")
	private int serialNo;
	
	@Column(name= "activity_category")
	private String activityCategory;
	
	@Column(name= "activity_name")
	private String activityName;
	
	@Column(name= "create_check")
	private String createCheck;
	
	@Column(name= "update_check")
	private String updateCheck;
	
	@Column(name= "retrieve_check")
	private String retrieveCheck;
	
	@Column(name= "delete_check")
	private String deleteCheck;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")  
	private User userObject;

	
	@JsonIgnore
	public User getUserObject() {
		return userObject;
	}

	public void setUserObject(User userObject) {
		this.userObject = userObject;
	}

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}



	public String getActivityCategory() {
		return activityCategory;
	}

	public void setActivityCategory(String activityCategory) {
		this.activityCategory = activityCategory;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getCreateCheck() {
		return createCheck;
	}

	public void setCreateCheck(String createCheck) {
		this.createCheck = createCheck;
	}

	public String getUpdateCheck() {
		return updateCheck;
	}

	public void setUpdateCheck(String updateCheck) {
		this.updateCheck = updateCheck;
	}

	public String getRetrieveCheck() {
		return retrieveCheck;
	}

	public void setRetrieveCheck(String retrieveCheck) {
		this.retrieveCheck = retrieveCheck;
	}

	public String getDeleteCheck() {
		return deleteCheck;
	}

	public void setDeleteCheck(String deleteCheck) {
		this.deleteCheck = deleteCheck;
	}

	
	
	

}
