/**
@{author} mallikarjun.guranna
13-Sep-2017
*/
package com.asms.schoolmgmt.request;

import java.util.List;

public class SectionDetails {
	/**
	@{author} mallikarjun.guranna
	13-Sep-2017
	*/
	
	private String name;
	
	
	private List<SubjectDetails> subjectDetails;
	
	private List<AdditionalSubjectsDetails> additionalSubjectsDetails;	

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SubjectDetails> getSubjectDetails() {
		return subjectDetails;
	}

	public void setSubjectDetails(List<SubjectDetails> subjectDetails) {
		this.subjectDetails = subjectDetails;
	}

	public List<AdditionalSubjectsDetails> getAdditionalSubjectsDetails() {
		return additionalSubjectsDetails;
	}

	public void setAdditionalSubjectsDetails(List<AdditionalSubjectsDetails> additionalSubjectsDetails) {
		this.additionalSubjectsDetails = additionalSubjectsDetails;
	}

	
}
