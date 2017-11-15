package com.asms.examination.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.asms.Exception.AsmsException;
import com.asms.Exception.ExceptionHandler;
import com.asms.common.excel.ExcelMaker;
import com.asms.common.helper.AsmsHelper;
import com.asms.common.pdf.PdfMaker;
import com.asms.examination.entity.Examination;
import com.asms.examination.entity.ExaminationDetails;
import com.asms.examination.entity.Marks;
import com.asms.examination.entity.MarksMaster;
import com.asms.examination.request.ExamRequestDetails;
import com.asms.examination.request.ExaminationRequestDetails;
import com.asms.examination.request.MarksDetails;
import com.asms.examination.request.MarksMasterDetails;
import com.asms.multitenancy.dao.MultitenancyDao;

@Service
@Component
public class ExaminationDaoImpl implements ExaminationDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ExceptionHandler exceptionHandler;

	@Resource(name = "asmsProperties")
	private Properties properties;

	@Resource(name = "asmsdbProperties")
	private Properties dbProperties;

	@Autowired
	private MultitenancyDao multitenancyDao;

	ResourceBundle messages;

	@Autowired
	private PdfMaker pdfMaker;
	
	@Autowired
	private  ExcelMaker excelMaker;
	

	private static final Logger logger = LoggerFactory.getLogger(ExaminationDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asms.examination.dao.ExaminationDao#insertExam(com.asms.examination.
	 * request.ExamRequestDetails)
	 */
	@Override
	public void insertExam(ExamRequestDetails examRequestDetails, String tenant) throws AsmsException {

		//Session session = null;

		String schema = multitenancyDao.getSchema(tenant);
		if (null != schema) {
			//session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			Examination examination = new Examination();
			examination.setAcademicYear(examRequestDetails.getAcademicYear());

			examination.setBoardId(examRequestDetails.getBoardId());
			examination.setExamName(examRequestDetails.getExamName());

			examination.setTotalMarks(examRequestDetails.getTotalMarks());
			insertExamDetail(examination, schema);
		} // else
		else {
			throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
					messages.getString("TENANT_INVALID_CODE_MSG"));
		}

	}

	private void insertExamDetail(Examination examination, String schema) throws AsmsException {
		Session session = null;
		Transaction tx = null;

		try {
			messages = AsmsHelper.getMessageFromBundle();

			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				tx = session.beginTransaction();
				// Examination examination =
				// examEntityCreator.createExamination(examRequestDetails);
				session.save(examination);

				tx.commit();
				session.close();
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "insertExamDetail()" + "   ", ex);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));

		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asms.examination.dao.ExaminationDao#insertExaminationDetails(com.asms
	 * .examination.request. ExaminationRequestDetails)
	 */
	@Override
	public void insertExaminationDetails(ExaminationRequestDetails examinationDetails1, int examId, String tenant)
			throws AsmsException {

	
			ExaminationDetails examinationDetails = new ExaminationDetails();
			// List<ExaminationRequestDetails> examinationRequestDetails1 = new
			// ArrayList<ExaminationRequestDetails>();

			if (examId > 0) {
				examinationDetails.setClassName(examinationDetails1.getClassName());

				examinationDetails.setExamDate(examinationDetails1.getExamDate());

				examinationDetails.setTimeFrom(examinationDetails1.getTimeFrom());
				examinationDetails.setTimeTo(examinationDetails1.getTimeTo());

				examinationDetails.setSubjectName(examinationDetails1.getSubjectName());
				examinationDetails.setMarks(examinationDetails1.getMarks());
				examinationDetails.setExaminationObject(examinationDetails1.getExamId());

				insertExamination(examinationDetails, examId, tenant);
			}

			else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}

			// else


		}

	private void insertExamination(ExaminationDetails examinationDetails, int examId, String tenant)
			throws AsmsException {
		Session session = null;
		Transaction tx = null;

		try {

			messages = AsmsHelper.getMessageFromBundle();

			String schema = multitenancyDao.getSchema(tenant);
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				String hqry = "from Examination  where serialNo='" + examId + "'";
				Examination examination = (Examination) session.createQuery(hqry).uniqueResult();
				tx = session.beginTransaction();
				// Examination examination =
				// examEntityCreator.createExamination(examRequestDetails);
				examinationDetails.setExaminationObject(examination);

				session.save(examinationDetails);

				tx.commit();
				session.close();
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}

		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "insertExamination()" + "   ", ex);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));

		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.asms.examination.dao.ExaminationDao#getExaminationDetails(String
	 * className)
	 */
	@Override
	public List<ExaminationDetails> getExaminationDetails(String className, String tenant) throws AsmsException {
		Session session = null;
		try {
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenant);
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				String hql = "from ExaminationDetails E where E.className='" + className + "'";
				@SuppressWarnings("unchecked")
				List<ExaminationDetails> examinationDetails = session.createQuery(hql).list();
				session.close();
				return examinationDetails;
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}
		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getExaminationDetails()" + "   ", e);
			if (e instanceof AsmsException) {
				throw exceptionHandler.constructAsmsException(((AsmsException) e).getCode(),
						((AsmsException) e).getDescription());
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
						messages.getString("SYSTEM_EXCEPTION"));
			}
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.asms.examination.dao.ExaminationDao#getExamNames(String
	 * className)
	 */
	@Override
	public List<String> getExamNames(String classNames, String tenantId) throws AsmsException {

		Session session = null;
		try {
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenantId);
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				String hql = "select E.examinationObject.examName from ExaminationDetails  E where  E.className='"
						+ classNames + "'";
				@SuppressWarnings("unchecked")
				List<String> examinationNames = session.createQuery(hql).list();
				session.close();
				return examinationNames;
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}
		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getExamNames()" + "   ", e);
			if (e instanceof AsmsException) {
				throw exceptionHandler.constructAsmsException(((AsmsException) e).getCode(),
						((AsmsException) e).getDescription());
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
						messages.getString("SYSTEM_EXCEPTION"));
			}
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.asms.examination.dao.ExaminationDao#insertMarksMaster(com.asms.
	 * examination.request.MarksMasterDetails)
	 */
	@Override
	public void insertMarksMaster(MarksMasterDetails marksMasterDetails, String tenant) throws AsmsException {

		MarksMaster marksMaster = new MarksMaster();

		marksMaster.setAcademicYear(marksMasterDetails.getAcademicYear());
		marksMaster.setClassId(marksMasterDetails.getClassId());
		marksMaster.setSectionId(marksMasterDetails.getSectionId());
		marksMaster.setStudentId(marksMasterDetails.getStudentId());

		createMarksMaster(marksMaster, tenant);

	}

	private void createMarksMaster(MarksMaster marksMaster, String tenant) throws AsmsException {
		Session session = null;
		Transaction tx = null;

		try {
			messages = AsmsHelper.getMessageFromBundle();

			String schema = multitenancyDao.getSchema(tenant);
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				tx = session.beginTransaction();

				session.save(marksMaster);

				tx.commit();
				session.close();
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "insertMarksMaster()" + "   ", ex);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));

		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asms.examination.dao.ExaminationDao#insertMarks(com.asms.examination.
	 * request.MarksDetails)
	 */
	@Override
	public void insertMarks(MarksDetails marksDetails, int marksMasterId, String schema) throws AsmsException {

		if (null != schema) {
			Marks marks = new Marks();

			marks.setSubjectId(marksDetails.getSubjectId());
			marks.setMarks(marksDetails.getMarks());
			marks.setRemarks(marksDetails.getRemarks());
			marks.setExamName(marksDetails.getExamName());

			createMarks(marks, marksMasterId, schema);
		}

		else {
			throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
					messages.getString("TENANT_INVALID_CODE_MSG"));
		}

	}

	private void createMarks(Marks marks, int marksMasterId, String tenant) throws AsmsException {
		Session session = null;
		Transaction tx = null;

		try {
			messages = AsmsHelper.getMessageFromBundle();

			String schema = multitenancyDao.getSchema(tenant);
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				String hqry = "from MarksMaster  where serialNo='" + marksMasterId + "'";
				MarksMaster marksMaster = (MarksMaster) session.createQuery(hqry).uniqueResult();
				tx = session.beginTransaction();
				marks.setMarksMasterObject(marksMaster);
				// Examination examination =
				// examEntityCreator.createExamination(examRequestDetails);
				session.save(marks);

				tx.commit();
				session.close();
			}

			else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "createMarks()" + "   ", ex);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));

		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}
	
	
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asms.examination.dao.ExaminationDao#createPdf(String examId)
	 */
	@Override
	public void createPdf(String examId, String tenant) throws AsmsException {
		Session session = null;
		try {
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenant);
			List<String> rowData = new ArrayList<String>();
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				String hql = "from Examination E where E.serialNo='" + examId + "'";
				Examination examination = (Examination) session.createQuery(hql).uniqueResult();
				List<ExaminationDetails> examinationDetails = examination.getExaminationDetails();
				if (!examinationDetails.isEmpty()) {
					String title =  " ExamName - "  +  examinationDetails.get(0).getExaminationObject().getExamName();
					String subTitle ="ClassName - " + examinationDetails.get(0).getClassName();
				    String[] columns = new String[]{"Subject","Date","Time From","Time To"};
				    for (ExaminationDetails ed : examinationDetails) {
						rowData.add(ed.getSubjectName());
						rowData.add(ed.getExamDate().toString());
						rowData.add(ed.getTimeFrom());
						rowData.add(ed.getTimeTo());
					}
				    pdfMaker.pdfMaker(title, subTitle, columns,rowData);
				   
				}

				session.close();
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}
		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "createPdf()" + "   ", e);
			if (e instanceof AsmsException) {
				throw exceptionHandler.constructAsmsException(((AsmsException) e).getCode(),
						((AsmsException) e).getDescription());
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
						messages.getString("SYSTEM_EXCEPTION"));
			}
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}

	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asms.examination.dao.ExaminationDao#createExcel(String examId)
	 */
	@Override
	public void createExcel(String examId, String tenant) throws AsmsException {
		Session session = null;
		try {
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenant);
			List<String> rowData = new ArrayList<String>();
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				String hql = "from Examination E where E.serialNo='" + examId + "'";
				Examination examination = (Examination) session.createQuery(hql).uniqueResult();
				List<ExaminationDetails> examinationDetails = examination.getExaminationDetails();
				String title =  " ExamName - "  +  examinationDetails.get(0).getExaminationObject().getExamName();
				String subTitle ="ClassName - " + examinationDetails.get(0).getClassName();
				
				
				
				String[] columns= new String[]{"Subject","Date","Time From","Time To"};
				
				for (ExaminationDetails ed : examinationDetails) {
					rowData.add(ed.getSubjectName());
					rowData.add(ed.getExamDate().toString());
					rowData.add(ed.getTimeFrom());
					rowData.add(ed.getTimeTo());
				
				}
				
				
				
				String [] rowArray = rowData.toArray(new String[rowData.size()]);
				excelMaker.excelMaker(title, subTitle, columns,rowArray);

				session.close();
	}
			else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}
		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "createPdf()" + "   ", e);
			if (e instanceof AsmsException) {
				throw exceptionHandler.constructAsmsException(((AsmsException) e).getCode(),
						((AsmsException) e).getDescription());
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
						messages.getString("SYSTEM_EXCEPTION"));
			}
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asms.examination.dao.ExaminationDao#createPdfForMarksSheet(String marksMasterId)
	 */
	@Override
	public void createPdfForMarksSheet(String marksMasterId, String tenant) throws AsmsException {
		Session session = null;
		
		
		try {
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenant);
			List<String> rowData = new ArrayList<String>();
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				String hql = "from MarksMaster M   where M.serialNo='" + marksMasterId + "'";
				
				MarksMaster marksMaster = (MarksMaster) session.createQuery(hql).uniqueResult();
				List<Marks> marks = marksMaster.getMarks();
				if (!marks.isEmpty()) {
				String title =  "Report Card";
				String subTitle = " ExamName - "  +  marks.get(0).getExamName() +"\n" +"ClassName - " + marks.get(0).getMarksMasterObject().getClassId() +"\n"+ "studentAdmissionNo - " + marks.get(0).getMarksMasterObject().getStudentId();
				//String studentAdmissionNo="studentAdmissionNo -" + marks.get(0).getMarksMasterObject().getStudentId() ;
				
			    String[] columns = new String[]{"Subject","marks"};
			    
			    for (Marks mks : marks) {
					rowData.add(mks.getSubjectId());
					rowData.add(String.valueOf(mks.getMarks()));
					
					
				}
			    pdfMaker.pdfMaker(title, subTitle,columns,rowData);
				}

			session.close();
		}
				else {
					throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
							messages.getString("TENANT_INVALID_CODE_MSG"));
				}
			} catch (Exception e) {
				if (session.isOpen()) {
					session.close();
				}
				logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
						+ "createPdf()" + "   ", e);
				if (e instanceof AsmsException) {
					throw exceptionHandler.constructAsmsException(((AsmsException) e).getCode(),
							((AsmsException) e).getDescription());
				} else {
					throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
							messages.getString("SYSTEM_EXCEPTION"));
				}
			} finally {
				if (session.isOpen()) {
					session.close();
				}
			

		}
		
		
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asms.examination.dao.ExaminationDao#createExcelForReportCard(String marksMasterId)
	 */
	@Override
	public void createExcelForReportCard(String marksMasterId, String tenant) throws AsmsException {
		
		Session session = null;
		try {
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenant);
			List<String> rowData = new ArrayList<String>();
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				String hql = "from MarksMaster M   where M.serialNo='" + marksMasterId + "'";
				MarksMaster marksMaster = (MarksMaster) session.createQuery(hql).uniqueResult();
				List<Marks> marks = marksMaster.getMarks();
				if (!marks.isEmpty()) {
				String title =  "Report Card";
				String subTitle = " ExamName - "  +  marks.get(0).getExamName() + " \t" + " ClassName - " + marks.get(0).getMarksMasterObject().getClassId() +" \t  "+ "  studentAdmissionNo - " + marks.get(0).getMarksMasterObject().getStudentId();
				
				
				
				String[] columns= new String[]{"Subject","marks"};
				  for (Marks mks : marks) {
						rowData.add(mks.getSubjectId());
						rowData.add(String.valueOf(mks.getMarks()));
						
				  }
				  String [] rowArray = rowData.toArray(new String[rowData.size()]);
					excelMaker.excelMaker(title, subTitle, columns,rowArray);
				  
			}
				session.close();
	}
			else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}
		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "createPdf()" + "   ", e);
			if (e instanceof AsmsException) {
				throw exceptionHandler.constructAsmsException(((AsmsException) e).getCode(),
						((AsmsException) e).getDescription());
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
						messages.getString("SYSTEM_EXCEPTION"));
			}
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		
	}
	
	
	
	
}
