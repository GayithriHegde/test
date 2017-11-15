/**
@{author} mallikarjun.guranna
10-Aug-2017
*/
package com.asms.usermgmt.request.teachingStaff;
/*
 * AddressDetails is the mapping class
 * to get Address Details from front end
 * 
 */
public class AddressDetails1 {
	/**
	@{author} mallikarjun.guranna
	10-Aug-2017
	*/
	private int siNo;
	
	private String addressLine1;
	
	private String addressLine2;
	
	private String country;
	
	private String state;
	
	private String district;
	
	private String subDivision;
	
	private String tehsil;
	
	private String village;
	
	private int pincode;

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getSubDivision() {
		return subDivision;
	}

	public void setSubDivision(String subDivision) {
		this.subDivision = subDivision;
	}

	public String getTehsil() {
		return tehsil;
	}

	public void setTehsil(String tehsil) {
		this.tehsil = tehsil;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public int getSiNo() {
		return siNo;
	}

	public void setSiNo(int siNo) {
		this.siNo = siNo;
	}
}
