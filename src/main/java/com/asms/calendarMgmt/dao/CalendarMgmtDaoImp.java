package com.asms.calendarMgmt.dao;

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

import com.asms.calendarMgmt.entity.Calendar;
import com.asms.calendarMgmt.Request.*;

import com.asms.Exception.AsmsException;
import com.asms.Exception.ExceptionHandler;
import com.asms.common.helper.AsmsHelper;
import com.asms.multitenancy.dao.MultitenancyDao;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.service.UserMgmtService;

@Service
@Component
public class CalendarMgmtDaoImp implements CalendarMgmtDao {

	@Autowired
	private SessionFactory sessionFactory;

	// @Autowired
	// private EntityCreater entityCreater;

	@Autowired
	private ExceptionHandler exceptionHandler;

	@Resource(name = "asmsProperties")
	private Properties properties;

	@Resource(name = "asmsdbProperties")
	private Properties dbProperties;

	@Autowired
	private MultitenancyDao multitenancyDao;

	ResourceBundle messages;

	private static final Logger logger = LoggerFactory.getLogger(UserMgmtService.class);

	@Override
	public void createEvent(EventDetails eventDetails, int userid, String tenantId) throws AsmsException {
		//Session session = null;
		messages = AsmsHelper.getMessageFromBundle();

		try {
			String schema = multitenancyDao.getSchema(tenantId);
			if (null == schema) {
				// session =
				// sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));

			}
			if (null != schema) {
				//session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();

				Calendar calendar = new Calendar();
				calendar.setFromTime(eventDetails.getFromTime());
				calendar.setToTime(eventDetails.getToTime());
				calendar.setFromDate(eventDetails.getFromDate());
				calendar.setToDate(eventDetails.getTodate());
				calendar.setAcademicYear(eventDetails.getAcademicYear());
				calendar.setName(eventDetails.getName());
				calendar.setRecurrencePattern(eventDetails.getRecurrencePattern());
				calendar.setCreatedby(userid);
				insertCalendar(calendar, schema);

			}
		} catch (Exception e) {
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "createevent()" + "   ", e);
			if (e instanceof AsmsException) {
				throw exceptionHandler.constructAsmsException(((AsmsException) e).getCode(),
						((AsmsException) e).getDescription());
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
						messages.getString("SYSTEM_EXCEPTION"));
			}
		}

	}

	@Override
	public void insertCalendar(Calendar calendar, String schema) throws AsmsException {

		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();

			session.save(calendar);

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
					+ "insertCalendar()" + "   ", ex);
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
	public List<Calendar> getEventDetails(String tenantId) throws AsmsException {
		Session session = null;
		try {
			String currentYear = AsmsHelper.getCurrentAcademicYear();
			String schema = multitenancyDao.getSchema(tenantId);
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				String hql = "from Calendar C where C.academicYear = ?";
				@SuppressWarnings("unchecked")
				List<Calendar> calendar = session.createQuery(hql).setParameter(0, currentYear).list();

				session.close();
				return calendar;

			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}
		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getEventDetails()" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
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

		@Override
	public void updateEvent(EventDetails details, User user, String tenant) throws AsmsException {
		Session session = null;

		Transaction tx = null;
		try {
			String schema = multitenancyDao.getSchema(tenant);
			if (null == schema) {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));

			}

			//ResourceBundle messages = AsmsHelper.getMessageFromBundle();

			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();
			Calendar calendar = (Calendar) session.load(Calendar.class, details.getId());
			updateEventDetails(calendar, details);

			session.update(calendar);
			tx.commit();
			session.close();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "updateEventDetails()" + "   ", e);
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

	private void updateEventDetails(Calendar calendar, EventDetails details) {

		if (null != details.getFromDate()) {
			calendar.setFromDate(details.getFromDate());
		}
		if (null != details.getTodate()) {
			calendar.setToDate(details.getTodate());
		}
		if (null != details.getFromTime()) {
			calendar.setFromTime(details.getFromTime());
		}
		if (null != details.getToTime()) {
			calendar.setToTime(details.getToTime());
		}
		if (null != details.getName()) {
			calendar.setName(details.getName());
		}
		if (null != details.getRecurrencePattern()) {
			calendar.setRecurrencePattern(details.getRecurrencePattern());
		}
		
		// pattern

	}

}
