package com.asms.usermgmt.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.asms.messagemgmt.entity.Message;
import com.asms.rolemgmt.entity.Role;
import com.asms.rolemgmt.entity.SubRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/* user class is the base class for all roles
 * and maps to user_information table
 */

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
@Entity
@Table(name = "user_information")
@Inheritance(strategy=InheritanceType.JOINED)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	
	@Column(name = "school_id")
	private int schoolId;
	
	@Column(name="account_status")
	private String accountStatus;

	
	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String userPassword;
	
	@Column(name = "isNew")
	private String isNew;
	
	@Column(name = "admission_for_year",nullable=false)
    private String admissionForYear;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role roleObject;
	
	
	@Transient
	private String sessionToken;
	
	@Transient
	private String domain;
	
	

	
	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@ManyToOne
	@JoinColumn(name = "sub_role_id")
	private SubRole subRoleObject;	
	
	@XmlElement
	@OneToMany(mappedBy="userObject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Privilege> privileges = new HashSet<Privilege>();
	
	@XmlElement
	@OneToMany(mappedBy="userObject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Message> messages = new HashSet<Message>();


	public String getUserId() {
		return userId;
	}

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Role getRoleObject() {
		return roleObject;
	}

	public void setRoleObject(Role roleObject) {
		this.roleObject = roleObject;
	}

	public SubRole getSubRoleObject() {
		return subRoleObject;
	}

	public void setSubRoleObject(SubRole subRoleObject) {
		this.subRoleObject = subRoleObject;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	@JsonIgnore
	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

	@JsonIgnore
	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public String getAdmissionForYear() {
		return admissionForYear;
	}

	public void setAdmissionForYear(String admissionForYear) {
		this.admissionForYear = admissionForYear;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	

	
	
	

	
}
