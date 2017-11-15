package com.asms.examination.entity;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/* Class name : Marks
 * 
 * Marks class is the Mapping entity class for marks table in DB
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "marks")
public class Marks {

	/**
	@{author} Devendra Singh
	23-oct-2017
	*/
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	
	@Column(name = "subject_id")
	private String subjectId;
	
	
	@Column(name = "marks")
	private int marks;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "examName")
	private String examName;
	
	@XmlElement
	@ManyToOne
	@JoinColumn(name = "marks_master_id")
	private MarksMaster marksMasterObject;

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public MarksMaster getMarksMasterObject() {
		return marksMasterObject;
	}

	public void setMarksMasterObject(MarksMaster marksMasterObject) {
		this.marksMasterObject = marksMasterObject;
	}
	
	public String getExamName() {
		return examName;
	}


	public void setExamName(String examName) {
		this.examName = examName;
	}
	

}
