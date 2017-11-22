package com.asms.studentAdmission.request;

public class UserReq {
	
	private AdmissionDetails admissionDetails;
	private AdmissionEnquiryDetails admissionEnquiryDetails;
	private ApplicationStatusDetails applicationStatusDetails;
	public AdmissionDetails getAdmissionDetails() {
		return admissionDetails;
	}
	public void setAdmissionDetails(AdmissionDetails admissionDetails) {
		this.admissionDetails = admissionDetails;
	}
	public AdmissionEnquiryDetails getAdmissionEnquiryDetails() {
		return admissionEnquiryDetails;
	}
	public void setAdmissionEnquiryDetails(AdmissionEnquiryDetails admissionEnquiryDetails) {
		this.admissionEnquiryDetails = admissionEnquiryDetails;
	}
	public ApplicationStatusDetails getApplicationStatusDetails() {
		return applicationStatusDetails;
	}
	public void setApplicationStatusDetails(ApplicationStatusDetails applicationStatusDetails) {
		this.applicationStatusDetails = applicationStatusDetails;
	}

}
