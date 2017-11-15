/**
@{author} Gayithri.Hg
25-Sep-2017
*/
package com.asms.schoolmgmt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class name : ClassGroup This table cis for grouping classes
 */

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "ClassBreaks")
public class Breaks {
	/**
	 * @{author} Gayithri.Hg
	 */

	@Id
	@GeneratedValue
	@Column(name = "serial_no")
	private int serialNo;

	@Column(name = "start_time")
	private String startTime;
	
	@Column(name = "end_time")
	private String endTime;

	@ManyToOne
	@JoinColumn(name = "group_id")
	private ClassGroup classGroupObject;

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@JsonIgnore
	public ClassGroup getClassGroup() {
		return classGroupObject;
	}

	public void setClassGroup(ClassGroup classGroupObject) {
		this.classGroupObject = classGroupObject;
	}	
	
	
	


}
