/**
@{author} mallikarjun.guranna
10-Aug-2017
*/
package com.asms.usermgmt.entity.nonTeachingStaff;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/* Class name : Address
 * 
 * Address class is the Mapping entity class for non_teaching_staff_address table in DB
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
@Entity 
@Table(name = "non_teaching_staff_address")
public class Address {
	/**
	@{author} mallikarjun.guranna
	10-Aug-2017
	*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="nt_staff_id")  
	private NonTeachingStaff nTeachingObject;
	
	
	
	@Column(name ="nt_staff_address_line1")
	private String addressLine1;
	
	@Column(name ="nt_staff_address_line2")
	private String addressLine2;
	
	@Column(name ="nt_staff_country")
	private String country;
	
	@Column(name ="nt_staff_state")
	private String state;
	
	@Column(name ="nt_staff_district")
	private String district;
	
	@Column(name ="nt_staff_subdivision")
	private String subDivision;
	
	@Column(name ="nt_staff_tehsil")
	private String tehsil;
	
	@Column(name ="nt_staff_village")
	private String village;
	
	@Column(name ="nt_staff_pincode")
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

	@JsonIgnore
	public NonTeachingStaff getnTeachingObject() {
		return nTeachingObject;
	}

	public void setnTeachingObject(NonTeachingStaff nTeachingObject) {
		this.nTeachingObject = nTeachingObject;
	}

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	





}
