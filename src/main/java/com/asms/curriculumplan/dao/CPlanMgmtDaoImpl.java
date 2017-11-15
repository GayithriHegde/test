package com.asms.curriculumplan.dao;

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
import com.asms.common.helper.AsmsHelper;
import com.asms.common.helper.Constants;
import com.asms.curriculumplan.entity.CurriculumPlan;
import com.asms.curriculumplan.entity.Unit;
import com.asms.curriculumplan.request.CurriculumPlanDetails;
import com.asms.multitenancy.dao.MultitenancyDao;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.entity.student.Student;

@Service
@Component
public class CPlanMgmtDaoImpl implements CPlanMgmtDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ExceptionHandler exceptionHandler;

	private static final Logger logger = LoggerFactory.getLogger(CPlanMgmtDaoImpl.class);

	@Resource(name = "asmsdbProperties")
	private Properties dbProperties;

	@Resource(name = "asmsProperties")
	private Properties properties;

	@Autowired
	private MultitenancyDao multitenancyDao;

	/*
	 * Method : createCPlan input: curriculumPlanDetais, tenantid
	 * 
	 * description : saves curriculum plan details in database
	 * 
	 */

	@Override
	public List<Unit> createCPlan(CurriculumPlanDetails details, String UserId, String tenant) throws AsmsException {
		Session session = null;
		Transaction tx = null;

		ResourceBundle messages = AsmsHelper.getMessageFromBundle();
		String hql = null;
		CurriculumPlan cp = null;

		try {
			String schema = multitenancyDao.getSchema(tenant);
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				// if cp is already inserted and now only units are getting
				// added
				if (details.getId() > 0) {
					hql = "from CurriculumPlan C where C.serialNo = ?";
					cp = (CurriculumPlan) session.createQuery(hql).setParameter(0, details.getId()).uniqueResult();
					if (null == cp) {
						throw exceptionHandler.constructAsmsException(messages.getString("CPLAN_ID_INVALID_CODE"),
								messages.getString("CPLAN_ID_INVALID_MSG"));
					}

				} else {
					cp = new CurriculumPlan();
					cp.setAcademicYear(AsmsHelper.getCurrentAcademicYear());
					cp.setClassName(details.getClassName());
					cp.setCreatedBy(UserId);
					cp.setCreatedOn(AsmsHelper.getTodayDate());
					cp.setSectionName(details.getSectionName());
					cp.setSubject(details.getSubject());
				}

				Unit unit = new Unit();
				unit.setCurriculumObject(cp);
				unit.setMarks(details.getMarks());
				unit.setMonth(details.getPlannedForMonth());
				unit.setNoOfPeriods(details.getNoOfPeriods());
				unit.setStatus(details.getStatus());
				unit.setUnitName(details.getUnitName());
				unit.setUnitNo(details.getUnitNo());

				cp.getUnits().add(unit);

				tx = session.beginTransaction();
				session.save(cp);
				tx.commit();

				session.close();

				return new ArrayList<>(cp.getUnits());

			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}
		}

		catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "createCPlan" + "   ", e);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<CurriculumPlan> getCPlans(User user, String tenant) throws AsmsException {
		Session session = null;
		//Transaction tx = null;

		ResourceBundle messages = AsmsHelper.getMessageFromBundle();
		String hql = null;
		
		List<CurriculumPlan> cps = new ArrayList<CurriculumPlan>();

		try {
			// return only for student and parent  ...  code has to be added for parent
			if(null != user.getRoleObject() && Constants.role_student.equalsIgnoreCase(user.getRoleObject().getRoleName()) ){
				String schema = multitenancyDao.getSchema(tenant);
				if (null != schema) {
					session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
					Student st = (Student)user;

					hql = "from CurriculumPlan U where  C where C.className = ? and C.sectionName =?";
					cps = (List<CurriculumPlan>) session.createQuery(hql).setParameter(0, st.getStudentClass())
							.setParameter(1, st.getStudentSection()).list();
					

				} else {
					throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
							messages.getString("TENANT_INVALID_CODE_MSG"));
				}
			}
			else{
				return cps;
			}
			
		}

		catch (Exception e) {
			if (null != session && session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getCPlans" + "   ", e);
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

		return cps;

	}

}
