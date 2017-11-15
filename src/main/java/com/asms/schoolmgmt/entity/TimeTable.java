/**
@{author} Gayithri.HG
13-Sep-2017
*/
package com.asms.schoolmgmt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.asms.usermgmt.entity.teachingStaff.TeachingStaff;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "TimeTable")
public class TimeTable {
	/**
	 * @{author} Gayithri.HG 30-Sep-2017
	 */

	@Id
	@GeneratedValue

	@Column(name = "serialNo")
	private int serialNo;

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}

	@Column(name = "day")
	private String day;

	@Column(name = "timeFrom")
	private String timeFrom;

	@Column(name = "timeTo")
	private String timeTo;

	@ManyToOne
	@JoinColumn(name = "section_id")
	private Section sectionObject;
	
	@Column(name = "subject")
	private String subjectName;
	
	@Column(name = "subjectType")
	private String subjectType;

	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private TeachingStaff teachingObject;

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
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

	@JsonIgnore
	public Section getSectionObject() {
		return sectionObject;
	}

	public void setSectionObject(Section sectionObject) {
		this.sectionObject = sectionObject;
	}



	public TeachingStaff getTeachingObject() {
		return teachingObject;
	}

	public void setTeachingObject(TeachingStaff teachingObject) {
		this.teachingObject = teachingObject;
	}



}
