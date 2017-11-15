package com.asms.examination.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.asms.examination.entity.ExaminationDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/* Class name : Examination
 * 
 * Examination class is the Mapping entity class for examination table in DB
 */

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "examination")
public class Examination 
{
	/**
	@{author} Devendra Singh
	12-oct-2017
	*/
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	
	
	@Column(name = "exam_name")
	private String examName;
	
	@Column(name = "board_id")
	private int boardId;
	
	
	@Column(name = "academic_year")
	private String academicYear;
	
	@Column(name = "total_marks")
	private int totalMarks;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "examinationObject",fetch = FetchType.EAGER)
	private List<ExaminationDetails> examinationDetails = new ArrayList<ExaminationDetails>();


	public int getSerialNo() {
		return serialNo;
	}


	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}


	public String getExamName() {
		return examName;
	}


	public void setExamName(String examName) {
		this.examName = examName;
	}


	public int getBoardId() {
		return boardId;
	}


	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}


	public String getAcademicYear() {
		return academicYear;
	}


	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}


	


	public int getTotalMarks() {
		return totalMarks;
	}


	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	@JsonIgnore
	public List<ExaminationDetails> getExaminationDetails() {
		return examinationDetails;
	}


	public void setExaminationDetails(List<ExaminationDetails> examinationDetails) {
		this.examinationDetails = examinationDetails;
	}


	


	
}
