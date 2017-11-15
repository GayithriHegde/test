package com.asms.calendarMgmt.Request;

public class EventDetails {
	
	/**
	@{author} karishma.k
	9-oct-2017
	*/
	private int id;
	
	private String fromDate ;
	
	private String todate ;
	
	private String fromTime ;
	
	private String toTime ;
	
	private String academicYear ;
	
	private String name ;
	
	private String recurrencePattern ;
	
	

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getTodate() {
		return todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRecurrencePattern() {
		return recurrencePattern;
	}

	public void setRecurrencePattern(String recurrencePattern) {
		this.recurrencePattern = recurrencePattern;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
