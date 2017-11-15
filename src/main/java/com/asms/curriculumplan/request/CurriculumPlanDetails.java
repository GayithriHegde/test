package com.asms.curriculumplan.request;

/* Class name : CurriculumPlanDetails
 * 
 * CurriculumPlanDetails class is used to map curriculum details to curriculum plan entity
 */

public class CurriculumPlanDetails{	
	
	private int id;
	
	private String className;
	
	private String sectionName;	
	
	private String subject;	
	
	private String unitNo;
	
	private String unitName;	
	
	private int marks;
	
	private String noOfPeriods;
	
	private String plannedForMonth;
	
	private String status;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	

	
	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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
	}*/

	public String getPlannedForMonth() {
		return plannedForMonth;
	}

	public String getNoOfPeriods() {
		return noOfPeriods;
	}

	public void setNoOfPeriods(String noOfPeriods) {
		this.noOfPeriods = noOfPeriods;
	}

	public void setPlannedForMonth(String plannedForMonth) {
		this.plannedForMonth = plannedForMonth;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	

	

  

	


}
