/**
@{author} mallikarjun.guranna
13-Sep-2017
*/
package com.asms.schoolmgmt.entity;

//import java.util.HashSet;
//import java.util.Set;
//
//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "AdditionalSubjects")
public class AdditionalSubjects {
	/**
	 * @{author} mallikarjun.guranna 13-Sep-2017
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "serial_no")
	private int serialNo;

	@Column(name = "name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "section_id")
	private Section sectionObject;
	
	

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public Section getSectionObject() {
		return sectionObject;
	}

	public void setSectionObject(Section sectionObject) {
		this.sectionObject = sectionObject;
	}



	
}
