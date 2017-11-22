package com.asms.studentAdmission.helper;

import org.springframework.stereotype.Component;

import com.asms.studentAdmission.entity.Admission;
import com.asms.studentAdmission.entity.AdmissionEnquiry;
import com.asms.studentAdmission.entity.ApplicationStatus;
import com.asms.studentAdmission.request.AdmissionDetails;
import com.asms.studentAdmission.request.AdmissionEnquiryDetails;
import com.asms.studentAdmission.request.ApplicationStatusDetails;

@Component
public class EntityMaker {
	
	public Admission createAdmissionEntity(AdmissionDetails admissionDetails)
	{
		Admission admission = new Admission();
		admission.setStudentName(admissionDetails.getStudentName());
		admission.setStudentType(admissionDetails.getStudentType());
		admission.setAdminssionID(admissionDetails.getAdminssionID());
		admission.setAdminssionYear(admissionDetails.getAdminssionYear());
		admission.setAdminssionDate(admissionDetails.getAdminssionDate());
		admission.setGender(admissionDetails.getGender());
		admission.setClassName(admissionDetails.getClassName());
		admission.setPhnNumber(admissionDetails.getPhnNumber());
		admission.setDob(admissionDetails.getDob());
		admission.setBirthPlcae(admissionDetails.getBirthPlcae());
		admission.setCaste(admissionDetails.getCaste());
		
		return admission;
		
	}
	
	
	public AdmissionEnquiry createAdmissionEnquiryEntity(AdmissionEnquiryDetails admissionEnquiryDetails)
	{
		AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();
		admissionEnquiry.setStudentName(admissionEnquiryDetails.getStudentName());
		admissionEnquiry.setAdmissionAppliedDate(admissionEnquiryDetails.getAdmissionAppliedDate());
		admissionEnquiry.setGender(admissionEnquiryDetails.getGender());
		admissionEnquiry.setPhoneNumber(admissionEnquiryDetails.getPhoneNumber());
		admissionEnquiry.setClassApplied(admissionEnquiryDetails.getClassApplied());
		admissionEnquiry.setPreviousSchoolName(admissionEnquiryDetails.getPreviousSchoolName());
		admissionEnquiry.setPreviousSchoolBoard(admissionEnquiryDetails.getPreviousSchoolBoard());
		admissionEnquiry.setDob(admissionEnquiryDetails.getDob());
		
		return admissionEnquiry;
		
	}
	
	
	
	public ApplicationStatus createApplicationStatusEntity(ApplicationStatusDetails applicationStatusDetails)
	{
		ApplicationStatus applicationStatus = new ApplicationStatus();
		applicationStatus.setStudentName(applicationStatusDetails.getStudentName());
		applicationStatus.setAdmissionAppliedDate(applicationStatusDetails.getAdmissionAppliedDate());
		applicationStatus.setGender(applicationStatusDetails.getGender());
		applicationStatus.setPhoneNumber(applicationStatusDetails.getPhoneNumber());
		applicationStatus.setClassApplied(applicationStatusDetails.getClassApplied());
		applicationStatus.setPreviousSchoolName(applicationStatusDetails.getPreviousSchoolName());
		applicationStatus.setPreviousSchoolBoard(applicationStatusDetails.getPreviousSchoolBoard());
		applicationStatus.setDob(applicationStatusDetails.getDob());
		applicationStatus.setStatus(applicationStatusDetails.getStatus());
		
		return applicationStatus;
		
	}
}
