package com.asms.messagemgmt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/* Class name : Student
 * 
 * Student class is the entity class for message_receiver table in DB
 */

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "message_receiver")
public class MessageReceiver {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	
	@ManyToOne
	@JoinColumn(name = "message_id")
	private Message messageObject;


	@Column(name = "userId")
	private String userId;
	
	@Column(name = "name")
	private String Name;
	
	@Column(name = "is_read")
	private boolean isRead;
	
	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	@Column( name = "isParent")
	private String isParent;
	
	
	
	public MessageReceiver(){
		isParent = "no";
	}


	public int getSerialNo() {
		return serialNo;
	}


	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}


	@JsonIgnore
	public Message getMessageObject() {
		return messageObject;
	}


	public void setMessageObject(Message messageObject) {
		this.messageObject = messageObject;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getIsParent() {
		return isParent;
	}


	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}


	public boolean isRead() {
		return isRead;
	}


	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}



	
	
	
	


}
