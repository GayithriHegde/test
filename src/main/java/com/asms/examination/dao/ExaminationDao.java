package com.asms.examination.dao;

import java.util.List;

import com.asms.Exception.AsmsException;
import com.asms.examination.entity.ExaminationDetails;
import com.asms.examination.request.ExamRequestDetails;
import com.asms.examination.request.ExaminationRequestDetails;
import com.asms.examination.request.MarksDetails;
import com.asms.examination.request.MarksMasterDetails;

public interface ExaminationDao {
	
	
	public void insertExam(ExamRequestDetails examRequestDetails,String tenant) throws AsmsException;
	
	
	public void insertExaminationDetails(ExaminationRequestDetails examinationRequestDetails,int examId,String tenant) throws AsmsException;
	
	public List<ExaminationDetails> getExaminationDetails(String className,String tenant) throws AsmsException;
	
	
	public List<String> getExamNames(String classNames,String tenantId) throws AsmsException; 
	
	
	public void insertMarksMaster(MarksMasterDetails marksMasterDetails,String tenant) throws AsmsException;
	
	public void insertMarks(MarksDetails marksDetails,int marksMasterId, String tenant) throws AsmsException;
	
	public void createPdf(String examId,String tenant) throws AsmsException;
	
	public void createExcel(String examId,String tenant) throws AsmsException;
	
	public void createPdfForMarksSheet(String marksMasterId,String tenant) throws AsmsException;
	
	public void createExcelForReportCard(String marksMasterId , String tenant) throws AsmsException;

}
