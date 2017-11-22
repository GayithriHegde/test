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

	
	private boolean experienceFlag;
	
	private String lastWorkedOrganisation;
	
	private String dateOfJoining;
	
	private String relievingDate;
	
	
	




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
