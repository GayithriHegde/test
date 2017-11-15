/**
@{author} mallikarjun.guranna
16-Aug-2017
*/
package com.asms.usermgmt.entity.teachingStaff;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/* Class name : StaffPreviousInformation1
 * 
 * StaffPreviousInformation class is the Mapping entity class for teaching_staff_previous_information table in DB
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
@Entity 
@Table(name = "teaching_staff_previous_information")
public class StaffPreviousInformation1 {
	/**
	@{author} mallikarjun.guranna
	16-Aug-2017
	*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="t_staff_id")  
	private TeachingStaff teachingObject;
	
	@Column(name ="t_staff_experience_flag")
	private boolean experienceFlag;
	
	@Column(name ="t_staff_last_worked_organisation")
	private String lastWorkedOrganisation;
	
	@Column(name ="t_staff_date_of_joining")
	private Date dateOfJoining;
	
	@Column(name ="t_staff_relieving_date")
	private Date relievingDate;
	
	@Column(name ="t_staff_experience_certificate")
	private String experienceCertificate;
	
	@Column(name ="t_staff_last_drawn_payslip")
	private String lastDrawnPayslip;
	
	@Column(name ="t_staff_resume")
	private String resume;

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
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

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public Date getRelievingDate() {
		return relievingDate;
	}

	public void setRelievingDate(Date relievingDate) {
		this.relievingDate = relievingDate;
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

	public TeachingStaff getTeachingObject() {
		return teachingObject;
	}

	public void setTeachingObject(TeachingStaff teachingObject) {
		this.teachingObject = teachingObject;
	}
	
}
