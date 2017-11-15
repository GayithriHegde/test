package com.asms.reportsGeneration.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.asms.Exception.AsmsException;
import com.asms.Exception.ExceptionHandler;
import com.asms.common.helper.AsmsHelper;
import com.asms.common.helper.Constants;
import com.asms.curriculumplan.entity.Unit;
import com.asms.examination.dao.ExaminationDaoImpl;
import com.asms.multitenancy.dao.MultitenancyDao;
import com.asms.reportsGeneration.helper.ReportExcelMaker;
import com.asms.reportsGeneration.request.CurriculamDetails;
import com.asms.reportsGeneration.request.RequestForReports;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.response.RegistrationResponse;

@Service
@Component
public class ReportsDaoImpl implements ReportsGenDao {

	ResourceBundle messages;

	@Autowired
	private MultitenancyDao multitenancyDao;

	@Autowired
	public SessionFactory sessionFactory;

	@Autowired
	private ReportExcelMaker reportExcelMaker;

	@Autowired
	private ExceptionHandler exceptionHandler;

	private static final Logger logger = LoggerFactory.getLogger(ReportsDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public void getCurriculamReport(CurriculamDetails curriculamDetails, User user, String tenant) {

		messages = AsmsHelper.getMessageFromBundle();

		String[] columns = new String[] { "SINO", "Academic Year", "Class", "Subjects", "Unit number", "Unit title",
				"No.of periods required", "Plan Date", "Status" };

		String title = "Curriculam plan report for the year" + curriculamDetails.getAcademicYear();
		String subTitle = " Class - " + curriculamDetails.getClassName() + " --------------Subject - "
				+ curriculamDetails.getSubject();

		Query q = null;
		Session session = null;

		try {
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenant);
			List<String> rowData = new ArrayList<String>();
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();

				String hql = "from  Unit U where U.curriculumObject.academicYear=? and "
						+ "U.curriculumObject.className =? and U.curriculumObject.subject = ?" ;

				q = session.createQuery(hql).setParameter(0, curriculamDetails.getAcademicYear())
						.setParameter(1, curriculamDetails.getClassName())
						.setParameter(2, curriculamDetails.getSubject());
				
				List<Unit> units = (List<Unit>) q.list();

				for (Unit records : units) {

					rowData.add(records.getUnitNo());
					rowData.add(records.getUnitName());
					// int i=Integer.parseInt(records.getNoOfPeriods());
					rowData.add(records.getNoOfPeriods());
					rowData.add(records.getMonth());
					rowData.add(records.getStatus());

				}

				String[] rowArray = rowData.toArray(new String[rowData.size()]);
				reportExcelMaker.excelMaker(title, subTitle, columns, rowArray);

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
					+ "getCurriculamReport()" + "   ", e);
		} finally {
			if (session.isOpen()) {
				session.close();
			}

		}

	}

}
