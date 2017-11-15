package com.asms.usermgmt.entity.akacartUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Class name : AkacartUser
 * This class is Hibernate mapping Entity to Akacart_User table in DB
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "Akacart_User")
public class AkacartUser {
	/**
	 * @{author} Praveen.Tiwari 10-Nov-2017
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;	
	
	@Column(name = "user_id")	
	private String userId;
	
	@Column(name = "akacart_access")
	private boolean akakartAccess = false;
	
	@Column(name = "created_on")
	private String createdOn;

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isAkakartAccess() {
		return akakartAccess;
	}

	public void setAkakartAccess(boolean akakartAccess) {
		this.akakartAccess = akakartAccess;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	
}
