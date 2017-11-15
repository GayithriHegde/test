package com.asms.examination.entity;

import java.util.Date;

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
/* Class name : ExaminationDetails
 * 
 * ExaminationDetails class is the Mapping entity class for examination_details table in DB
 */

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "examination_details")
public class ExaminationDetails {

	/**
	@{author} Devendra Singh
	12-oct-2017
	*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	
	@Column(name = "class_name")
	private String className;
	
	
	@Column(name = "exam_date")
	private Date examDate;
	
	@Column(name = "timeFrom")
	private String timeFrom;

	@Column(name = "timeTo")
	private String timeTo;
	
	
	@Column(name = "subject_name")
	private String subjectName;
	
	@Column(name = "marks")
	private int marks;
	
	@XmlElement
	@ManyToOne
	@JoinColumn(name = "examination_id")
	private Examination examinationObject;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public String getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}

	public String getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public Examination getExaminationObject() {
		return examinationObject;
	}

	public void setExaminationObject(Examination examinationObject) {
		this.examinationObject = examinationObject;
	}

	

}
