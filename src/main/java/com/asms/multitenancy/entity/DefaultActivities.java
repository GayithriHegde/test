package com.asms.multitenancy.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

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
@Table(name = "activities_default")
public class DefaultActivities
{
	/**
	 * 
	 */
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="serial_no")
	private int serialNo;
	
	@Column(name= "activity_id")
	private int activityId;
	
	@Column(name= "role")
	private String role;
	
	@Column(name= "create_check")
	private String createCheck;
	
	@Column(name= "update_check")
	private String updateCheck;
	
	@Column(name= "retrieve_check")
	private String retrieveCheck;
	
	@Column(name= "delete_check")
	private String deleteCheck;

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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
