package com.asms.schoolmgmt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/*
 * Class name : BoardMaster
 * This class is the Hibernate mapping Entity/model class to map board_master table in DB
 */

@Entity
@Table(name="board_master")
public class BoardMaster  {
	
	@Id
	@GenericGenerator(name="ref",strategy="increment")
	@GeneratedValue(generator="ref")
	@Column(name="serial_no")
	private int serialNo;
	
	@Column(name="board_code")
	private String boardCode;	
	
	@Column(name="board_id")
	private int boardId;
	
	@Column(name="board_name")
	private String boardNames;
	
	

	

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getBoardCode() {
		return boardCode;
	}

	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getBoardNames() {
		return boardNames;
	}

	public void setBoardNames(String boardNames) {
		this.boardNames = boardNames;
	}
	

}
