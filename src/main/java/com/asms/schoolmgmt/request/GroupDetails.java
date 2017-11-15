/**
@{author} gayithri.hegde
25-Sep-2017
*/
package com.asms.schoolmgmt.request;

import java.util.List;

import com.asms.schoolmgmt.entity.Breaks;

public class GroupDetails {
	/**
	 * @{author} gayithri.hg 25-Sep-2017
	 */

	private String requestType;
	private List<ClassDetails> classDetails;
	private String startTime;
	private String endTime;
	private String periodDuration;  // in minutes
	
	private WeaklyHolidayDetails weaklyHolidayDetails;

	private List<Breaks> breaks; 

	

	public List<ClassDetails> getClassDetails() {
		return classDetails;
	}

	public void setClassDetails(List<ClassDetails> classDetails) {
		this.classDetails = classDetails;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	

	public List<Breaks> getBreaks() {
		return breaks;
	}

	public void setBreaks(List<Breaks> breaks) {
		this.breaks = breaks;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	
	public String getPeriodDuration() {
		return periodDuration;
	}

	public void setPeriodDuration(String periodDuration) {
		this.periodDuration = periodDuration;
	}

	public WeaklyHolidayDetails getWeaklyHolidayDetails() {
		return weaklyHolidayDetails;
	}

	public void setWeaklyHolidayDetails(WeaklyHolidayDetails weaklyHolidayDetails) {
		this.weaklyHolidayDetails = weaklyHolidayDetails;
	}
	

}
