package com.asms.usermgmt.entity.student;

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



/* Class name : Sibling
 * 
 * Parent class is the entity class for sibling_details table in DB
 */

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "student_siblings")
public class Sibling {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="student_id")  
	private Student studentObject;	
	
	@Column(name = "sibling_id")
	private int siblingId;
	
	
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

	public int getSiblingId() {
		return siblingId;
	}

	public void setSiblingId(int siblingId) {
		this.siblingId = siblingId;
	}
	


}
