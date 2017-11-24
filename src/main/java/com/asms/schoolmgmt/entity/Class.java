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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.asms.usermgmt.entity.teachingStaff.TeachingSubjects;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*Entity class for class_master table.
 * 
 * this class is the hibernate mapping class for class_master table
 * 
 * 
 */

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "class_master")
public class Class {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "class_id")
	private int id;

	
	
	@Column(name = "board_id")
	private int boardId;
	
	@Column(name = "class_name")
	private String name;
	
	
	@ManyToOne
	@JoinColumn(name = "group_id")
	private ClassGroup classGroupObject;


	@JsonIgnore
	public ClassGroup getClassGroupObject() {
		return classGroupObject;
	}

	public void setClassGroupObject(ClassGroup classGroupObject) {
		this.classGroupObject = classGroupObject;
	}

	

	@XmlElement
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "classObject", fetch = FetchType.EAGER)
	private List<Section> sectionObjects = new ArrayList<Section>();

	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "classes")
	private Set<AcademicYear> academicYears = new HashSet<AcademicYear>();
	
	

	

	@JsonIgnore
	public List<Section> getSectionObjects() {
		return sectionObjects;
	}

	public void setSectionObjects(List<Section> sectionObjects) {
		this.sectionObjects = sectionObjects;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	
	@JsonIgnore
	public Set<AcademicYear> getAcademicYears() {
		return academicYears;
	}

	public void setAcademicYears(Set<AcademicYear> academicYears) {
		this.academicYears = academicYears;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	@Override
	public String toString() {
		return "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
