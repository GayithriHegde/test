/**
@{author} mallikarjun.guranna
17-Aug-2017
*/
package com.asms.usermgmt.request.teachingStaff;

import com.asms.usermgmt.entity.nonTeachingStaff.NonTeachingStaff;

/*
 * StaffStatutoryDetails class  is the mapping class
 * to get Staff Statutory Details from front end
 * 
 */
public class StaffStatutoryDetails1 {
	/**
	@{author} mallikarjun.guranna
	17-Aug-2017
	*/
	
	private int serialNo;
	
	private NonTeachingStaff nTeachingObject;
	
	private String bankName;
	
	private String bankAccountNo;
	
	private String bankIfscCode;
	
	private String panNo;
	
	private String panCard;
	
	private String aadhaarNo;
	
	private String aadhaarCard;
	
	private String pfNo;

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
}
