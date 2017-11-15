/**
@{author} mallikarjun.guranna
11-Sep-2017
*/
package com.asms.schoolmgmt.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class name : AcademicYear This table created dynamically when school setup
 * creates from school admin And this class is hibernate mapping class to
 * _AcademicYear table in DB.
 */

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "AcademicYear")
public class AcademicYear {
	/**
	 * @{author} mallikarjun.guranna 11-Sep-2017
	 */

	@Id
	@GeneratedValue
	@Column(name = "academic_year_id")
	private int academicYearId;

	@Column(name = "academicYear_FromTo")
	private String academicYearFromTo;

	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "AcademicYearClassMap", joinColumns = {
			@JoinColumn(name = "academicYear_Id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "class_id", nullable = false, updatable = false) })
	private Set<Class> classes = new HashSet<Class>();

	public int getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(int academicYearId) {
		this.academicYearId = academicYearId;
	}

	
	@JsonIgnore
	public Set<Class> getClasses() {
		return classes;
	}

	public void setClasses(Set<Class> classes) {
		this.classes = classes;
	}

	public String getAcademicYearFromTo() {
		return academicYearFromTo;
	}

	public void setAcademicYearFromTo(String academicYearFromTo) {
		this.academicYearFromTo = academicYearFromTo;
	}

}
