package com.asms.messagemgmt.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.asms.usermgmt.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/* Class name : Student
 * 
 * Student class is the entity class for student_details table in DB
 */

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "message")
public class Message implements Comparable<Message>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;


	@Column(name = "message")
	private String message;

	@Column(name = "academic_year")
	private String academicYear;

	@Column(name = "date")
	private String date;
	
	@Column(name = "class_name")
	private String className;
	
	@Column(name = "section_name")
	private String sectionName;

	

	@XmlElement
	@OneToMany(mappedBy="messageObject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<MessageReceiver> messageReceivers = new HashSet<MessageReceiver>();
  

	@Column(name = "receiverType")
	private String receiverType;   // all, all_parents, all_students, all_staff, all_teaching_staff, 
									// all_non_teaching_staff, all_management, parents_class_section, student_class_section, slected_students, selected_parents, 
									//selected_staff
	
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User userObject;

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	
	
	



	public Set<MessageReceiver> getMessageReceivers() {
		return messageReceivers;
	}

	public void setMessageReceivers(Set<MessageReceiver> messageReceivers) {
		this.messageReceivers = messageReceivers;
	}

	public User getUserObject() {
		return userObject;
	}

	public void setUserObject(User userObject) {
		this.userObject = userObject;
	}

	public String getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}

	@JsonIgnore
	public User getFromUserObject() {
		return userObject;
	}

	public void setFromUserObject(User userObject) {
		this.userObject = userObject;
	}
	
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	
	

	@Override
	public int compareTo(Message arg0) {
		return getDate().compareTo(arg0.getDate());
	}
	
	


}
