package com.asms.studentAdmission.dao;

import com.asms.Exception.AsmsException;
import com.asms.studentAdmission.request.AdmissionDetails;
import com.asms.studentAdmission.request.AdmissionEnquiryDetails;
import com.asms.studentAdmission.request.ApplicationStatusDetails;
import com.asms.usermgmt.entity.User;

public interface AdmissionRprtDao {
	
	public void createAdmission(AdmissionDetails admissionDetails,User user,String domain)  throws AsmsException;
	public void createAdmissionEnquiry(AdmissionEnquiryDetails admissionEnquiryDetails,User user,String domain)  throws AsmsException;
	public void createApplicationStatus(ApplicationStatusDetails a,User b ,String c)  throws AsmsException;

}
