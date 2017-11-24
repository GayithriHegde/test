package com.asms.usermgmt.entity.teachingStaff;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.asms.schoolmgmt.entity.Class;
import com.asms.schoolmgmt.entity.Section;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Class name : TeachingStaff
 * This class is the Hibernate mapping Entity/model class to map teaching_staff table in DB
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "teaching_staff_classes")
public class TeachingClasses {

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;

	@Column(name = "class_name")
	private String className;
	
	
	@ManyToOne
	@JoinColumn(name = "t_staff_id")
	private TeachingStaff teachingObject;

	

	

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	@JsonIgnore
	public TeachingStaff getTeachingObject() {
		return teachingObject;
	}

	public void setTeachingObject(TeachingStaff teachingObject) {
		this.teachingObject = teachingObject;
	}


	


	

}
