package com.asms.CountryMgmt.Entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




/*
 * Entity Class: Country
 * 
 * This class is the Hibernate mapping entity class for country table in DB
 * 
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
@Entity
@Table(name = "country")
public class Country
{
	/**
	 * 
	 */
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="serial_no")
	private int siNo;
	
	@Column(name= "country_name")
	private String countryName;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "countryObject")
	private List<StateEntity> list;
	
	@JsonIgnore
	public List<StateEntity> getList() {
		return list;
	}

	public void setList(List<StateEntity> list) {
		this.list = list;
	}

	public int getSiNo() {
		return siNo;
	}

	public void setSiNo(int siNo) {
		this.siNo = siNo;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	

}
