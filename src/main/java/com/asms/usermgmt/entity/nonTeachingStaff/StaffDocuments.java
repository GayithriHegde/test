/**
@{author} mallikarjun.guranna
16-Aug-2017
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/* Class name : StaffDocuments
 * 
 * StaffDocuments class is the Mapping entity class for non_teaching_staff_documents table in DB
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
@Entity 
@Table(name = "non_teaching_staff_documents")
public class StaffDocuments {
	/**
	@{author} mallikarjun.guranna
	16-Aug-2017
	*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="nt_staff_id")  
	private NonTeachingStaff nTeachingObject;
	
	@Column(name = "nt_staff_10th_certificate")
	private String ten10thCertificate;
	
	@Column(name = "nt_staff_12th_certificate")
	private String twelve12thCertificate;
	
	@Column(name = "nt_staff_degree_certificate")
	private String degreeCertificate;
	
	@Column(name = "nt_staff_pg_degree_certificate")
	private String pgDegreeCertificate;
	
	@Column(name = "nt_staff_medical_fitness_certificate")
	private String medicalFitnessCertificate;
	
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
	public String getTen10thCertificate() {
		return ten10thCertificate;
	}
	public void setTen10thCertificate(String ten10thCertificate) {
		this.ten10thCertificate = ten10thCertificate;
	}
	public String getTwelve12thCertificate() {
		return twelve12thCertificate;
	}
	public void setTwelve12thCertificate(String twelve12thCertificate) {
		this.twelve12thCertificate = twelve12thCertificate;
	}
	public String getDegreeCertificate() {
		return degreeCertificate;
	}
	public void setDegreeCertificate(String degreeCertificate) {
		this.degreeCertificate = degreeCertificate;
	}
	public String getPgDegreeCertificate() {
		return pgDegreeCertificate;
	}
	public void setPgDegreeCertificate(String pgDegreeCertificate) {
		this.pgDegreeCertificate = pgDegreeCertificate;
	}
	public String getMedicalFitnessCertificate() {
		return medicalFitnessCertificate;
	}
	public void setMedicalFitnessCertificate(String medicalFitnessCertificate) {
		this.medicalFitnessCertificate = medicalFitnessCertificate;
	}

	
}
