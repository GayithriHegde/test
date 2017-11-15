package com.asms.lessonmgmt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "Period_Lesson_Plan")
public class PeriodLessanPlan {
	
	/**
	 * @{author} Praveen.Tiwari 9-Oct-2017
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "serial_no")
	private int serialNo;
	
	@Column(name = "content")
	private String content;
	

	@ManyToOne
	@JoinColumn(name = "lessonPlan_id")
	private LessonPlan  lessonPlanObject;
	
	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LessonPlan getLessonPlanObject() {
		return lessonPlanObject;
	}

	public void setLessonPlanObject(LessonPlan lessonPlanObject) {
		this.lessonPlanObject = lessonPlanObject;
	}

	
}
