package com.asms.usermgmt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="student_type")
public class StudentType 

{
	@Id
	@GenericGenerator(name="ref",strategy="increment")
	@GeneratedValue(generator="ref")
	@Column(name="serial_no")
	private int serialNo;
	
	@Column(name="student_type_name")
	private String studentTypeName;

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getStudentTypeName() {
		return studentTypeName;
	}

	public void setStudentTypeName(String studentTypeName) {
		this.studentTypeName = studentTypeName;
	}
	

}
