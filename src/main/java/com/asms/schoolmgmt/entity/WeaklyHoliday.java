package com.asms.schoolmgmt.entity;

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

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
@Entity
@Table(name="Weakly_Holiday")
public class WeaklyHoliday {
	
	/**
	 * @{author} Praveen.Tiwari 10-Nov-2017
	 */

	  @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  @Column(name="serial_no")
	  private int serialNo;
	  
	  @Column(name="day_of_holiday")
	  private String dayofholiday;
	  
	  @ManyToOne
	  @JoinColumn(name="group_id")
	  private ClassGroup classGroupObject;
	  
	  public int getSerialNo()
	  {
	    return this.serialNo;
	  }
	  
	  public void setSerialNo(int serialNo)
	  {
	    this.serialNo = serialNo;
	  }
	  
	  public String getDayofholiday()
	  {
	    return this.dayofholiday;
	  }
	  
	  public void setDayofholiday(String dayofholiday)
	  {
	    this.dayofholiday = dayofholiday;
	  }
	  
	  @JsonIgnore
	  public ClassGroup getClassGroupObject()
	  {
	    return this.classGroupObject;
	  }
	  
	  public void setClassGroupObject(ClassGroup classGroupObject)
	  {
	    this.classGroupObject = classGroupObject;
	  }
	}



