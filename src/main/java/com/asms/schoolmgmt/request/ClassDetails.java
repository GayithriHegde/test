/**
@{author} mallikarjun.guranna
14-Sep-2017
*/
package com.asms.schoolmgmt.request;

import java.util.List;

public class ClassDetails {
	/**
	 * @{author} mallikarjun.guranna 14-Sep-2017
	 */

	private int boardId;
	private List<SectionDetails> sectionDetails;
	private String name;

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public List<SectionDetails> getSectionDetails() {
		return sectionDetails;
	}

	public void setSectionDetails(List<SectionDetails> sectionDetails) {
		this.sectionDetails = sectionDetails;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
