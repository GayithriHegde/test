/**
mallikarjun.guranna
Oct 6, 2017
*/
package com.asms.attendance.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Query;
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
import com.asms.attendance.entity.StaffAttendance;
import com.asms.attendance.entity.StudentAttendance;
import com.asms.attendance.request.StaffAttendanceDetails;
import com.asms.attendance.request.StudentAttendanceDetails;
import com.asms.common.helper.AsmsHelper;
import com.asms.common.helper.Constants;
import com.asms.multitenancy.dao.MultitenancyDao;
import com.asms.usermgmt.entity.student.Student;
import com.asms.usermgmt.request.UserBasicDetails;

@Service
@Component
public class AttendanceDaoImpl implements AttendanceDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ExceptionHandler exceptionHandler;

	@Autowired
	private MultitenancyDao multitenancyDao;

	ResourceBundle messages;

	private static final Logger logger = LoggerFactory.getLogger(AttendanceDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.asms.attendance.dao.AttendanceDao#search(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */

	// this method is the implementation of search() of AttendanceDao and
	// returns list of students based on className and sectionName

	@SuppressWarnings("unchecked")
	@Override
	public List<UserBasicDetails> search(String className, String sectionName, String tenant) throws AsmsException {
		// users =null;

		Session session = null;
		try {
			messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenant);
			String hql = "select student_first_name,student_last_name  from student_details  where ";

			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();

				if (className != null) {
					hql = hql + " student_class='" + className + "'";
				}
				if (className != null && sectionName != null) {
					hql = hql + " and student_section='" + sectionName + "'";
				}

			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}

			// return list of users here

			Query q = session.createSQLQuery(hql);
			List<Student[]> l = q.list();
			String name;
			List<UserBasicDetails> userBasicDetailsList = new ArrayList<UserBasicDetails>();
			for (Object[] student : l) {
				UserBasicDetails userBasicDetails = new UserBasicDetails();
				name = (String) student[0] + " " + (String) student[1];
				userBasicDetails.setFirstName(name);
				userBasicDetailsList.add(userBasicDetails);
			}
			session.close();
			return userBasicDetailsList;
			// return entityCreator.createUserBasicDetails(users);

		} catch (Exception ex) {
			if (null != session && session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "search()" + "   ", ex);
			if (ex instanceof AsmsException) {
				throw exceptionHandler.constructAsmsException(((AsmsException) ex).getCode(),
						((AsmsException) ex).getDescription());
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

	@Override
	public void insertStudentAttendanceDetails(StudentAttendanceDetails studentAttendanceDetails, String tenant)
			throws AsmsException {

		StudentAttendance studentAttendance = new StudentAttendance();
		studentAttendance.setAcademicYear(studentAttendanceDetails.getAcademicYear());
		studentAttendance.setClassName(studentAttendanceDetails.getClassName());
		studentAttendance.setDate(studentAttendanceDetails.getDate());
		studentAttendance.setStdId(studentAttendanceDetails.getStdId());
		studentAttendance.setName(studentAttendanceDetails.getName());
		studentAttendance.setSectionName(studentAttendanceDetails.getSectionName());
		studentAttendance.setMorningSession(studentAttendanceDetails.getMorningSession());
		studentAttendance.setAfternoonSession(studentAttendanceDetails.getAfternoonSession());
		//studentAttendance.setStatus(studentAttendanceDetails.getStatus());

		Session session = null;
		Transaction tx = null;
		try {
			messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenant);
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();
			session.save(studentAttendance);

			tx.commit();
			session.close();

		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			if (null != session && session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "insertStudentAttendanceDetails()" + "   ", ex);
			if (ex instanceof AsmsException) {
				throw exceptionHandler.constructAsmsException(((AsmsException) ex).getCode(),
						((AsmsException) ex).getDescription());
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
						messages.getString("SYSTEM_EXCEPTION"));
			}

		}

	}

	@Override
	public void insertStaffAttendanceDetails(StaffAttendanceDetails staffAttendanceDetails, String tenant)
			throws AsmsException {

		StaffAttendance staffAttendance = new StaffAttendance();
		staffAttendance.setAcademicYear(staffAttendanceDetails.getAcademicYear());
		staffAttendance.setStaffId(staffAttendanceDetails.getStaffId());
		staffAttendance.setDate(staffAttendanceDetails.getDate());
		staffAttendance.setName(staffAttendanceDetails.getName());
		staffAttendance.setStatus(staffAttendanceDetails.getStatus());

		Session session = null;
		Transaction tx = null;
		try {
			messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenant);
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();
			session.save(staffAttendance);

			tx.commit();
			session.close();

		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			if (null != session && session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "insertStaffAttendanceDetails()" + "   ", ex);
			if (ex instanceof AsmsException) {
				throw exceptionHandler.constructAsmsException(((AsmsException) ex).getCode(),
						((AsmsException) ex).getDescription());
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
						messages.getString("SYSTEM_EXCEPTION"));
			}

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserBasicDetails> getStaffMemberByRoleName(String roleName, String tenant) throws AsmsException {
		Session session = null;
		List<UserBasicDetails> userBasicDetailsList = new ArrayList<UserBasicDetails>();
		try {
			messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenant);
			Query q=null;
			
			
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			if (schema != null) {
				
				if(roleName.equals(Constants.role_teaching_staff))
				{
					//String hql = "select T.firstName, T.lastName from TeachingStaff T  where T.roleObject.roleName =?";
					String hql = "SELECT A.t_staff_first_name,A.t_staff_last_name FROM "+schema+".teaching_staff as A join "+schema+".user_information as B on A.serial_no=B.serial_no and B.role_id=(select role_id from "+schema+".roles where role_name='"+Constants.role_teaching_staff+"')";
					q = session.createSQLQuery(hql);
					//q = session.createQuery(hql).setParameter(0, Constants.role_teaching_staff);
					List<Object[]> l = (List<Object[]>) q.list();
					String name;
					//List<UserBasicDetails> userBasicDetailsList = new ArrayList<UserBasicDetails>();
					for (Object[] teachers : l) {
						UserBasicDetails userBasicDetails = new UserBasicDetails();
						name = (String) teachers[0] + " " + (String) teachers[1];
						userBasicDetails.setFirstName(name);
						userBasicDetailsList.add(userBasicDetails);
					}
					session.close();
					// return list of users of teachers 
					//return userBasicDetailsList;
				}
				else if(roleName.equals(Constants.role_non_teaching_staff))
				{
					String hql = "select U.firstName, U.lastName from User U where U.roleObject.roleName =?";
					q = session.createQuery(hql).setParameter(0, Constants.role_non_teaching_staff);
					List<Object[]> l = (List<Object[]>) q.list();
					String name;
					
					for (Object[] nonTeachers : l) {
						UserBasicDetails userBasicDetails = new UserBasicDetails();
						name = (String) nonTeachers[0] + " " + (String) nonTeachers[1];
						userBasicDetails.setFirstName(name);
						userBasicDetailsList.add(userBasicDetails);
					}
					session.close();
					// return list of users of non teaching staff 
					//return userBasicDetailsList;
				
					
				}
				
				
				

			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}

			

			
			// return entityCreator.createUserBasicDetails(users);

		} catch (Exception ex) {
			if (null != session && session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "search()" + "   ", ex);
			if (ex instanceof AsmsException) {
				throw exceptionHandler.constructAsmsException(((AsmsException) ex).getCode(),
						((AsmsException) ex).getDescription());
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
						messages.getString("SYSTEM_EXCEPTION"));
			}
		} finally {
			if (null != session && session.isOpen()) {
				session.close();
			}
		}
		return userBasicDetailsList;
		
		
	}

	@Override
	public List<StudentAttendanceDetails> getAttendanceOfStudent(String studentId, String tenant) throws AsmsException {
		
		
		String schoolStartDate = "2017-10-15";
		//int noOfDaysPresent=0;
		//int noOfDaysAbsent=0;
		//Date today;
		List<StudentAttendanceDetails> studentAttendanceDetailsList = new ArrayList<StudentAttendanceDetails>();
		Session session = null;
		//List<UserBasicDetails> userBasicDetailsList = new ArrayList<UserBasicDetails>();
		try {
			messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenant);
			Query q=null;
			
			
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			if (schema != null) {
				
				
					//String hql = "select T.firstName, T.lastName from TeachingStaff T  where T.roleObject.roleName =?";
					String hql = 
"SELECT * FROM "+schema+".student_attendance where studentId='"+studentId+"' and date between '"+schoolStartDate+"' And now();";
					q = session.createSQLQuery(hql);
					//q = session.createQuery(hql).setParameter(0, Constants.role_teaching_staff);
					@SuppressWarnings("unchecked")
					List<Object[]> l =  q.list();
					//String name;
					//List<UserBasicDetails> userBasicDetailsList = new ArrayList<UserBasicDetails>();
					for (Object[] student : l) {
						StudentAttendanceDetails studentAttendanceDetails = new StudentAttendanceDetails();

						studentAttendanceDetails.setStdId((String)student[1]);
						studentAttendanceDetails.setAcademicYear((String)student[2]);
						studentAttendanceDetails.setDate((Date)student[3]);
						studentAttendanceDetails.setName((String)student[4]);
						studentAttendanceDetails.setClassName((String)student[5]);
						studentAttendanceDetails.setSectionName((String)student[6]);
						studentAttendanceDetails.setAfternoonSession((String)student[7]);
						studentAttendanceDetails.setMorningSession((String)student[8]);
						
						
						studentAttendanceDetailsList.add(studentAttendanceDetails);
					}
					session.close();
					// return list of users of teachers 
					//return userBasicDetailsList;
				
				
				

			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}

			

			
			// return entityCreator.createUserBasicDetails(users);

		} catch (Exception ex) {
			if (null != session && session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getAttendanceOfStudent()" + "   ", ex);
			if (ex instanceof AsmsException) {
				throw exceptionHandler.constructAsmsException(((AsmsException) ex).getCode(),
						((AsmsException) ex).getDescription());
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
						messages.getString("SYSTEM_EXCEPTION"));
			}
		} finally {
			if (null != session && session.isOpen()) {
				session.close();
			}
		}
		return studentAttendanceDetailsList;
		
		
		
		
		
	}

	@Override
	public List<StaffAttendanceDetails> getAttendanceOfStaff(String staffId, String tenant) throws AsmsException {

		
		
		String schoolStartDate = "2017-10-15";
		//int noOfDaysPresent=0;
		//int noOfDaysAbsent=0;
		//Date today;
		List<StaffAttendanceDetails> staffAttendanceDetailsList = new ArrayList<StaffAttendanceDetails>();
		Session session = null;
		//List<UserBasicDetails> userBasicDetailsList = new ArrayList<UserBasicDetails>();
		try {
			messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenant);
			Query q=null;
			
			
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			if (schema != null) {
				
				
					//String hql = "select T.firstName, T.lastName from TeachingStaff T  where T.roleObject.roleName =?";
					String hql = 
"SELECT * FROM "+schema+".staff_attendance where staffId='"+staffId+"' and date between '"+schoolStartDate+"' And now();";
					q = session.createSQLQuery(hql);
					//q = session.createQuery(hql).setParameter(0, Constants.role_teaching_staff);
					@SuppressWarnings("unchecked")
					List<Object[]> l = (List<Object[]>) q.list();
					//String name;
					//List<UserBasicDetails> userBasicDetailsList = new ArrayList<UserBasicDetails>();
					for (Object[] satff : l) {
						StaffAttendanceDetails staffAttendanceDetails = new StaffAttendanceDetails();
						staffAttendanceDetails.setAcademicYear((String)satff[1]);
						staffAttendanceDetails.setStaffId((String)satff[5]);
						
						staffAttendanceDetails.setDate((Date)satff[2]);
						staffAttendanceDetails.setName((String)satff[3]);
						staffAttendanceDetails.setStatus((String)satff[4]);
						
						
						
						
						staffAttendanceDetailsList.add(staffAttendanceDetails);
					}
					session.close();
					// return list of users of teachers 
					//return userBasicDetailsList;
				
				
				

			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}

			

			
			// return entityCreator.createUserBasicDetails(users);

		} catch (Exception ex) {
			if (null != session && session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getAttendanceOfStudent()" + "   ", ex);
			if (ex instanceof AsmsException) {
				throw exceptionHandler.constructAsmsException(((AsmsException) ex).getCode(),
						((AsmsException) ex).getDescription());
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
						messages.getString("SYSTEM_EXCEPTION"));
			}
		} finally {
			if (null != session && session.isOpen()) {
				session.close();
			}
		}
		return staffAttendanceDetailsList;
		
		
		
		
		
	
	}

}
