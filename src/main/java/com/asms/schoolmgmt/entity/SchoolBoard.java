/**
@{author} gayithri.hg
01-Sep-2017
*/
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

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "school_board")
public class SchoolBoard {
	/**
	 * @{author} gayithri.hg 01-Sep-2017
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
 
	@ManyToOne
	@JoinColumn(name = "school_id")
	private School schoolObject;

	@Column(name = "board_id")
	private int boardId;

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	@JsonIgnore
	public School getSchoolObject() {
		return schoolObject;
	}

	public void setSchoolObject(School schoolObject) {
		this.schoolObject = schoolObject;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	

}
