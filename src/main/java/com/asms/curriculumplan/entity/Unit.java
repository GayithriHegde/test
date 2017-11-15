package com.asms.curriculumplan.entity;

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

/* Class name : Unit
 * 
 * Unit class is the entity class for student_details table in DB
 */

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "unit")
public class Unit{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;


	@Column(name = "unit_no")
	private String unitNo;

	@Column(name = "unit_name")
	private String unitName;

	@Column(name = "marks")
	private int marks;
	
	@Column(name = "no_of_periods")
	private String noOfPeriods;
	
	@Column(name = "planned_month")
	private String month;
	
	@Column(name = "status")
	private String status;
	
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ManyToOne
	@JoinColumn(name = "curriculum_id")
	private CurriculumPlan curriculumObject;

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}



	public String getUnitNo() {
		return unitNo;
	}

	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

/*	public int getNoOfPeriods() {
		return noOfPeriods;
	}

	public void setNoOfPeriods(int noOfPeriods) {
		this.noOfPeriods = noOfPeriods;
	}
*/
	public String getMonth() {
		return month;
	}

	public String getNoOfPeriods() {
		return noOfPeriods;
	}

	public void setNoOfPeriods(String noOfPeriods) {
		this.noOfPeriods = noOfPeriods;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@JsonIgnore
	public CurriculumPlan getCurriculumObject() {
		return curriculumObject;
	}

	public void setCurriculumObject(CurriculumPlan curriculumObject) {
		this.curriculumObject = curriculumObject;
	}

	
	


}
