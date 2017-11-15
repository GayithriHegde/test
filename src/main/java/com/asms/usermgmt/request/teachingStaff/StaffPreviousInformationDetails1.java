/**
@{author} mallikarjun.guranna
16-Aug-2017
*/
package com.asms.usermgmt.request.teachingStaff;

import com.asms.usermgmt.entity.nonTeachingStaff.NonTeachingStaff;


/*
 * StaffPreviousInformationDetails class  is the mapping class
 * to get Previous Information Details from front end
 * 
 */
public class StaffPreviousInformationDetails1 {
	/**
	@{author} mallikarjun.guranna
	16-Aug-2017
	*/
	private int serialNo;
		
	private NonTeachingStaff nTeachingObject;
	
	private boolean experienceFlag;
	
	private String lastWorkedOrganisation;
	
	private String dateOfJoining;
	
	private String relievingDate;
	
	private String experienceCertificate;
	
	private String lastDrawnPayslip;
	
	private String resume;
	

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public NonTeachingStaff getnTeachingObject() {
		return nTeachingObject;
	}

	public void setnTeachingObject(NonTeachingStaff nTeachingObject) {
		this.nTeachingObject = nTeachingObject;
	}

	public boolean isExperienceFlag() {
		return experienceFlag;
	}

	public void setExperienceFlag(boolean experienceFlag) {
		this.experienceFlag = experienceFlag;
	}

	public String getLastWorkedOrganisation() {
		return lastWorkedOrganisation;
	}

	public void setLastWorkedOrganisation(String lastWorkedOrganisation) {
		this.lastWorkedOrganisation = lastWorkedOrganisation;
	}

	

	public String getExperienceCertificate() {
		return experienceCertificate;
	}

	public void setExperienceCertificate(String experienceCertificate) {
		this.experienceCertificate = experienceCertificate;
	}

	public String getLastDrawnPayslip() {
		return lastDrawnPayslip;
	}

	public void setLastDrawnPayslip(String lastDrawnPayslip) {
		this.lastDrawnPayslip = lastDrawnPayslip;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getRelievingDate() {
		return relievingDate;
	}

	public void setRelievingDate(String relievingDate) {
		this.relievingDate = relievingDate;
	}


}
