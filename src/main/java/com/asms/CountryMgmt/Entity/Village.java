package com.asms.CountryMgmt.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
@Entity
@Table(name = "village")
public class Village {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "serial_no")
	private int serialNo;
	
	
	@Column(name = "tehsil_id")
	private int tehsilId;
	
	@Column(name = "Village")
	private String villageName;

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public int getTehsilId() {
		return tehsilId;
	}

	public void setTehsilId(int tehsilId) {
		this.tehsilId = tehsilId;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}


}
