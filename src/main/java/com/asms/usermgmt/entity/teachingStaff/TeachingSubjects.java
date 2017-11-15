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
@Table(name = "teaching_staff_subjects")
public class TeachingSubjects {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;

	@Column(name = "subject_name")
	private String subject;
	
	
	@ManyToOne
	@JoinColumn(name = "t_staff_id")
	private TeachingStaff teachingObject;

	@XmlElement
	@ManyToOne
	@JoinColumn(name = "class_id")
	private Class classObject;
	
	
	@ManyToOne
	@JoinColumn(name = "section_id")
	private Section sectionObject;

	@JsonIgnore
	public Section getSectionObject() {
		return sectionObject;
	}

	public void setSectionObject(Section sectionObject) {
		this.sectionObject = sectionObject;
	}

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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@JsonIgnore
	public Class getClassObject() {
		return classObject;
	}

	public void setClassObject(Class classObject) {
		this.classObject = classObject;
	}



	

}
