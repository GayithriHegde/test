package com.asms.lessonmgmt.entity;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "Lesson_Plan")
public class LessonPlan {
	
	/**
	 * @{author} Praveen.Tiwari 9-Oct-2017
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "serial_no")
	private int serialNo;
	

	@Column(name = "unit_id")
	private int unitId;
	
	@Column(name = "no_of_periods")
	private int totalPeriods;
	
	@Column(name = "objective")
	private String Objective;
	
	@Column(name = "created_By")
	private String createdBy;
	
	
	@Column(name = "resources_text")
	private String resourcesText;
	
	@Column(name = "resources_patht")
	private String resourcesPath;
	
	@Column(name = "activities_planned_text")
	private String activitiesPlannedText;
	
	@Column(name = "activities_planned_Path")
	private String activitiesPlannedPath;
	
	@Column(name = "class_home_assignment_text")
	private String classHomeAssignmentText;
	
	@Column(name = "class_home_assignment_path")
	private String classHomeAssignmentPath;
	
	@Column(name = "planned_assignment_text")
	private String plannedAssignmentText;
	
	@Column(name = "academic_year")
	private String academicyear;
	
	@Column(name = "planned_assignment_path")
	private String plannedAssignmentPath;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "lessonPlanObject",fetch=FetchType.EAGER)
	private List<PeriodLessanPlan> periodLessanPlans = new ArrayList< PeriodLessanPlan >();


	
	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public int getTotalPeriods() {
		return totalPeriods;
	}

	public void setTotalPeriods(int totalPeriods) {
		this.totalPeriods = totalPeriods;
	}

	public String getObjective() {
		return Objective;
	}

	public void setObjective(String objective) {
		Objective = objective;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getResourcesText() {
		return resourcesText;
	}

	public void setResourcesText(String resourcesText) {
		this.resourcesText = resourcesText;
	}

	public String getResourcesPath() {
		return resourcesPath;
	}

	public void setResourcesPath(String resourcesPath) {
		this.resourcesPath = resourcesPath;
	}

	public String getActivitiesPlannedText() {
		return activitiesPlannedText;
	}

	public void setActivitiesPlannedText(String activitiesPlannedText) {
		this.activitiesPlannedText = activitiesPlannedText;
	}

	public String getActivitiesPlannedPath() {
		return activitiesPlannedPath;
	}

	public void setActivitiesPlannedPath(String activitiesPlannedPath) {
		this.activitiesPlannedPath = activitiesPlannedPath;
	}

	public String getClassHomeAssignmentText() {
		return classHomeAssignmentText;
	}

	public void setClassHomeAssignmentText(String classHomeAssignmentText) {
		this.classHomeAssignmentText = classHomeAssignmentText;
	}

	public String getClassHomeAssignmentPath() {
		return classHomeAssignmentPath;
	}

	public void setClassHomeAssignmentPath(String classHomeAssignmentPath) {
		this.classHomeAssignmentPath = classHomeAssignmentPath;
	}

	public String getPlannedAssignmentText() {
		return plannedAssignmentText;
	}

	public void setPlannedAssignmentText(String plannedAssignmentText) {
		this.plannedAssignmentText = plannedAssignmentText;
	}

	public String getAcademicyear() {
		return academicyear;
	}

	public void setAcademicyear(String academicyear) {
		this.academicyear = academicyear;
	}

	public String getPlannedAssignmentPath() {
		return plannedAssignmentPath;
	}

	public void setPlannedAssignmentPath(String plannedAssignmentPath) {
		this.plannedAssignmentPath = plannedAssignmentPath;
	}

	
	@JsonIgnore
	public List<PeriodLessanPlan> getPeriodLessanPlans() {
		return periodLessanPlans;
	}

	public void setPeriodLessanPlans(List<PeriodLessanPlan> periodLessanPlans) {
		this.periodLessanPlans = periodLessanPlans;
	}

	
	
}
