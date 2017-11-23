package com.asms.lessonmgmt.dao;

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
import com.asms.common.helper.AsmsHelper;
import com.asms.lessonmgmt.entity.LessonPlan;
import com.asms.lessonmgmt.entity.PeriodLessanPlan;
import com.asms.lessonmgmt.request.LessonPlanDetails;
import com.asms.multitenancy.dao.MultitenancyDao;

@Service
@Component
public class LessonPlanMgmtDaoImpl implements LessonPlanMgmtDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ExceptionHandler exceptionHandler;

	private static final Logger logger = LoggerFactory.getLogger(LessonPlanMgmtDaoImpl.class);

	@Resource(name = "asmsdbProperties")
	private Properties dbProperties;

	@Autowired
	private MultitenancyDao multitenancyDao;

	ResourceBundle messages;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.asms.LessonPlanMgmt.dao.lessonplanMgmtDao#createlessonplan(
	 * com.asms.LessonPlanMgmt. request.LessonPlanDetails)
	 */

	@Override
	public void createplan(LessonPlanDetails lessonPlanDetails, String tenantId) throws AsmsException {
		//Session session = null;

		String schema = multitenancyDao.getSchema(tenantId);
		if (null != schema) {
			//session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();

			LessonPlan lessonplan = new LessonPlan();

			lessonplan.setUnitId(lessonPlanDetails.getUnitId());
			lessonplan.setTotalPeriods(lessonPlanDetails.getTotalPeriods());
			lessonplan.setObjective(lessonPlanDetails.getObjective());
			lessonplan.setCreatedBy(lessonPlanDetails.getCreatedBy());
			lessonplan.setResourcesText(lessonPlanDetails.getResourcesText());
			lessonplan.setResourcesPath(lessonPlanDetails.getResourcesPath());
			lessonplan.setActivitiesPlannedText(lessonPlanDetails.getActivitiesPlannedText());
			lessonplan.setActivitiesPlannedPath(lessonPlanDetails.getActivitiesPlannedPath());
			lessonplan.setClassHomeAssignmentText(lessonPlanDetails.getClassHomeAssignmentText());
			lessonplan.setClassHomeAssignmentPath(lessonPlanDetails.getClassHomeAssignmentPath());
			lessonplan.setPlannedAssignmentText(lessonPlanDetails.getPlannedAssignmentText());
			lessonplan.setAcademicyear(lessonPlanDetails.getAcademicYear());
			lessonplan.setPlannedAssignmentPath(lessonPlanDetails.getPlannedAssignmentPath());

			for (int i = 0; i < lessonPlanDetails.getPeriodLessanPlans().size(); i++) {
				PeriodLessanPlan periodLessanPlan = new PeriodLessanPlan();
				periodLessanPlan.setContent(lessonPlanDetails.getPeriodLessanPlans().get(i).getContent());
				periodLessanPlan.setLessonPlanObject(lessonplan);
				lessonplan.getPeriodLessanPlans().add(periodLessanPlan);

			}
			insertLessonPlan(lessonplan, tenantId);
			// insertPeriodLessonPlan(periodLessanPlan,lessonplan.getSerialNo(),tenantId);

		} else {

		}

	}

	/*
	 * insert LessonPlan : This method inserts LesssonPlan  in the database.
	 * 
	 * 
	 * parameters: lessonPlanDetails , String tenant id
	 * 
	 * return: void
	 * 
	 * 
	 */
	private void insertLessonPlan(LessonPlan lessonplan, String tenant) throws AsmsException {

		Session session = null;
		Transaction tx = null;

		try {
			String schema = multitenancyDao.getSchema(tenant);
			if (null != schema) {

				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();

				tx = session.beginTransaction();

				session.save(lessonplan);
				tx.commit();
				session.close();

			}
		} catch (Exception ex) {
			if (null != session && session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."

					+ "insertLessonPlan()" + "   ", ex);

			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));

		} finally {
			if (null != session && session.isOpen()) {
				session.close();
			}
		}
	}

	/*
	 * Method : getLessonPlans : gets the LessonPlans input : tenant, userId
	 * return : LesssonPlans
	 *
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<LessonPlan> getLessonPlans(String tenantId, String userId) throws AsmsException {
		Session session = null;
		
		try {
			String schema = multitenancyDao.getSchema(tenantId);
			if (null != schema){
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			String hql = "from LessonPlan L where L.createdBy=?";
			// List<LessonPlan> lessonPlan = session.createQuery(hql).list();
			List<LessonPlan> lessonPlan = session.createQuery(hql).setParameter(0, userId).list();
			
			session.close();
			return lessonPlan;
			
		}else{ 
			throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
			 messages.getString("TENANT_INVALID_CODE_MSG"));
			 
		}

		} catch (Exception e) {
			if (null != session && session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getLessonPlan()" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			if (e instanceof AsmsException) {
				throw exceptionHandler.constructAsmsException(((AsmsException) e).getCode(),
						((AsmsException) e).getDescription());
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
						messages.getString("SYSTEM_EXCEPTION"));
			}

		} finally {
			if (null != session && session.isOpen()) {
				session.close();
			}
		}

	}

}
