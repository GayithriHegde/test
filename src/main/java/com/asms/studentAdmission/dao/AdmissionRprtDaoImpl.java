package com.asms.studentAdmission.dao;

import java.util.ResourceBundle;

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
import com.asms.common.helper.AsmsHelper;
import com.asms.multitenancy.dao.MultitenancyDao;
import com.asms.studentAdmission.entity.Admission;
import com.asms.studentAdmission.entity.AdmissionEnquiry;
import com.asms.studentAdmission.entity.ApplicationStatus;
import com.asms.studentAdmission.helper.EntityMaker;
import com.asms.studentAdmission.request.AdmissionDetails;
import com.asms.studentAdmission.request.AdmissionEnquiryDetails;
import com.asms.studentAdmission.request.ApplicationStatusDetails;
import com.asms.usermgmt.entity.User;

@Service
@Component
public class AdmissionRprtDaoImpl implements AdmissionRprtDao {

	@Autowired
	private MultitenancyDao multitenancyDao;

	@Autowired
	private ExceptionHandler exceptionHandler;

	@Autowired
	private EntityMaker entityMaker;
	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger logger = LoggerFactory.getLogger(AdmissionRprtDaoImpl.class);

	@Override
	public void createAdmission(AdmissionDetails admissionDetails, User user, String domain) throws AsmsException {
		// TODO Auto-generated method stub
		ResourceBundle messages = AsmsHelper.getMessageFromBundle();
		String schema = multitenancyDao.getSchemaByDomain(domain);

		if (null == schema) {
			throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
					messages.getString("TENANT_INVALID_CODE_MSG"));
		}

		Admission admission = entityMaker.createAdmissionEntity(admissionDetails);

		insertAdmission(admission, schema);

	}

	public void insertAdmission(Admission admission, String schema) throws AsmsException {

		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();
			session.save(admission);
			tx.commit();
			session.close();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."

					+ "insertAdmission()" + "   ", ex);

			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			if (ex instanceof AsmsException) {
				throw exceptionHandler.constructAsmsException(((AsmsException) ex).getCode(),
						((AsmsException) ex).getDescription());
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

	@Override
	public void createAdmissionEnquiry(AdmissionEnquiryDetails admissionEnquiryDetails, User user, String domain)
			throws AsmsException {
		// TODO Auto-generated method stub
		ResourceBundle messages = AsmsHelper.getMessageFromBundle();
		String schema = multitenancyDao.getSchemaByDomain(domain);

		if (null == schema) {
			throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
					messages.getString("TENANT_INVALID_CODE_MSG"));
		}

		AdmissionEnquiry admissionEnquiry = entityMaker.createAdmissionEnquiryEntity(admissionEnquiryDetails);

		insertAdmissionEnquiry(admissionEnquiry, schema);

	}

	public void insertAdmissionEnquiry(AdmissionEnquiry admissionEnquiry, String schema) throws AsmsException {

		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();
			session.save(admissionEnquiry);
			tx.commit();
			session.close();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."

					+ "insertAdmissionEnquiry()" + "   ", ex);

			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			if (ex instanceof AsmsException) {
				throw exceptionHandler.constructAsmsException(((AsmsException) ex).getCode(),
						((AsmsException) ex).getDescription());
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

	

	@Override
	public void createApplicationStatus(ApplicationStatusDetails applicationStatusDetails, User user, String domain) throws AsmsException {
		// TODO Auto-generated method stub
		ResourceBundle messages = AsmsHelper.getMessageFromBundle();
		String schema = multitenancyDao.getSchemaByDomain(domain);

		if (null == schema) {
			throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
					messages.getString("TENANT_INVALID_CODE_MSG"));
		}

		ApplicationStatus applicationStatus = entityMaker.createApplicationStatusEntity(applicationStatusDetails);

		insertApplicationStatus(applicationStatus, schema);

	}

	public void insertApplicationStatus(ApplicationStatus applicationStatus, String schema) throws AsmsException {

		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();
			session.save(applicationStatus);
			tx.commit();
			session.close();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."

					+ "insertApplicationStatus()" + "   ", ex);

			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			if (ex instanceof AsmsException) {
				throw exceptionHandler.constructAsmsException(((AsmsException) ex).getCode(),
						((AsmsException) ex).getDescription());
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
