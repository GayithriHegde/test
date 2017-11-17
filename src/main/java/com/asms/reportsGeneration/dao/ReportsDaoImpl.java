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
import com.asms.multitenancy.dao.MultitenancyDao;
import com.asms.reportsGeneration.helper.ReportExcelMaker;
import com.asms.reportsGeneration.request.CurriculamDetails;
import com.asms.usermgmt.entity.Admin;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.entity.management.Management;
import com.asms.usermgmt.entity.nonTeachingStaff.NonTeachingStaff;
import com.asms.usermgmt.entity.student.Student;
import com.asms.usermgmt.entity.teachingStaff.TeachingStaff;


//reports implementation goes here
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

		String[] columns = new String[] { "Unit number", "Unit title", "No.of periods required", "Plan Date",
				"Status" };

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
						+ "U.curriculumObject.className =? and U.curriculumObject.subject = ?";

				q = session.createQuery(hql).setParameter(0, curriculamDetails.getAcademicYear())
						.setParameter(1, curriculamDetails.getClassName())
						.setParameter(2, curriculamDetails.getSubject());

				List<Unit> units = (List<Unit>) q.list();

				for (Unit records : units) {

					rowData.add(records.getUnitNo());
					rowData.add(records.getUnitName());
					rowData.add(records.getNoOfPeriods());
					rowData.add(records.getMonth());
					rowData.add(records.getStatus());

				}

				String[] rowArray = rowData.toArray(new String[rowData.size()]);
				reportExcelMaker.excelCurriculumMaker(title, subTitle, columns, rowArray);

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

	@Override
	public void getAllUsers(String year, String tenant) throws AsmsException {
		messages = AsmsHelper.getMessageFromBundle();

		String[] columns = new String[] { "SINO","Staff Name", "Staff Type", "Staff Id", "Gender", "Qualification",
				"phone Number", "DOB" };

		String title = "----------------Staff Details---------------------";
		String subTitle = "List of Users for the year " + year;

		Query q = null;
		Session session = null;

		try {
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenant);
			List<String> rowData = new ArrayList<String>();
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();

				String hql = "from User U where U.admissionForYear = ?";

				q = session.createQuery(hql).setParameter(0, year);
				List<User> userList = (List<User>) q.list();
				
				for (User userType : userList) {
					if(((User) userType).getRoleObject().getRoleName().equals(Constants.role_admin))
					{
						
						Admin admin = (Admin) userType;
						
						rowData.add(String.valueOf(admin.getSerialNo()));
						rowData.add(admin.getName());
						rowData.add(((User) userType).getRoleObject().getRoleName());
						rowData.add(admin.getUserId());
						
						
					}
					if(((User) userType).getRoleObject().getRoleName().equals(Constants.role_student))
					{
						
						Student student = (Student) userType;
						
						rowData.add(String.valueOf(student.getSerialNo()));
						rowData.add(student.getStudentFirstName());
						
						rowData.add(((User) userType).getRoleObject().getRoleName());
						rowData.add(student.getUserId());
						rowData.add(student.getStudentGender());
						rowData.add(student.getStudentClass());
						rowData.add(String.valueOf(student.getParentObject().getfContactNumber()));
						rowData.add(String.valueOf(student.getStudentDob()));
						
					}
					if(((User) userType).getRoleObject().getRoleName().equals(Constants.role_management))
					{
						
						Management management = (Management) userType;
						
						rowData.add(String.valueOf(management.getSerialNo()));
						rowData.add(management.getMngmtFirstName());
						rowData.add(((User) userType).getRoleObject().getRoleName());
						rowData.add(management.getUserId());
						
						rowData.add(management.getMngmtContactNo());
						
					}
					if(((User) userType).getRoleObject().getRoleName().equals(Constants.role_teaching_staff))
					{
						
						TeachingStaff teachingStaff = (TeachingStaff) userType;
						
						rowData.add(String.valueOf(teachingStaff.getSerialNo()));
						rowData.add(teachingStaff.getFirstName());
						
						rowData.add(((User) userType).getRoleObject().getRoleName());
						rowData.add(teachingStaff.getUserId());
						rowData.add(teachingStaff.getGender());
						rowData.add(teachingStaff.getQualification());
						rowData.add(String.valueOf(teachingStaff.getContactNo()));
						rowData.add(String.valueOf(teachingStaff.getDob()));
						
					}
					if(((User) userType).getRoleObject().getRoleName().equals(Constants.role_non_teaching_staff))
					{
						
						NonTeachingStaff nonTeachingStaff = (NonTeachingStaff) userType;
						
						rowData.add(String.valueOf(nonTeachingStaff.getSerialNo()));
						rowData.add(nonTeachingStaff.getFirstName());
						
						rowData.add(((User) userType).getRoleObject().getRoleName());
						rowData.add(nonTeachingStaff.getUserId());
						rowData.add(nonTeachingStaff.getGender());
						rowData.add(nonTeachingStaff.getQualification());
						rowData.add(String.valueOf(nonTeachingStaff.getContactNo()));
						rowData.add(String.valueOf(nonTeachingStaff.getDob()));
						
					}
					
					
					
				}
				session.close();
				//return userBasicDetailsList;
				
				
				String[] rowArray = rowData.toArray(new String[rowData.size()]);
				reportExcelMaker.excelMakerListOfUser(title, subTitle, columns, rowArray);

			//	session.close();
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
