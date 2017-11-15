/**
@{author} mallikarjun.guranna
17-Aug-2017
*/
package com.asms.usermgmt.entity.teachingStaff;

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
/* Class name : StaffStatutory1
 * 
 * StaffStatutory class is the Mapping entity class for teaching_staff_statutory_details table in DB
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
@Entity 
@Table(name = "teaching_staff_statutory_details")
public class StaffStatutory1 {
	/**
	@{author} mallikarjun.guranna
	17-Aug-2017
	*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="t_staff_id")  
	private TeachingStaff teachingObject;
	
	@Column(name ="t_staff_bank_name")
	private String bankName;
	
	@Column(name ="t_staff_bank_account_no")
	private String bankAccountNo;
	
	@Column(name ="t_staff_bank_ifsc_code")
	private String bankIfscCode;
	
	@Column(name ="t_staff_pan_no")
	private String panNo;
	
	@Column(name ="t_staff_pan_card")
	private String panCard;
	
	@Column(name ="t_staff_aadhaar_no")
	private String aadhaarNo;
	
	@Column(name ="t_staff_aadhaar_card")
	private String aadhaarCard;
	
	@Column(name ="t_staff_pf_no")
	private String pfNo;

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}


	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public String getBankIfscCode() {
		return bankIfscCode;
	}

	public void setBankIfscCode(String bankIfscCode) {
		this.bankIfscCode = bankIfscCode;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getPanCard() {
		return panCard;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	public String getAadhaarNo() {
		return aadhaarNo;
	}

	public void setAadhaarNo(String aadhaarNo) {
		this.aadhaarNo = aadhaarNo;
	}

	public String getAadhaarCard() {
		return aadhaarCard;
	}

	public void setAadhaarCard(String aadhaarCard) {
		this.aadhaarCard = aadhaarCard;
	}

	public String getPfNo() {
		return pfNo;
	}

	public void setPfNo(String pfNo) {
		this.pfNo = pfNo;
	}

	public TeachingStaff getTeachingObject() {
		return teachingObject;
	}

	public void setTeachingObject(TeachingStaff teachingObject) {
		this.teachingObject = teachingObject;
	}
}
