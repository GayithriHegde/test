/**
@{author} mallikarjun.guranna
10-Aug-2017
*/
package com.asms.usermgmt.entity.student;

import javax.persistence.CascadeType;

/* Class name : Address
 * 
 * Address class is the entity class for student_address table in DB
 */

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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
@Entity
@Table(name = "student_address")
public class StudentAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="student_id")  
	private Student studentObject;
	
	@Column(name ="student_permanent_address_line1")
	private String pAddressLine1;
	
	@Column(name ="student_paddress_line2")
	private String pAddressLine2;
	
	@Column(name ="student_paddress_country")
	private String pCountry;
	
	@Column(name ="student_paddress_state")
	private String pState;
	
	@Column(name ="student_paddress_district")
	private String pDistrict;
	
	@Column(name ="student_paddress_subdivision")
	private String pSubDivision;
	
	@Column(name ="student_paddress_tehsil")
	private String pTehsil;
	
	@Column(name ="student_paddress_village")
	private String pVillage;
	
	@Column(name ="student_paddress_pincode")
	private String pPincode;
	
	@Column(name ="student_paddress_location")
	private String pLocation;

	@Column(name ="student_corresponding_address_line1")
	private String cAddressLine1;
	
	@Column(name ="student_caddress_line2")
	private String cAddressLine2;
	
	@Column(name ="student_caddress_country")
	private String cCountry;
	
	@Column(name ="student_caddress_state")
	private String cState;
	
	@Column(name ="student_caddress_district")
	private String cDistrict;
	
	@Column(name ="student_caddress_subdivision")
	private String cSubDivision;
	
	@Column(name ="student_caddress_tehsil")
	private String cTehsil;
	
	@Column(name ="student_caddress_village")
	private String cVillage;
	
	@Column(name ="student_caddress_pincode")
	private String cPincode;
	
	@Column(name ="student_caddress_location")
	private String cLocation;

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	
	@JsonIgnore
	public Student getStudentObject() {
		return studentObject;
	}

	public void setStudentObject(Student studentObject) {
		this.studentObject = studentObject;
	}

	public String getpAddressLine1() {
		return pAddressLine1;
	}

	public void setpAddressLine1(String pAddressLine1) {
		this.pAddressLine1 = pAddressLine1;
	}

	public String getpAddressLine2() {
		return pAddressLine2;
	}

	public void setpAddressLine2(String pAddressLine2) {
		this.pAddressLine2 = pAddressLine2;
	}

	public String getpCountry() {
		return pCountry;
	}

	public void setpCountry(String pCountry) {
		this.pCountry = pCountry;
	}

	public String getpState() {
		return pState;
	}

	public void setpState(String pState) {
		this.pState = pState;
	}

	public String getpDistrict() {
		return pDistrict;
	}

	public void setpDistrict(String pDistrict) {
		this.pDistrict = pDistrict;
	}

	public String getpSubDivision() {
		return pSubDivision;
	}

	public void setpSubDivision(String pSubDivision) {
		this.pSubDivision = pSubDivision;
	}

	public String getpTehsil() {
		return pTehsil;
	}

	public void setpTehsil(String pTehsil) {
		this.pTehsil = pTehsil;
	}

	public String getpVillage() {
		return pVillage;
	}

	public void setpVillage(String pVillage) {
		this.pVillage = pVillage;
	}

	public String getpPincode() {
		return pPincode;
	}

	public void setpPincode(String pPincode) {
		this.pPincode = pPincode;
	}

	public String getpLocation() {
		return pLocation;
	}

	public void setpLocation(String pLocation) {
		this.pLocation = pLocation;
	}

	public String getcAddressLine1() {
		return cAddressLine1;
	}

	public void setcAddressLine1(String cAddressLine1) {
		this.cAddressLine1 = cAddressLine1;
	}

	public String getcAddressLine2() {
		return cAddressLine2;
	}

	public void setcAddressLine2(String cAddressLine2) {
		this.cAddressLine2 = cAddressLine2;
	}

	public String getcCountry() {
		return cCountry;
	}

	public void setcCountry(String cCountry) {
		this.cCountry = cCountry;
	}

	public String getcState() {
		return cState;
	}

	public void setcState(String cState) {
		this.cState = cState;
	}

	public String getcDistrict() {
		return cDistrict;
	}

	public void setcDistrict(String cDistrict) {
		this.cDistrict = cDistrict;
	}

	public String getcSubDivision() {
		return cSubDivision;
	}

	public void setcSubDivision(String cSubDivision) {
		this.cSubDivision = cSubDivision;
	}

	public String getcTehsil() {
		return cTehsil;
	}

	public void setcTehsil(String cTehsil) {
		this.cTehsil = cTehsil;
	}

	public String getcVillage() {
		return cVillage;
	}

	public void setcVillage(String cVillage) {
		this.cVillage = cVillage;
	}

	public String getcPincode() {
		return cPincode;
	}

	public void setcPincode(String cPincode) {
		this.cPincode = cPincode;
	}

	public String getcLocation() {
		return cLocation;
	}

	public void setcLocation(String cLocation) {
		this.cLocation = cLocation;
	}

	



}
