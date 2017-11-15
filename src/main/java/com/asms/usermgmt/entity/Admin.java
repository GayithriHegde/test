package com.asms.usermgmt.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/*Entity class for admin_login table.
 * 
 * this class is the hibernate mapping class for admin_login table
 * 
 * 
 */

 
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
@Entity
@Table(name = "admin_login")
@PrimaryKeyJoinColumn(name = "serial_no", referencedColumnName = "serial_no")
public class Admin extends User{
	


	@Column(name = "admin_name")
	private String name;

	
	@Column(name = "admin_timestamp")
	private Date createdOn;

	@Column(name = "admin_status")
	private String status;



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
