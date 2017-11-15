package com.asms.examination.helper;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.asms.examination.entity.Examination;
import com.asms.examination.entity.ExaminationDetails;
import com.asms.examination.entity.Marks;
import com.asms.examination.entity.MarksMaster;
import com.asms.examination.request.ExamRequestDetails;
import com.asms.examination.request.ExaminationRequestDetails;
import com.asms.examination.request.MarksDetails;
import com.asms.examination.request.MarksMasterDetails;

/*
 * ExamEntityCreator.java does creation of entity objects
 * from ui object
 * 
 */
@Component
public class ExamEntityCreator {
	
	
	/*
	 * Method: createExamination -> maps ui values to entity input : ExamRequestDetails
	 * return : Examination
	 * 
	 */

	public Examination createExamination(ExamRequestDetails details) throws ParseException {
	
		Examination examination  = new Examination();
		examination.setExamName(details.getExamName());
		examination.setBoardId(details.getBoardId());
		examination.setTotalMarks(details.getTotalMarks());
		examination.setAcademicYear(details.getAcademicYear());

		return examination;
	}
	
	
	public List<ExaminationDetails> createExaminationDetails(List<ExaminationRequestDetails> details) throws ParseException {
		
		ExaminationDetails examinationDetails = new ExaminationDetails();
		
		List<ExaminationDetails> examinationDetails2  = new ArrayList<ExaminationDetails>();
		
		
		for (ExaminationRequestDetails examinationRequestDetails : details) {
			examinationDetails.setClassName(examinationRequestDetails.getClassName());
		
			examinationDetails.setExamDate(examinationRequestDetails.getExamDate());

		examinationDetails.setTimeFrom(examinationRequestDetails.getTimeFrom());
		examinationDetails.setTimeTo(examinationRequestDetails.getTimeFrom());
		
		examinationDetails.setSubjectName(examinationRequestDetails.getSubjectName());
		examinationDetails.setMarks(examinationRequestDetails.getMarks());
		
		examinationDetails2.add(examinationDetails);
		}
		
		return examinationDetails2; 
	}
	
	
	/*
	 * Method: createExaminationDetail -> maps ui values to entity input : ExaminationRequestDetails
	 * return : ExaminationDetails
	 * 
	 */
public ExaminationDetails createExaminationDetail(ExaminationRequestDetails details) throws ParseException {
		
		ExaminationDetails examinationDetails = new ExaminationDetails();
		
		//ExaminationDetails examinationDetails2  = new ExaminationDetails();
		//Examination examination  = new Examination();
			examinationDetails.setClassName(details.getClassName());
		
			examinationDetails.setExamDate(details.getExamDate());

		examinationDetails.setTimeFrom(details.getTimeFrom());
		examinationDetails.setTimeTo(details.getTimeFrom());
		
		examinationDetails.setSubjectName(details.getSubjectName());
		examinationDetails.setMarks(details.getMarks());
		examinationDetails.setExaminationObject(details.getExamId());
		
		return examinationDetails; 
	}


/*
 * Method: createMarksMaster -> maps ui values to entity input : MarksMasterDetails
 * return : MarksMaster
 * 
 */
public MarksMaster createMarksMaster(MarksMasterDetails marksMasterDetails)
{
	 MarksMaster marksMaster = new MarksMaster();
	 
	 marksMaster.setAcademicYear(marksMasterDetails.getAcademicYear());
	 marksMaster.setClassId(marksMasterDetails.getClassId());
	 marksMaster.setSectionId(marksMasterDetails.getSectionId());
	 marksMaster.setStudentId(marksMasterDetails.getStudentId());

	 return marksMaster;
	 
	 
	 
}


/*
 * Method: createMarks -> maps ui values to entity input : MarksDetails
 * return : Marks
 * 
 */
public Marks createMarks(MarksDetails marksDetails)
{
	 Marks marks = new Marks();
	 
	 marks.setSubjectId(marksDetails.getSubjectId());
	 marks.setMarks(marksDetails.getMarks());
	 marks.setRemarks(marksDetails.getRemarks());
	marks.setExamName(marksDetails.getExamName());

	 return marks;
	 
	 
	 
}

}
