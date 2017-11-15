package com.asms.schoolmgmt.entity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.asms.usermgmt.entity.teachingStaff.TeachingSubjects;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/*Entity class for section_master table.
 * 
 * this class is the hibernate mapping class for section_master table
 * 
 * 
 */

 
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
@Entity
@Table(name = "section_master")
public class Section {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int id;
	
	
	@Column(name = "section_name")
	private String name;
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sectionObject")
	private List<TeachingSubjects> teachingSubjects = new ArrayList<TeachingSubjects>();
	
	@XmlElement
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sectionObject", fetch = FetchType.EAGER)
	private Set<ClassSubjects> Subjects = new HashSet<ClassSubjects>();
	
	@XmlElement
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sectionObject", fetch = FetchType.EAGER)
	private Set<AdditionalSubjects> additionalSubjects = new HashSet<AdditionalSubjects>();
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sectionObject", fetch = FetchType.EAGER)
	private Set<TimeTable> timeTables = new HashSet<TimeTable>();
	
	@ManyToOne
	@JoinColumn(name = "class_id")
	private Class classObject;


	@JsonIgnore
	public Class getClassObject() {
		return classObject;
	}






	public void setClassObject(Class classObject) {
		this.classObject = classObject;
	}






	public int getId() {
		return id;
	}






	public void setId(int id) {
		this.id = id;
	}






	




	@JsonIgnore
	public List<TeachingSubjects> getTeachingSubjects() {
		return teachingSubjects;
	}






	public void setTeachingSubjects(List<TeachingSubjects> teachingSubjects) {
		this.teachingSubjects = teachingSubjects;
	}


    



	@Override
	public String toString() {
		return "";
	}






	




	@JsonIgnore
	public Set<ClassSubjects> getSubjects() {
		return Subjects;
	}






	public void setSubjects(Set<ClassSubjects> subjects) {
		Subjects = subjects;
	}





    @JsonIgnore
	public Set<AdditionalSubjects> getAdditionalSubjects() {
		return additionalSubjects;
	}






	public void setAdditionalSubjects(Set<AdditionalSubjects> additionalSubjects) {
		this.additionalSubjects = additionalSubjects;
	}






	public String getName() {
		return name;
	}






	public void setName(String name) {
		this.name = name;
	}





	@JsonIgnore
	public Set<TimeTable> getTimeTables() {
		return timeTables;
	}






	public void setTimeTables(Set<TimeTable> timeTables) {
		this.timeTables = timeTables;
	}






	      
}
