package com.asms.lessonmgmt.request;

import java.util.List;

import com.asms.lessonmgmt.entity.PeriodLessanPlan;

public class LessonPlanDetails {
	

	/**
	 * @{author} Praveen.Tiwari 9-Oct-2017
	 */
	
	private int unitId;
	
	private int totalPeriods;
	
	private String Objective;
	
	private String createdBy;
	
	private String resourcesText;
	
	private String resourcesPath;
	
	private String activitiesPlannedText;
	
	private String activitiesPlannedPath;
	
	private String classHomeAssignmentText;
	
	private String classHomeAssignmentPath;
	
	

	private String plannedAssignmentText;
	
	private String academicYear;
	
	private String plannedAssignmentPath;
	
	private List<PeriodLessanPlan> periodLessanPlans;

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

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	public String getPlannedAssignmentPath() {
		return plannedAssignmentPath;
	}

	public void setPlannedAssignmentPath(String plannedAssignmentPath) {
		this.plannedAssignmentPath = plannedAssignmentPath;
	}


	public List<PeriodLessanPlan> getPeriodLessanPlans() {
		return periodLessanPlans;
	}


	public void setPeriodLessanPlans(List<PeriodLessanPlan> periodLessanPlans) {
		this.periodLessanPlans = periodLessanPlans;
	}


	
	}
	
	
	

	
	
	

