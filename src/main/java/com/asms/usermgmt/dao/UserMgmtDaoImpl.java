package com.asms.usermgmt.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.asms.Exception.AsmsException;
import com.asms.Exception.ExceptionHandler;
import com.asms.common.helper.AsmsHelper;
import com.asms.common.helper.Constants;
import com.asms.multitenancy.dao.MultitenancyDao;
import com.asms.multitenancy.entity.Activity;
import com.asms.multitenancy.entity.DefaultActivities;
import com.asms.multitenancy.entity.SuperAdmin;
import com.asms.multitenancy.entity.Tenant;
import com.asms.rolemgmt.entity.Role;
import com.asms.rolemgmt.entity.SubRole;
import com.asms.schoolmgmt.dao.SchoolMgmtDao;
import com.asms.schoolmgmt.entity.Class;
import com.asms.schoolmgmt.entity.School;
import com.asms.schoolmgmt.entity.Section;
import com.asms.usermgmt.entity.Privilege;
import com.asms.usermgmt.entity.StudentType;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.entity.akacartUser.AkacartUser;
import com.asms.usermgmt.entity.management.Management;
import com.asms.usermgmt.entity.nonTeachingStaff.Address;
import com.asms.usermgmt.entity.nonTeachingStaff.NonTeachingStaff;
import com.asms.usermgmt.entity.nonTeachingStaff.StaffDocuments;
import com.asms.usermgmt.entity.nonTeachingStaff.StaffPreviousInformation;
import com.asms.usermgmt.entity.nonTeachingStaff.StaffStatutory;
import com.asms.usermgmt.entity.student.Parent;
import com.asms.usermgmt.entity.student.Sibling;
import com.asms.usermgmt.entity.student.Student;
import com.asms.usermgmt.entity.student.StudentAddress;
import com.asms.usermgmt.entity.student.StudentDocuments;
import com.asms.usermgmt.entity.student.StudentPreviousInfo;
import com.asms.usermgmt.entity.teachingStaff.Address1;
import com.asms.usermgmt.entity.teachingStaff.StaffDocuments1;
import com.asms.usermgmt.entity.teachingStaff.StaffPreviousInformation1;
import com.asms.usermgmt.entity.teachingStaff.StaffStatutory1;
import com.asms.usermgmt.entity.teachingStaff.TeachingStaff;
import com.asms.usermgmt.entity.teachingStaff.TeachingSubjects;
import com.asms.usermgmt.helper.EntityCreator;
import com.asms.usermgmt.request.ChangePasswordDetails;
import com.asms.usermgmt.request.TeachingSubjectDetails;
import com.asms.usermgmt.request.UserDetails;
import com.asms.usermgmt.request.AkacartUserDetails.AkacartUserDetails;
import com.asms.usermgmt.request.nonTeachingStaff.AddressDetails;
import com.asms.usermgmt.request.nonTeachingStaff.NonTeachingStaffDetails;
import com.asms.usermgmt.request.nonTeachingStaff.StaffDocumentsDetails;
import com.asms.usermgmt.request.nonTeachingStaff.StaffPreviousInformationDetails;
import com.asms.usermgmt.request.nonTeachingStaff.StaffStatutoryDetails;
import com.asms.usermgmt.request.student.ParentDetails;
import com.asms.usermgmt.request.student.StudentDetails;
import com.asms.usermgmt.request.student.StudentDocumentDetails;
import com.asms.usermgmt.request.teachingStaff.StaffDocumentsDetails1;
import com.asms.usermgmt.request.teachingStaff.StaffPreviousInformationDetails1;
import com.asms.usermgmt.request.teachingStaff.StaffStatutoryDetails1;
import com.asms.usermgmt.request.teachingStaff.TeachingStaffDetails;
import com.asms.usermgmt.response.LoginResponse;
import com.asms.usermgmt.response.RegistrationResponse;
import com.asms.usermgmt.service.CasteTypes;
import com.asms.usermgmt.service.QualificationType;
import com.asms.usermgmt.service.ReligionTypes;
import com.asms.usermgmt.service.UserMgmtService;

@Service
@Component
public class UserMgmtDaoImpl implements UserMgmtDao {

	private int initialValue = 20;
	private float percentage = 100.00f;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ExceptionHandler exceptionHandler;

	@Autowired
	private EntityCreator entityCreator;

	@Autowired
	private SchoolMgmtDao schoolMgmtDao;

	@Resource(name = "asmsProperties")
	private Properties properties;

	@Resource(name = "asmsdbProperties")
	private Properties dbProperties;

	@Autowired
	private MultitenancyDao multitenancyDao;

	ResourceBundle messages;
	private AkacartUser AkacartUser;
	private com.asms.usermgmt.entity.akacartUser.AkacartUser akacartUser;

	// RegistrationResponse rReponse;

	private static final Logger logger = LoggerFactory.getLogger(UserMgmtService.class);

	/*
	 * Method : getUserRole : gets the user role from database input : String
	 * (email) return : String
	 *
	 */

	@Override
	public String getUserRole(String email, String tenant) throws AsmsException {
		Session session = null;
		try {
			messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenant);
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				String hql = "Select U.roleObject.roleName from User U where U.email=?";
				String role = (String) session.createQuery(hql).setParameter(0, email).uniqueResult();
				session.close();
				return role;
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getUserRole()" + "   ", e);

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
	public User getUser(String email, String tenant) throws AsmsException {
		Session session = null;
		try {
			messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenant);
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				String hql = "from User U where U.email=?";
				User user = (User) session.createQuery(hql).setParameter(0, email).uniqueResult();
				session.close();
				return user;
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getUser()" + "   ", e);
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
	 * getUserById : This method retrieves user object from database using
	 * userid
	 * 
	 * parameters: String userid
	 * 
	 * return User Object
	 * 
	 * 
	 */

	@Override
	public User getUserById(String userId, String schema) throws AsmsException {
		Session session = null;
		try {

			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			String hql = "from User U where U.userId=?";
			User user = (User) session.createQuery(hql).setParameter(0, userId).uniqueResult();
			session.close();
			return user;

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getUserById()" + "   ", e);
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

	/*
	 * getUserById : This method retrieves user object from database using
	 * userid
	 * 
	 * parameters: String userid
	 * 
	 * return User Object
	 * 
	 * 
	 */

	@Override
	public User getUserDetailsById(String userId, String tenant) throws AsmsException {
		Session session = null;
		try {
			messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenant);
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				String hql = "from User U where U.userId=?";
				User user = (User) session.createQuery(hql).setParameter(0, userId).uniqueResult();
				session.close();
				return user;
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}
		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getUserDetailsById()" + "   ", e);
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
	public Student getStudentByUserId(String userId, String tenant) throws AsmsException {
		Session session = null;
		try {
			messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenant);
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				String hql = "from Student U where U.userId=?";
				Student student = (Student) session.createQuery(hql).setParameter(0, userId).uniqueResult();
				session.close();
				return student;
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getStudentByUserId()" + "   ", e);
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

	public Student getStudentByStudentId(int studentId, String schema) throws AsmsException {
		Session session = null;
		try {

			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			String hql = "from Student U where U.serialNo=?";
			Student student = (Student) session.createQuery(hql).setParameter(0, studentId).uniqueResult();
			session.close();
			return student;

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getStudentByStudentId()" + "   ", e);
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
	public Role getRoleObject(String roleName, String schema) throws AsmsException {
		Session session = null;
		try {
			messages = AsmsHelper.getMessageFromBundle();

			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			String hql = "from Role U where U.roleName=?";
			Role role = (Role) session.createQuery(hql).setParameter(0, roleName).uniqueResult();
			session.close();
			return role;

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getRoleObject()" + "   ", e);
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
	public SubRole getSubRoleObject(String roleName, String schema) throws AsmsException {
		Session session = null;
		try {
			messages = AsmsHelper.getMessageFromBundle();
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			String hql = "from SubRole U where U.subRoleName=?";
			SubRole sRole = (SubRole) session.createQuery(hql).setParameter(0, roleName).uniqueResult();
			session.close();
			return sRole;

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getSubRoleObject()" + "   ", e);
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

	private List<TeachingSubjects> getTeachingSubjects(UserDetails details, TeachingStaff tStaff, String tenant)
			throws AsmsException {

		List<TeachingSubjects> teachingSubjects = new ArrayList<TeachingSubjects>();

		List<TeachingSubjectDetails> subjectDetailsList = details.getTeachingStaffDetails()
				.getTeachingSubjectDetailsList();
		TeachingSubjects tSubjects;
		Class classObject;
		Section sectionObject;

		for (int i = 0; i < subjectDetailsList.size(); i++) {

			TeachingSubjectDetails subjectDetails = subjectDetailsList.get(i);
			tSubjects = new TeachingSubjects();
			String className = subjectDetails.getClassName();
			String subject = subjectDetails.getSubject();
			String section = subjectDetails.getSectionName();
			classObject = schoolMgmtDao.getClassByName(className, tenant);

			sectionObject = schoolMgmtDao.getSectionByName(className, section, tenant);
			if (null != classObject && null != sectionObject) {
				tSubjects.setClassObject(classObject);
				tSubjects.setSubject(subject);
				tSubjects.setSectionObject(sectionObject);
				tSubjects.setTeachingObject(tStaff);
				teachingSubjects.add(tSubjects);
			}

		}
		return teachingSubjects;
	}

	/*
	 * Method : registerUser : inserts user details into database input :
	 * UserDetails return : void
	 *
	 */
	@Override
	public RegistrationResponse registerUser(UserDetails userDetails, User user, String tenant) throws AsmsException {
		RegistrationResponse rReponse = new RegistrationResponse();
		try {
			String schema = multitenancyDao.getSchema(tenant);
			if (null == schema) {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));

			}
			School school = schoolMgmtDao.getSchool(schema);
			String userid = generateUserId();
			String email = userDetails.getEmail();
			User userFromDB = getUser(email, tenant);
			if (null == userFromDB) {

				if (userDetails.getRole().equalsIgnoreCase(Constants.role_student)) {
					Role role = getRoleObject(userDetails.getRole(), schema);
					SubRole sRole = getSubRoleObject(userDetails.getSubRole(), schema);
					if (null != role && null != sRole) {
						Student student = entityCreator.createStudent(userDetails.getStudentDetails(), user);
						student.setUserId(userid);
						student.setEmail(userDetails.getEmail());
						student.setRoleObject(role);
						student.setSubRoleObject(sRole);
						student.setStatus("incomplete");
						student.setUserPassword(userDetails.getUserPassword());
						student.setSchoolId(school.getSerialNo());
						student.setAdmissionForYear(userDetails.getAdmissionForYear());
						createDefaultPrivileges(Constants.role_student, student);

						// attach sibling
						Sibling elderSiblingObject = null;
						if (null != userDetails.getStudentDetails().getSiblingDetails()) {
							int studentId = userDetails.getStudentDetails().getSiblingDetails().getStudent_id();
							if (studentId > 0) {
								List<Sibling> siblings = getSiblings(studentId, schema);
								if (null == siblings || siblings.isEmpty()) {
									// create sibling_id
									int siblingId = studentId;

									// for elder student
									Student elderSibling = getStudentByStudentId(studentId, schema);
									if (null != elderSibling) {
										elderSiblingObject = new Sibling();
										elderSiblingObject.setSiblingId(siblingId);
										elderSiblingObject.setStudentObject(elderSibling);

										// for new student
										Sibling newSibling = new Sibling();
										newSibling.setStudentObject(student);
										newSibling.setSiblingId(siblingId);
										student.setSibling(newSibling);
									}

								} else {
									Student elderSibling = getStudentByStudentId(studentId, schema);
									Sibling newSibling = new Sibling();
									newSibling.setStudentObject(student);
									newSibling.setSiblingId(elderSibling.getSibling().getSiblingId());
									student.setSibling(newSibling);
								}

							} else {
								Object e = null;
								// log
								logger.error("Session Id: " + MDC.get("studentId") + "   " + "Method: "
										+ this.getClass().getName() + "." + "getSibling()" + "   ", e);
								// throw
								// exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
								// messages.getString("TENANT_INVALID_CODE_MSG"));

							}
						}

						// SiblingDetails sd =
						// userDetails.getStudentDetails().getSiblingDetails();

						insertStudent(student, elderSiblingObject, schema);

						rReponse.setProgressPercentage(initialValue);
						rReponse.setUserId(userid);

					} else {

						logger.debug("role not matched ");
					}

				} else if (userDetails.getRole().equalsIgnoreCase(Constants.role_management)) {
					Role role = getRoleObject(userDetails.getRole(), schema);
					SubRole sRole = getSubRoleObject(userDetails.getSubRole(), schema);
					if (null != role && null != sRole) {
						Management management = entityCreator.createManagement(userDetails.getManagementDetails(),
								user);
						management.setUserId(userid);
						management.setEmail(userDetails.getEmail());
						management.setRoleObject(role);
						management.setSubRoleObject(sRole);
						management.setUserPassword(userDetails.getUserPassword());
						management.setSchoolId(school.getSerialNo());
						management.setAdmissionForYear(userDetails.getAdmissionForYear());
						createDefaultPrivileges(Constants.role_management, management);
						insertManagement(management, schema);

						rReponse.setProgressPercentage(100);
						rReponse.setUserId(userid);

					} else {
						logger.debug("role not matched");

					}

				} else if (userDetails.getRole().equalsIgnoreCase(Constants.role_teaching_staff)) {
					Role role = getRoleObject(userDetails.getRole(), schema);
					SubRole sRole = getSubRoleObject(userDetails.getSubRole(), schema);
					if (null != role && null != sRole) {
						TeachingStaff teachingStaff = entityCreator
								.createTeachingStaff(userDetails.getTeachingStaffDetails(), user);

						teachingStaff.setUserPassword(userDetails.getUserPassword());
						teachingStaff.setUserId(userid);
						teachingStaff.setEmail(userDetails.getEmail());
						teachingStaff.setRoleObject(role);
						teachingStaff.setSubRoleObject(sRole);
						teachingStaff.setSchoolId(school.getSerialNo());
						teachingStaff.setAdmissionForYear(userDetails.getAdmissionForYear());
						teachingStaff.setTeachingSubjects((getTeachingSubjects(userDetails, teachingStaff, schema)));
						createDefaultPrivileges(Constants.role_teaching_staff, teachingStaff);
						insertTeachingStaff(teachingStaff, schema);

						rReponse.setProgressPercentage(initialValue);
						rReponse.setUserId(userid);

					} else {
						logger.debug("role not matched");
					}

				} else if (userDetails.getRole().equalsIgnoreCase(Constants.role_non_teaching_staff)) {
					Role role = getRoleObject(userDetails.getRole(), schema);
					SubRole sRole = getSubRoleObject(userDetails.getSubRole(), schema);
					if (null != role && null != sRole) {
						NonTeachingStaff nonTeachingStaff = entityCreator
								.createNonTeachingStaff(userDetails.getNonTeachingStaffDetails(), user);

						nonTeachingStaff.setUserPassword(generatePassword(Constants.role_non_teaching_staff));
						nonTeachingStaff.setUserId(userid);
						nonTeachingStaff.setEmail(userDetails.getEmail());
						nonTeachingStaff.setRoleObject(role);
						nonTeachingStaff.setSubRoleObject(sRole);
						nonTeachingStaff.setSchoolId(school.getSerialNo());
						nonTeachingStaff.setAdmissionForYear(userDetails.getAdmissionForYear());
						createDefaultPrivileges(Constants.role_non_teaching_staff, nonTeachingStaff);
						insertNonTeachingStaff(nonTeachingStaff, schema);
						rReponse.setProgressPercentage(initialValue);
						rReponse.setUserId(userid);
					} else {
						logger.debug("role not matched");
					}
				}
			} else {
				throw this.exceptionHandler.constructAsmsException(this.messages.getString("USER_EXISTS_CODE"),
						this.messages.getString("USER_EXISTS_MSG"));
			}
			return rReponse;

		} catch (Exception e) {
			logger.error(
					"Session Id: " + MDC.get("sessionId") + "   Method: " + getClass().getName() + ".registerUser()   ",
					e);
			if ((e instanceof AsmsException)) {
				throw this.exceptionHandler.constructAsmsException(((AsmsException) e).getCode(),
						((AsmsException) e).getDescription());
			}
			throw this.exceptionHandler.constructAsmsException(this.messages.getString("SYSTEM_EXCEPTION_CODE"),
					this.messages.getString("SYSTEM_EXCEPTION"));
		}

	}

	/*
	 * <<<<<<< HEAD Method : insertUser : inserts User entity into database
	 * input : user return : void ======= Method : insertStudent : inserts
	 * Student entity into database input : student return : void >>>>>>> branch
	 * 'master' of https://github.com/akaperi/asms-services
	 *
	 */

	@Override
	public void insertUser(User user, String schema) throws AsmsException {
		Session session = null;
		Transaction tx = null;
		try {
			messages = AsmsHelper.getMessageFromBundle();

			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();
			session.save(user);

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
					+ "insertUser()" + "   ", ex);
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

	/*
	 * Method : insertStudent : inserts Student entity into database input :
	 * student return : void
	 *
	 */

	// generate default encrypted password
	private String generatePassword(String role) {
		// return BCrypt.hashpw(role + "123", BCrypt.gensalt(10));
		return role + "123";
	}

	// generate userid
	private String generateUserId() {
		return UUID.randomUUID().toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asms.usermgmt.dao.UserMgmtDao#insertStudent(com.asms.usermgmt.entity.
	 * Student)
	 */

	private void insertStudent(Student student, Sibling elderSibling, String schema) throws AsmsException {
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();

			session.save(student);
			if (null != elderSibling) {
				session.save(elderSibling);
			}

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

					+ "insertStudent()" + "   ", ex);

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asms.usermgmt.dao.UserMgmtDao#insertManagement(com.asms.usermgmt.
	 * entity.Management)
	 */
	private void insertManagement(Management management, String schema) throws AsmsException {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();
			session.save(management);
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

					+ "insertManagement()" + "   ", ex);

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

	// method comments
	@Override
	public LoginResponse authenticateAkaperiUser(HttpServletRequest request, HttpServletResponse response, String email,
			String password) throws AsmsException {

		Session session = null;
		LoginResponse loginResponse = null;

		try {

			String hql;

			session = sessionFactory.withOptions().tenantIdentifier(dbProperties.getProperty("default_schema"))
					.openSession();
			hql = "from SuperAdmin U where U.username=? and U.password=?";
			SuperAdmin admin = (SuperAdmin) session.createQuery(hql).setParameter(0, email).setParameter(1, password)
					.uniqueResult();
			session.close();
			if (null == admin) {
				logger.info("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
						+ "authenticate()" + "   ", "Authentication failed");
				return null;
			} else {

				HttpSession httpSession = request.getSession(false);
				Role role = new Role();
				role.setRoleName(Constants.role_super_admin);
				User user = new User();
				user.setRoleObject(role);

				user.setEmail(admin.getUsername());
				user.setUserPassword(admin.getPassword());

				httpSession.setAttribute("ap_user", user);
				loginResponse = new LoginResponse();
				loginResponse.setParent(false);
				loginResponse.setRole(role.getRoleName());

				return loginResponse;
			}

		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}

	@Override
	public LoginResponse authenticate(HttpServletRequest request, HttpServletResponse response, String domain,
			String email, String password) throws AsmsException {
		Session session = null;
		LoginResponse loginResponse = null;

		try {

			String hql;
			session = sessionFactory.withOptions().tenantIdentifier(dbProperties.getProperty("default_schema"))
					.openSession();
			hql = "from Tenant U where U.subDomain=?";
			Tenant tenant = (Tenant) session.createQuery(hql).setParameter(0, domain).uniqueResult();
			session.close();
			if (null == tenant) {
				logger.info("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
						+ "authenticate()" + "   ", "Authentication failed TENANT_INVALID_CODE");
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));

			} else {
				session = sessionFactory.withOptions().tenantIdentifier(tenant.getName()).openSession();
				hql = "from User U where U.email=? ";
				User user = (User) session.createQuery(hql).setParameter(0, email)
						.uniqueResult();

				loginResponse = new LoginResponse();

				if (null == user) {
					Parent parent = null;

					// check if user there in parent table

					hql = "from Parent P where P.fEmail=? and P.fpassword=?";
					parent = (Parent) session.createQuery(hql).setParameter(0, email).setParameter(1, password)
							.setMaxResults(1).uniqueResult();
					if (null != parent) {
						loginResponse.setParent(true);
						loginResponse.setFather(true);
					}

					// parent not null

					hql = "from Parent P where P.mEmail=? and P.mpassword=?";
					parent = (Parent) session.createQuery(hql).setParameter(0, email).setParameter(1, password)
							.setMaxResults(1).uniqueResult();
					if (null != parent) {
						loginResponse.setParent(true);
						loginResponse.setMother(true);
					}

					session.close();

					if (null == parent) {
						logger.info("Session Id: " + MDC.get("sessionId") + "   " + "Method: "
								+ this.getClass().getName() + "." + "authenticate()" + "   ", "Authentication failed");
						throw exceptionHandler.constructAsmsException(messages.getString("AUTHENTICATION_FAILED_CODE"),
								messages.getString("AUTHENTICATION_FAILED_MSG"));
					}

					user = new User();
					Role role = new Role();
					role.setRoleName(Constants.role_parent);
					user.setRoleObject(role);
					user.setPrivileges(parent.getStudentObject().getPrivileges());
					user.setIsNew(parent.getIsNew());

					HttpSession httpSession = request.getSession(false);
					httpSession.setAttribute("ap_user", user);
					// user = (User)httpSession.getAttribute("ap_user");
					loginResponse.setParent(true);
					loginResponse.setPrivileges(new ArrayList<Privilege>(user.getPrivileges()));
					loginResponse.setNew(user.getIsNew());
					return loginResponse;

				} else {

					boolean isPasswaordSame = BCrypt.checkpw(password, user.getUserPassword());
					if (isPasswaordSame) {
						HttpSession httpSession = request.getSession(false);
						httpSession.setAttribute("ap_user", user);
						// user = (User)httpSession.getAttribute("ap_user");
						loginResponse.setParent(false);
						loginResponse.setPrivileges(new ArrayList<Privilege>(user.getPrivileges()));
						loginResponse.setNew(user.getIsNew());
						return loginResponse;
					} else {
						logger.info("Session Id: " + MDC.get("sessionId") + "   " + "Method: "
								+ this.getClass().getName() + "." + "authenticate()" + "   ", "Authentication failed");
						throw exceptionHandler.constructAsmsException(messages.getString("AUTHENTICATION_FAILED_CODE"),
								messages.getString("AUTHENTICATION_FAILED_MSG"));
					}
				}

			}
		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "authenticate()" + "   ", e);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.asms.usermgmt.dao.UserMgmtDao#insertNonTeachingStaff(com.asms.
	 * usermgmt.entity.NonTeachingStaff)
	 */

	private void insertNonTeachingStaff(NonTeachingStaff nonTeachingStaff, String schema) throws AsmsException {
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();

			session.save(nonTeachingStaff);

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

					+ "insertNonTeachingStaff()" + "   ", ex);

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asms.usermgmt.dao.UserMgmtDao#insertTeachingStaff(com.asms.usermgmt.
	 * entity.TeachingStaff)
	 */
	private void insertTeachingStaff(TeachingStaff teachingStaff, String schema) throws AsmsException {
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();

			session.save(teachingStaff);

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

					+ "insertTeachingStaff()" + "   ", ex);

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

	private void insertParent(Parent parent, String schema) throws AsmsException {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();

			String hql = "from Parent P where P.fEmail=? or P.fpassword=?";
			// get List of parents with the new father email and mother email
			@SuppressWarnings("unchecked")

			List<Parent> parents = session.createQuery(hql).setParameter(0, parent.getfEmail())
					.setParameter(1, parent.getmEmail()).list();

			if (parents.isEmpty()) {
				// Register parent

				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				tx = session.beginTransaction();

				session.save(parent);
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("FEMAIL_ALREADY_EXIST_EXCEPTION_CODE"),
						messages.getString("FEMAIL_ALREADY_EXIST_EXCEPTION_MSG"));
			}
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

					+ "insertParent()" + "   ", ex);

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

	private void insertStudentAddress(StudentAddress address, String schema) throws AsmsException {
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();
			session.save(address);
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

					+ "insertStudentAddress()" + "   ", ex);

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

	private void insertStudentDocs(StudentDocuments docs, String schema) throws AsmsException {
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();

			session.save(docs);

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

					+ "insertStudentDocs()" + "   ", ex);

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

	private void insertStudentSiblings(List<Sibling> si, String schema) throws AsmsException {
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();

			for (Sibling sibling : si) {
				session.save(sibling);
				tx.commit();
			}

			session.close();

		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."

					+ "insertStudentSiblings()" + "   ", ex);

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

	private void insertPreviousInfo(StudentPreviousInfo details, String schema) throws AsmsException {
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();

			session.save(details);

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

					+ "insertPreviousInfo()" + "   ", ex);

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

	// this method adds additional details to the db
	@Override
	public RegistrationResponse addDetails(UserDetails details, User user, String tenant) throws AsmsException {

		RegistrationResponse rReponse = new RegistrationResponse();
		ResourceBundle messages = AsmsHelper.getMessageFromBundle();
		// get userid
		String userId = details.getUserId();
		String schema = multitenancyDao.getSchema(tenant);

		if (null == schema) {
			throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
					messages.getString("TENANT_INVALID_CODE_MSG"));

		}

		if (details.getRole().equalsIgnoreCase(Constants.role_student)) {
			StudentDetails sDetails = details.getStudentDetails();
			User studentUser = getUserById(userId, schema);

			if (null == studentUser) {

				throw exceptionHandler.constructAsmsException(messages.getString("USER_INVALID_CODE"),
						messages.getString("USER_INVALID_MSG"));

			}

			if (null != sDetails.getParentDetails()) {
				Parent parent = entityCreator.createParent(sDetails.getParentDetails(), user);
				parent.setStudentObject((Student) studentUser);

				// check if parent is already added

				Student st = (Student) studentUser;
				Parent parentFromDb = st.getParentObject();
				if (null == parentFromDb) {

					insertParent(parent, schema);
					initialValue = 1 + 1;
					Student s = (Student) studentUser;

					if (null != s.getStudentPreviousInfo()) {
						initialValue = initialValue + 1;
					}
					if (null != s.getStudentAddress()) {
						initialValue = initialValue + 1;
					}

					if (null != s.getStudentDocuments()) {
						initialValue = initialValue + 1;
					}
					if (null != s.getSibling()) {
						initialValue = initialValue + 1;
					}

					if (initialValue == 6) {
						s.setStatus("Complete");
					}

					rReponse.setProgressPercentage(calulateRegistrationProgress(initialValue, 6));
				} else {

					throw exceptionHandler.constructAsmsException(
							messages.getString("PARENT_ALREADY_EXIST_INVALID_CODE"),
							messages.getString("PARENT_ALREADY_EXIST_INVALID_CODE_MSG"));
				}

			}
			if (null != sDetails.getAddressDetails()) {
				StudentAddress address = entityCreator.createStudentAddress(sDetails.getAddressDetails(), user);
				address.setStudentObject((Student) studentUser);
				insertStudentAddress(address, schema);

				initialValue = 1 + 1;
				Student s = (Student) studentUser;

				if (null != s.getStudentPreviousInfo()) {
					initialValue = initialValue + 1;
				}
				if (null != s.getParentObject()) {
					initialValue = initialValue + 1;
				}

				if (null != s.getStudentDocuments()) {
					initialValue = initialValue + 1;
				}
				if (null != s.getSibling()) {
					initialValue = initialValue + 1;
				}

				if (initialValue == 6) {
					s.setStatus("Complete");
				}

				rReponse.setProgressPercentage(calulateRegistrationProgress(initialValue, 6));

			}
			if (null != sDetails.getDocumentDetails()) {
				StudentDocuments docs = entityCreator.createStudentDocuments(sDetails.getDocumentDetails(), user);
				docs.setStudentObject((Student) studentUser);
				insertStudentDocs(docs, schema);
				initialValue = 1 + 1;
				Student s = (Student) studentUser;

				if (null != s.getStudentPreviousInfo()) {
					initialValue = initialValue + 1;
				}
				if (null != s.getParentObject()) {
					initialValue = initialValue + 1;
				}

				if (null != s.getStudentAddress()) {
					initialValue = initialValue + 1;
				}
				if (null != s.getSibling()) {
					initialValue = initialValue + 1;
				}

				if (initialValue == 6) {
					s.setStatus("Complete");
				}

				rReponse.setProgressPercentage(calulateRegistrationProgress(initialValue, 6));

			}
			/*
			 * if (null != sDetails.getSiblingDetails()) { List<Sibling> si =
			 * entityCreator.createSibling(sDetails.getSiblingDetails(), user);
			 * for (int i = 0; i < si.size(); i++) {
			 * si.get(i).setStudentObject((Student) studentUser);
			 * insertStudentSiblings(si, schema);
			 * 
			 * initialValue = 1 + 1; Student s = (Student) studentUser;
			 * 
			 * if (null != s.getStudentPreviousInfo()) { initialValue =
			 * initialValue + 1; } if (null != s.getParentObject()) {
			 * initialValue = initialValue + 1; }
			 * 
			 * if (null != s.getStudentAddress()) { initialValue = initialValue
			 * + 1; } if (null != s.getStudentDocuments()) { initialValue =
			 * initialValue + 1; }
			 * 
			 * if (initialValue == 6) { s.setStatus("Complete"); }
			 * 
			 * rReponse.setProgressPercentage(calulateRegistrationProgress(
			 * initialValue, 6)); } }
			 */
			if (null != sDetails.getPreviousDetails()) {
				StudentPreviousInfo si;
				try {
					si = entityCreator.createPreviousDetails(sDetails.getPreviousDetails(), user);
				} catch (ParseException e) {
					throw exceptionHandler.constructAsmsException(messages.getString("DATE_INVALID_CODE"),
							messages.getString("DATE_INVALID"));
				}
				si.setStudentObject((Student) studentUser);
				insertPreviousInfo(si, schema);

				initialValue = 1 + 1;
				Student s = (Student) studentUser;

				if (null != s.getStudentPreviousInfo()) {
					initialValue = initialValue + 1;
				}
				if (null != s.getParentObject()) {
					initialValue = initialValue + 1;
				}

				if (null != s.getStudentAddress()) {
					initialValue = initialValue + 1;
				}
				if (null != s.getSibling()) {
					initialValue = initialValue + 1;
				}

				if (initialValue == 6) {
					s.setStatus("Complete");
				}

				rReponse.setProgressPercentage(calulateRegistrationProgress(initialValue, 6));

			}
		} else if (details.getRole().equalsIgnoreCase(Constants.role_non_teaching_staff)) {

			NonTeachingStaffDetails nonTeachingStaffDetails = details.getNonTeachingStaffDetails();

			userId = details.getUserId();

			User nonTechingUser = getUserById(userId, schema);

			if (null == nonTechingUser) {

				throw exceptionHandler.constructAsmsException(messages.getString("USER_INVALID_CODE"),
						messages.getString("USER_INVALID_MSG"));
			}
			if (null != nonTeachingStaffDetails.getAddressDetails()) {
				Address address = entityCreator.createAddressDetails(nonTeachingStaffDetails.getAddressDetails(), user);
				address.setnTeachingObject((NonTeachingStaff) nonTechingUser);
				insertaddressDetails(address, schema);
				initialValue = 1 + 1;
				NonTeachingStaff nT = (NonTeachingStaff) nonTechingUser;

				if (null != nT.getStaffDocuments()) {
					initialValue = initialValue + 1;
				}

				if (null != nT.getStaffPreviousInformation()) {
					initialValue = initialValue + 1;
				}
				if (null != nT.getStaffStatutory()) {
					initialValue = initialValue + 1;
				}

				if (initialValue == 6) {
					nT.setStatus("Complete");
				}

				rReponse.setProgressPercentage(calulateRegistrationProgress(initialValue, 5));

			}
			if (null != nonTeachingStaffDetails.getStaffDocumentsDetails()) {
				StaffDocuments StaffDocuments = entityCreator
						.createDocumentDetails(nonTeachingStaffDetails.getStaffDocumentsDetails(), user);
				StaffDocuments.setnTeachingObject((NonTeachingStaff) nonTechingUser);
				insertDocumentDetails(StaffDocuments, schema);

				initialValue = 1 + 1;
				NonTeachingStaff nT = (NonTeachingStaff) nonTechingUser;

				if (null != nT.getAddress()) {
					initialValue = initialValue + 1;
				}

				if (null != nT.getStaffPreviousInformation()) {
					initialValue = initialValue + 1;
				}
				if (null != nT.getStaffStatutory()) {
					initialValue = initialValue + 1;
				}

				if (initialValue == 6) {
					nT.setStatus("Complete");
				}

				rReponse.setProgressPercentage(calulateRegistrationProgress(initialValue, 5));

			}
			if (null != nonTeachingStaffDetails.getStaffPreviousInformationDetails()) {
				StaffPreviousInformation staffPreviousInformation;
				try {
					staffPreviousInformation = entityCreator.createStaffPreviousInformationDetails(
							nonTeachingStaffDetails.getStaffPreviousInformationDetails(), user);
					staffPreviousInformation.setnTeachingObject((NonTeachingStaff) nonTechingUser);
					insertPreviousInformationDetails(staffPreviousInformation, schema);

					initialValue = 1 + 1;
					NonTeachingStaff nT = (NonTeachingStaff) nonTechingUser;

					if (null != nT.getAddress()) {
						initialValue = initialValue + 1;
					}

					if (null != nT.getStaffDocuments()) {
						initialValue = initialValue + 1;
					}
					if (null != nT.getStaffStatutory()) {
						initialValue = initialValue + 1;
					}
					if (initialValue == 6) {
						nT.setStatus("Complete");
					}

					rReponse.setProgressPercentage(calulateRegistrationProgress(initialValue, 5));
				} catch (ParseException e) {
					throw exceptionHandler.constructAsmsException(messages.getString("DATE_INVALID_CODE"),
							messages.getString("DATE_INVALID"));
				}
			}

			if (null != nonTeachingStaffDetails.getStaffStatutoryDetails()) {
				StaffStatutory staffStatutory = entityCreator
						.createStaffStatutoryDetails(nonTeachingStaffDetails.getStaffStatutoryDetails(), user);
				staffStatutory.setnTeachingObject((NonTeachingStaff) nonTechingUser);
				insertStatutoryDetails(staffStatutory, schema);

				initialValue = 1 + 1;
				NonTeachingStaff nT = (NonTeachingStaff) nonTechingUser;

				if (null != nT.getAddress()) {
					initialValue = initialValue + 1;
				}

				if (null != nT.getStaffDocuments()) {
					initialValue = initialValue + 1;
				}
				if (null != nT.getStaffPreviousInformation()) {
					initialValue = initialValue + 1;
				}

				if (initialValue == 6) {
					nT.setStatus("Complete");
				}

				rReponse.setProgressPercentage(calulateRegistrationProgress(initialValue, 5));

			}
		}

		// -------------

		else if (details.getRole().equalsIgnoreCase(Constants.role_teaching_staff)) {

			TeachingStaffDetails teachingStaffDetails = details.getTeachingStaffDetails();

			userId = details.getUserId();

			User techingUser = getUserById(userId, schema);
			if (null == techingUser) {

				throw exceptionHandler.constructAsmsException(messages.getString("USER_INVALID_CODE"),
						messages.getString("USER_INVALID_MSG"));
			}
			if (null != teachingStaffDetails.getAddressDetails()) {
				Address1 address = entityCreator.createAddressDetails1(teachingStaffDetails.getAddressDetails(), user);
				address.setTeachingObject((TeachingStaff) techingUser);
				insertaddressDetails1(address, schema);

				initialValue = 1 + 1;
				TeachingStaff T = (TeachingStaff) techingUser;

				if (null != T.getStaffDocuments()) {
					initialValue = initialValue + 1;
				}

				if (null != T.getStaffPreviousInformation()) {
					initialValue = initialValue + 1;
				}
				if (null != T.getStaffStatutory()) {
					initialValue = initialValue + 1;
				}
				if (null != T.getTeachingSubjects()) {
					initialValue = initialValue + 1;
				}
				if (initialValue == 6) {
					T.setAcStatus("Complete");
				}

				rReponse.setProgressPercentage(calulateRegistrationProgress(initialValue, 6));

			}
			if (null != teachingStaffDetails.getStaffDocumentsDetails()) {
				StaffDocuments1 StaffDocuments = entityCreator
						.createDocumentDetails1(teachingStaffDetails.getStaffDocumentsDetails(), user);
				StaffDocuments.setTeachingObject((TeachingStaff) techingUser);
				insertDocumentDetails1(StaffDocuments, schema);

				initialValue = 1 + 1;
				TeachingStaff T = (TeachingStaff) techingUser;

				if (null != T.getAddress()) {
					initialValue = initialValue + 1;
				}

				if (null != T.getStaffPreviousInformation()) {
					initialValue = initialValue + 1;
				}
				if (null != T.getStaffStatutory()) {
					initialValue = initialValue + 1;
				}
				if (null != T.getTeachingSubjects()) {
					initialValue = initialValue + 1;
				}
				if (initialValue == 6) {
					T.setAcStatus("Complete");
				}

				rReponse.setProgressPercentage(calulateRegistrationProgress(initialValue, 6));

			}
			if (null != teachingStaffDetails.getStaffPreviousInformationDetails()) {

				StaffPreviousInformation1 staffPreviousInformation;
				try {
					staffPreviousInformation = entityCreator.createStaffPreviousInformationDetails1(
							teachingStaffDetails.getStaffPreviousInformationDetails(), user);
					staffPreviousInformation.setTeachingObject((TeachingStaff) techingUser);
					insertPreviousInformationDetails1(staffPreviousInformation, schema);
					initialValue = 1 + 1;
					TeachingStaff T = (TeachingStaff) techingUser;

					if (null != T.getAddress()) {
						initialValue = initialValue + 1;
					}

					if (null != T.getStaffDocuments()) {
						initialValue = initialValue + 1;
					}
					if (null != T.getStaffStatutory()) {
						initialValue = initialValue + 1;
					}
					if (null != T.getTeachingSubjects()) {
						initialValue = initialValue + 1;
					}
					if (initialValue == 6) {
						T.setAcStatus("Complete");
					}

					rReponse.setProgressPercentage(calulateRegistrationProgress(initialValue, 6));
				} catch (ParseException e) {
					throw exceptionHandler.constructAsmsException(messages.getString("DATE_INVALID_CODE"),
							messages.getString("DATE_INVALID"));
				}
			}

			if (null != teachingStaffDetails.getStaffStatutoryDetails()) {
				StaffStatutory1 staffStatutory = entityCreator
						.createStaffStatutoryDetails1(teachingStaffDetails.getStaffStatutoryDetails(), user);
				staffStatutory.setTeachingObject((TeachingStaff) techingUser);
				insertStatutoryDetails1(staffStatutory, schema);
				initialValue = 1 + 1;
				TeachingStaff T = (TeachingStaff) techingUser;

				if (null != T.getAddress()) {
					initialValue = initialValue + 1;
				}

				if (null != T.getStaffDocuments()) {
					initialValue = initialValue + 1;
				}
				if (null != T.getStaffPreviousInformation()) {
					initialValue = initialValue + 1;
				}
				if (null != T.getTeachingSubjects()) {
					initialValue = initialValue + 1;
				}

				if (initialValue == 6) {
					T.setAcStatus("Complete");
				}
				rReponse.setProgressPercentage(calulateRegistrationProgress(initialValue, 6));

			}
		} else {
			throw exceptionHandler.constructAsmsException(messages.getString("ROLE_INVALID_CODE"),
					messages.getString("ROLE_INVALID"));
		}
		return rReponse;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.asms.usermgmt.dao.UserMgmtDao#insertaddressDetails()
	 */
	private void insertaddressDetails(Address address, String schema) throws AsmsException {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();
			session.save(address);
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

					+ "insertaddressDetails()" + "   ", ex);

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asms.usermgmt.dao.UserMgmtDao#insertDocumentDetails(com.asms.usermgmt
	 * .entity.StaffDocuments)
	 */
	private void insertDocumentDetails(StaffDocuments staffDocuments, String schema) throws AsmsException {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();
			session.save(staffDocuments);
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

					+ "insertDocumentDetails()" + "   ", ex);

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asms.usermgmt.dao.UserMgmtDao#insertPreviousInformationDetails(com.
	 * asms.usermgmt.entity.StaffPreviousInformation)
	 */
	private void insertPreviousInformationDetails(StaffPreviousInformation staffPreviousInformation, String schema)
			throws AsmsException {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();
			session.save(staffPreviousInformation);
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

					+ "insertDocumentDetails()" + "   ", ex);

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.asms.usermgmt.dao.UserMgmtDao#insertStatutoryDetails(com.asms.
	 * usermgmt.entity.StaffStatutory)
	 */
	private void insertStatutoryDetails(StaffStatutory staffStatutory, String schema) throws AsmsException {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();
			session.save(staffStatutory);
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

					+ "insertStatutoryDetails()" + "   ", ex);

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
	// #Teaching imple

	// #Teaching imple

	/*
	 * Method: search ->This Method is used to search Userdetails Based On
	 * role,admissionNo firstName , lastName. input : role , admissionNo
	 * firstName , lastName. Returns : List Of UserDetails(List<UserDetails>)
	 * 
	 */
	@Override
	public List<UserDetails> search(String role, String id, String studentFirstName, String studentLastName,
			String mngmtFirstName, String mngmtLastName, String firstName, String lastName, String tenant)
			throws AsmsException {
		Session session = null;
		try {
			messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenant);
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				if (role.equalsIgnoreCase(Constants.role_student)) {
					String hql = "from Student S  where ";
					if (id != null) {
						hql = hql + " S.admissionNo='" + id + "'";
					}
					if (id == null && studentFirstName != null) {
						hql = hql + "  studentFirstName='" + studentFirstName + "'";

					}
					if (id != null && studentFirstName != null) {
						hql = hql + " and studentFirstName='" + studentFirstName + "'";
					}
					if (studentLastName != null) {
						hql = hql + " and studentLastName='" + studentLastName + "'";
					}
					@SuppressWarnings("unchecked")
					List<User> users = session.createQuery(hql).list();

					return entityCreator.createStudentDetails(users);

				} else if (role.equalsIgnoreCase(Constants.role_management)) {
					String hql = "from Management  where ";
					if (mngmtFirstName != null && mngmtLastName == null) {
						hql = hql + "mngmtFirstName='" + mngmtFirstName + "'";
					}
					if (mngmtFirstName == null && mngmtLastName != null) {
						hql = hql + "mngmtLastName='" + mngmtLastName + "'";
					}
					if (mngmtLastName != null && mngmtFirstName != null) {
						hql = hql + "mngmtFirstName='" + mngmtFirstName + "' and  mngmtLastName='" + mngmtLastName
								+ "'";
					}

					@SuppressWarnings("unchecked")
					List<UserDetails> userDetails = session.createQuery(hql).list();
					return userDetails;

				} else if (role.equalsIgnoreCase(Constants.role_teaching_staff)) {
					String hql = "from TeachingStaff  where ";
					if (firstName != null && lastName == null) {
						hql = hql + "firstName='" + firstName + "'";
					}
					if (firstName == null && lastName != null) {
						hql = hql + "lastName='" + lastName + "'";
					}
					if (lastName != null && firstName != null) {
						hql = hql + "firstName='" + firstName + "' and lastName='" + lastName + "'";
					}
					@SuppressWarnings("unchecked")
					List<UserDetails> userDetails = session.createQuery(hql).list();
					return userDetails;
				} else if (role.equalsIgnoreCase(Constants.role_teaching_staff)) {
					String hql = "from TeachingStaff  where ";
					if (firstName != null && lastName == null) {
						hql = hql + "firstName='" + firstName + "'";
					}
					if (firstName == null && lastName != null) {
						hql = hql + "lastName='" + lastName + "'";
					}
					if (lastName != null && firstName != null) {
						hql = hql + "firstName='" + firstName + "' and lastName='" + lastName + "'";
					}
					@SuppressWarnings("unchecked")
					List<User> users = session.createQuery(hql).list();
					return entityCreator.createTeachingStaffDetails(users);
				}

				else if (role.equalsIgnoreCase(Constants.role_non_teaching_staff)) {

					String hql = "from NonTeachingStaff  where ";
					if (firstName != null && lastName == null) {
						hql = hql + "firstName='" + firstName + "'";
					}
					if (firstName == null && lastName != null) {
						hql = hql + "lastName='" + lastName + "'";
					}
					if (lastName != null && firstName != null) {
						hql = hql + "firstName='" + firstName + "' and lastName='" + lastName + "'";
					}
					@SuppressWarnings("unchecked")
					List<User> users = session.createQuery(hql).list();
					return entityCreator.createNonTeachingStaffDetails(users);
				} else {
					return new ArrayList<UserDetails>();
				}

			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}

		} catch (Exception ex) {
			if (session.isOpen()) {
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
			if (session.isOpen()) {
				session.close();
			}
		}

	}

	/*
	 * Method: searchForPrivileges ->This Method is used to search Userds Based
	 * On role,subrole Returns : List Of UserDetails(List<UserDetails>)
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserDetails> searchForPrivileges(String role, String subRole, String id, String tenant)
			throws AsmsException {
		Session session = null;
		try {
			messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenant);
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				String hql = "from User S  where S.roleObject.roleName = '" + role + "'";
				if (null != subRole && !subRole.isEmpty()) {
					hql = hql + " and S.subRoleObject.subRoleName = '" + subRole + "'";

				}
				if (Constants.role_student.equalsIgnoreCase(role)) {
					hql = hql + " and S.admissionNo='" + id + "'";
				}

				List<User> users = new ArrayList<User>();

				users = session.createQuery(hql).list();

				return entityCreator.createUserDetails(users);

			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}
		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "searchForPrivileges()" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public List<StudentType> getAll() throws AsmsException {
		Session session = null;
		try {
			session = sessionFactory.withOptions().tenantIdentifier(dbProperties.getProperty("default_schema"))
					.openSession();
			String hql = "from StudentType";

			@SuppressWarnings("unchecked")
			List<StudentType> studentTypes = session.createQuery(hql).list();
			session.close();
			return studentTypes;

		} catch (Exception ex) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getAll()" + "   ", ex);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asms.usermgmt.dao.UserMgmtDao#insertaddressDetails1(com.asms.usermgmt
	 * .entity.teachingStaff.Address1)
	 */
	private void insertaddressDetails1(Address1 address, String schema) throws AsmsException {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();
			session.save(address);
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

					+ "insertaddressDetails1TeachingStaff()" + "   ", ex);

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.asms.usermgmt.dao.UserMgmtDao#insertDocumentDetails1(com.asms.
	 * usermgmt.entity.teachingStaff.StaffDocuments1)
	 */
	private void insertDocumentDetails1(StaffDocuments1 staffDocuments, String schema) throws AsmsException {

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();
			session.save(staffDocuments);
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

					+ "insertDocumentDetails1TeachingStaff()" + "   ", ex);

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asms.usermgmt.dao.UserMgmtDao#insertPreviousInformationDetails1(com.
	 * asms.usermgmt.entity.teachingStaff.StaffPreviousInformation1)
	 */
	private void insertPreviousInformationDetails1(StaffPreviousInformation1 staffPreviousInformation, String schema)
			throws AsmsException {
		//
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();
			session.save(staffPreviousInformation);
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

					+ "insertDocumentDetails1TeachingStaff()" + "   ", ex);

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.asms.usermgmt.dao.UserMgmtDao#insertStatutoryDetails1(com.asms.
	 * usermgmt.entity.teachingStaff.StaffStatutory1)
	 */
	private void insertStatutoryDetails1(StaffStatutory1 staffStatutory, String schema) throws AsmsException {

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();
			session.save(staffStatutory);
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

					+ "insertStatutoryDetails1TeachingStaff()" + "   ", ex);

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asms.usermgmt.dao.UserMgmtDao#updateUser(com.asms.usermgmt.request.
	 * UserDetails, com.asms.usermgmt.entity.User)
	 */
	@Override
	public void updateUser(UserDetails userDetails, User user, String tenant) throws AsmsException {

		Session session = null;

		Transaction tx = null;
		try {
			String schema = multitenancyDao.getSchema(tenant);
			if (null == schema) {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));

			}

			ResourceBundle messages = AsmsHelper.getMessageFromBundle();

			User userObject = getUserDetailsById(userDetails.getUserId(), schema);
			if (null == userObject || userObject.toString().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("UPDATE_DETAILS_USER_NOT_FOUND_CODE"),
						messages.getString("UPDATE_DETAILS_USER_NOT_FOUND_MSG"));
			}

			else if (null != userDetails && userDetails.getRole().equalsIgnoreCase(Constants.role_student)) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				tx = session.beginTransaction();
				Student student = (Student) session.load(Student.class, userObject.getSerialNo());

				if (null != userDetails.getStudentDetails()) {
					if (null != userDetails.getEmail()) {
						student.setEmail(userDetails.getEmail());
					}
					if (null != userDetails.getUserPassword()) {
						student.setUserPassword(userDetails.getUserPassword());
					}
					updateStudentDetails(student, userDetails.getStudentDetails());
					updateStudentDetails(student, userDetails.getStudentDetails());
				}

				if (null != userDetails.getStudentDetails().getParentDetails() && null != student.getParentObject()) {
					updateParentDetails(student.getParentObject(), userDetails.getStudentDetails().getParentDetails());
				}

				if (null != userDetails.getStudentDetails().getStudentDocumentDetails()
						&& null != student.getStudentDocuments()) {
					updateStudentDocumentDetails(student.getStudentDocuments(),
							userDetails.getStudentDetails().getStudentDocumentDetails());
				}

				if (null != userDetails.getStudentDetails().getStudentPreviousInfo()
						&& null != student.getStudentPreviousInfo()) {
					updateStudentPreviousInfo(student.getStudentPreviousInfo(),
							userDetails.getStudentDetails().getStudentPreviousInfo());
				}

				if (null != userDetails.getStudentDetails().getStudentAddress()
						&& null != student.getStudentAddress()) {
					updateStudentAddress(student.getStudentAddress(),
							userDetails.getStudentDetails().getStudentAddress());
				}

				session.update(student);
				tx.commit();
				session.close();

			}

			else if (null != userDetails && userDetails.getRole().equalsIgnoreCase(Constants.role_non_teaching_staff)) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				tx = session.beginTransaction();
				NonTeachingStaff nonTeachingStaff = (NonTeachingStaff) session.load(NonTeachingStaff.class,
						userObject.getSerialNo());

				if (null != userDetails.getNonTeachingStaffDetails()) {
					if (null != userDetails.getEmail()) {
						nonTeachingStaff.setEmail(userDetails.getEmail());
					}
					if (null != userDetails.getUserPassword()) {
						nonTeachingStaff.setUserPassword(userDetails.getUserPassword());
					}

					updateNonTeachingStaffDetails(nonTeachingStaff, userDetails.getNonTeachingStaffDetails());
				}

				if (null != userDetails.getNonTeachingStaffDetails().getAddressDetails()
						&& null != nonTeachingStaff.getAddress()) {
					updateNonTeachingStaffAddressDetails(nonTeachingStaff.getAddress(),
							userDetails.getNonTeachingStaffDetails().getAddressDetails());
				}

				if (null != userDetails.getNonTeachingStaffDetails().getStaffDocumentsDetails()
						&& null != nonTeachingStaff.getStaffDocuments()) {
					updateNonTeachingStaffDocumentsDetails(nonTeachingStaff.getStaffDocuments(),
							userDetails.getNonTeachingStaffDetails().getStaffDocumentsDetails());
				}

				if (null != userDetails.getNonTeachingStaffDetails().getStaffStatutoryDetails()
						&& null != nonTeachingStaff.getStaffStatutory()) {
					updateNonTeachingStaffStatutoryDetails(nonTeachingStaff.getStaffStatutory(),
							userDetails.getNonTeachingStaffDetails().getStaffStatutoryDetails());
				}

				if (null != userDetails.getNonTeachingStaffDetails().getStaffPreviousInformationDetails()
						&& null != nonTeachingStaff.getStaffPreviousInformation()) {
					updateNonTeachingStaffPreviousInformationDetails(nonTeachingStaff.getStaffPreviousInformation(),
							userDetails.getNonTeachingStaffDetails().getStaffPreviousInformationDetails());
				}

				session.update(nonTeachingStaff);
				tx.commit();
				session.close();
			}

			else if (null != userDetails && userDetails.getRole().equalsIgnoreCase(Constants.role_teaching_staff)) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				tx = session.beginTransaction();
				TeachingStaff teachingStaff = (TeachingStaff) session.load(TeachingStaff.class,
						userObject.getSerialNo());

				if (null != userDetails.getTeachingStaffDetails()) {
					if (null != userDetails.getEmail()) {
						teachingStaff.setEmail(userDetails.getEmail());
					}
					if (null != userDetails.getUserPassword()) {
						teachingStaff.setUserPassword(userDetails.getUserPassword());
					}

					updateTeachingStaffDetails(teachingStaff, userDetails.getTeachingStaffDetails());
				}
				teachingStaff.setAcStatus("InComplete");

				if (null != userDetails.getTeachingStaffDetails().getAddressDetails()
						&& null != teachingStaff.getAddress()) {

					updateTeachingStaffAddressDetails(teachingStaff.getAddress(),
							userDetails.getTeachingStaffDetails().getAddressDetails());

				}

				if (null != userDetails.getTeachingStaffDetails().getStaffDocumentsDetails1()
						&& null != teachingStaff.getStaffDocuments()) {

					updateTeachingStaffDocuments(teachingStaff.getStaffDocuments(),
							userDetails.getTeachingStaffDetails().getStaffDocumentsDetails1());
				}

				if (null != userDetails.getTeachingStaffDetails().getStaffPreviousInformationDetails1()
						&& null != teachingStaff.getStaffPreviousInformation()) {

					updateTeachingStaffPreviousInfo(teachingStaff.getStaffPreviousInformation(),
							userDetails.getTeachingStaffDetails().getStaffPreviousInformationDetails1());
				}

				if (null != userDetails.getTeachingStaffDetails().getStatutoryDetails1()
						&& null != teachingStaff.getStaffStatutory()) {

					updateTeachingStaffStatutoryDetails(teachingStaff.getStaffStatutory(),
							userDetails.getTeachingStaffDetails().getStatutoryDetails1());
				}

				session.update(teachingStaff);
				tx.commit();
				session.close();
			}

			else if (null != userDetails && userDetails.getRole().equalsIgnoreCase(Constants.role_management)) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				tx = session.beginTransaction();
				Management management = (Management) session.load(Management.class, userObject.getSerialNo());
				if (null != userDetails.getEmail()) {
					management.setEmail(userDetails.getEmail());
				}
				if (null != userDetails.getUserPassword()) {
					management.setUserPassword(userDetails.getUserPassword());
				}

				if (null != userDetails.getManagementDetails().getTrustId()
						|| !userDetails.getManagementDetails().getTrustId().isEmpty()) {
					management.setTrustId(userDetails.getManagementDetails().getTrustId());
				}
				if (null != userDetails.getManagementDetails().getMngmtFirstName()
						|| !userDetails.getManagementDetails().getMngmtFirstName().isEmpty()) {
					management.setMngmtFirstName(userDetails.getManagementDetails().getMngmtFirstName());
				}
				if (null != userDetails.getManagementDetails().getMngmtMiddleName()
						|| !userDetails.getManagementDetails().getMngmtMiddleName().isEmpty()) {
					management.setMngmtMiddleName(userDetails.getManagementDetails().getMngmtMiddleName());
				}
				if (null != userDetails.getManagementDetails().getMngmtLastName()
						|| !userDetails.getManagementDetails().getMngmtLastName().isEmpty()) {
					management.setMngmtLastName(userDetails.getManagementDetails().getMngmtLastName());
				}
				if (null != userDetails.getManagementDetails().getMngmtDesignation()
						|| !userDetails.getManagementDetails().getMngmtDesignation().isEmpty()) {
					management.setMngmtDesignation(userDetails.getManagementDetails().getMngmtDesignation());
				}
				if (null != userDetails.getManagementDetails().getMngmtContactNo()
						|| !userDetails.getManagementDetails().getMngmtContactNo().isEmpty()) {
					management.setMngmtContactNo(userDetails.getManagementDetails().getMngmtContactNo());
				}

				session.update(management);
				tx.commit();
				session.close();

			}

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "updateUser()" + "   ", e);
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
	 * Method: getUserDetails ->This Method is used to get UserDetails Based On
	 * UserId . input : UserId. Returns : UserDetails(UserDetails)
	 * 
	 */
	@Override
	public UserDetails getUserDetails(String userId, String tenant) throws AsmsException {
		Session session = null;
		try {
			messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenant);
			if (null != schema) {
				String hql = "from User U where U.userId=?";
				User user = (User) session.createQuery(hql).setParameter(0, userId).uniqueResult();
				session.close();
				return entityCreator.createStudentDetail(user);
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getUserDetails()" + "   ", e);
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
	 * Method: updateStudentDetails ->This Method is used to update
	 * StudentDetails. input : . Returns : no return Type
	 * 
	 */
	private void updateStudentDetails(Student student, StudentDetails studentDetails) {

		if (null != studentDetails.getStudentFirstName() || !studentDetails.getStudentFirstName().isEmpty()) {
			student.setStudentFirstName(studentDetails.getStudentFirstName());
		}
		if (null != studentDetails.getStudentMiddleName() || !studentDetails.getStudentMiddleName().isEmpty()) {
			student.setStudentMiddleName(studentDetails.getStudentMiddleName());
		}
		if (null != studentDetails.getStudentLastName() || !studentDetails.getStudentLastName().isEmpty()) {
			student.setStudentLastName(studentDetails.getStudentLastName());
		}
		if (null != studentDetails.getStudentPhoto() || !studentDetails.getStudentPhoto().isEmpty()) {
			student.setStudentPhoto(studentDetails.getStudentPhoto());
		}

		if (studentDetails.getEmergencyContactNo() > 0) {
			student.setEmergencyContactNo(studentDetails.getEmergencyContactNo());
		}
		if (null != studentDetails.getStudentType() || !studentDetails.getStudentType().isEmpty()) {
			student.setStudentType(studentDetails.getStudentType());
		}
		if (null != studentDetails.getStudentGender() || !studentDetails.getStudentType().isEmpty()) {
			student.setStudentGender(studentDetails.getStudentGender());
		}
		if (null != studentDetails.getStudentIdentificationMarks()
				|| !studentDetails.getStudentIdentificationMarks().isEmpty()) {
			student.setStudentIdentificationMarks(studentDetails.getStudentIdentificationMarks());
		}
		if (null != studentDetails.getStudentBirthplace() || !studentDetails.getStudentBirthplace().isEmpty()) {
			student.setStudentBirthplace(studentDetails.getStudentBirthplace());
		}
		if (studentDetails.getEmergencyContactNo() > 0) {
			student.setEmergencyContactNo(studentDetails.getEmergencyContactNo());
		}
		if (null != studentDetails.getStudentNationality() || !studentDetails.getStudentNationality().isEmpty()) {
			student.setStudentNationality(studentDetails.getStudentNationality());
		}
		if (null != studentDetails.getStudentType() || !studentDetails.getStudentType().isEmpty()) {
			student.setStudentType(studentDetails.getStudentType());
		}
		if (null != studentDetails.getStudentReligion() || !studentDetails.getStudentReligion().isEmpty()) {
			student.setStudentReligion(studentDetails.getStudentReligion());
		}
		if (null != studentDetails.getStudentGender() || !studentDetails.getStudentGender().isEmpty()) {
			student.setStudentGender(studentDetails.getStudentGender());
		}
		if (null != studentDetails.getStudentCasteCategory() || !studentDetails.getStudentCasteCategory().isEmpty()) {
			student.setStudentCasteCategory(studentDetails.getStudentCasteCategory());
		}
		if (null != studentDetails.getStudentIdentificationMarks()
				|| !studentDetails.getStudentIdentificationMarks().isEmpty()) {
			student.setStudentIdentificationMarks(studentDetails.getStudentIdentificationMarks());
		}
		if (null != studentDetails.getStudentSubCaste() || !studentDetails.getStudentSubCaste().isEmpty()) {
			student.setStudentSubCaste(studentDetails.getStudentSubCaste());
		}

		if (null != studentDetails.getStudentMotherTongue() || !studentDetails.getStudentMotherTongue().isEmpty()) {
			student.setStudentMotherTongue(studentDetails.getStudentMotherTongue());
		}
		if (null != studentDetails.getStatus() || !studentDetails.getStatus().isEmpty()) {
			student.setStatus(studentDetails.getStatus());
		}

	}

	/*
	 * Method: updateTeachingStaffDetails ->This Method is used to update
	 * TeachingStaffDetails. input : TeachingStaff, TeachingStaffDetails.
	 * Returns : No ReturnType
	 * 
	 */
	private void updateTeachingStaffDetails(TeachingStaff teachingStaff, TeachingStaffDetails teachingStaffDetails) {

		if (null != teachingStaffDetails.getDesignation() || !teachingStaffDetails.getDesignation().isEmpty()) {
			teachingStaff.setDesignation(teachingStaffDetails.getDesignation());
		}
		if (null != teachingStaffDetails.getFirstName() || !teachingStaffDetails.getFirstName().isEmpty()) {
			teachingStaff.setFirstName(teachingStaffDetails.getFirstName());
		}
		if (null != teachingStaffDetails.getMiddleName() || !teachingStaffDetails.getMiddleName().isEmpty()) {
			teachingStaff.setMiddleName(teachingStaffDetails.getMiddleName());
		}
		if (null != teachingStaffDetails.getLastName() || !teachingStaffDetails.getLastName().isEmpty()) {
			teachingStaff.setLastName(teachingStaffDetails.getLastName());
		}

		if (null != teachingStaffDetails.getGender() || !teachingStaffDetails.getGender().isEmpty()) {
			teachingStaff.setGender(teachingStaffDetails.getGender());
		}
		if (0 < teachingStaffDetails.getContactNo()) {
			teachingStaff.setContactNo(teachingStaffDetails.getContactNo());
		}

		if (null != teachingStaffDetails.getQualification() || !teachingStaffDetails.getQualification().isEmpty()) {
			teachingStaff.setQualification(teachingStaffDetails.getQualification());
		}
		if (null != teachingStaffDetails.getReligion() || !teachingStaffDetails.getReligion().isEmpty()) {
			teachingStaff.setReligion(teachingStaffDetails.getReligion());
		}
		if (null != teachingStaffDetails.getPhoto() || !teachingStaffDetails.getPhoto().isEmpty()) {
			teachingStaff.setPhoto(teachingStaffDetails.getPhoto());
		}
		if (null != teachingStaffDetails.getMaritalStatus() || !teachingStaffDetails.getMaritalStatus().isEmpty()) {
			teachingStaff.setMaritalStatus(teachingStaffDetails.getMaritalStatus());
		}
		if (null != teachingStaffDetails.getSpouseName() || !teachingStaffDetails.getSpouseName().isEmpty()) {
			teachingStaff.setSpouseName(teachingStaffDetails.getSpouseName());
		}
		if (0 < teachingStaffDetails.getSpouseContactNo()) {
			teachingStaff.setSpouseContactNo(teachingStaffDetails.getSpouseContactNo());
		}
		if (null != teachingStaffDetails.getCasteCategory() || !teachingStaffDetails.getCasteCategory().isEmpty()) {
			teachingStaff.setCasteCategory(teachingStaffDetails.getCasteCategory());
		}
		if (null != teachingStaffDetails.getAcStatus() || !teachingStaffDetails.getAcStatus().isEmpty()) {
			teachingStaff.setAcStatus(teachingStaffDetails.getAcStatus());
		}

	}

	/*
	 * getUserById : This method retrieves user object from database using
	 * userid
	 * 
	 * parameters: String userid
	 * 
	 * return User Object
	 * 
	 * 
	 */

	private void updateParentDetails(Parent parent, ParentDetails details) {

		/**
		 * @{author} karishma.k 01-Sep-2017
		 */

		if (null != details.getfFirstName()) {
			parent.setfFirstName(details.getfFirstName());
		}
		if (null != details.getfMiddleName()) {
			parent.setfMiddleName(details.getfMiddleName());
		}
		if (null != details.getfLastName()) {
			parent.setfLastName(details.getfLastName());
		}
		if (null != details.getfEmail()) {
			parent.setfEmail(details.getfEmail());
		}
		if (details.getfContactNumber() > 0) {
			parent.setfContactNumber(details.getfContactNumber());
		}
		if (details.getfIncome() > 0) {
			parent.setfIncome(details.getfIncome());
		}
		if (null != details.getfOccupation()) {
			parent.setfOccupation(details.getfOccupation());
		}
		if (null != details.getfQualification()) {
			parent.setfQualification(details.getfQualification());
		}
		if (null != details.getmFirstName()) {
			parent.setmFirstName(details.getmFirstName());
		}
		if (null != details.getmMiddleName()) {
			parent.setmOccupation(details.getmQualification());
		}
		if (null != details.getmLastName()) {
			parent.setmLastName(details.getmLastName());
		}
		if (null != details.getmEmail()) {
			parent.setmEmail(details.getmEmail());
		}
		if (details.getmContactNumber() > 0) {
			parent.setmContactNumber(details.getmContactNumber());
		}
		if (null != details.getmQualification()) {
			parent.setmQualification(details.getmQualification());
		}
		if (null != details.getmOccupation()) {
			parent.setmOccupation(details.getmOccupation());
		}
		if (details.getmIncome() > 0) {
			parent.setmIncome(details.getmIncome());
		}
	}

	/*
	 * getUserById : This method retrieves user object from database using
	 * userid
	 * 
	 * parameters: String userid
	 * 
	 * return User Object
	 * 
	 * 
	 */
	/*
	 * private void updatesiblingDetails(List<Sibling> sibling, SiblingDetails
	 * details) { if (null != details.getName()) { ((Sibling)
	 * sibling).setName(details.getName()); } if (null != details.getGender()) {
	 * ((Sibling) sibling).setGender(details.getGender()); } if
	 * (details.getAge() > 0) { ((Sibling) sibling).setAge(details.getAge()); }
	 * if (null != details.getSchool()) { ((Sibling)
	 * sibling).setSchool(details.getSchool()); } }
	 */

	/*
	 * getUserById : This method retrieves user object from database using
	 * userid
	 * 
	 * parameters: String userid
	 * 
	 * return User Object
	 * 
	 * 
	 */

	private void updateStudentDocumentDetails(StudentDocuments studentDocuments, StudentDocumentDetails details) {
		if (null != details.getAadhaarNo()) {
			studentDocuments.setAadhaarNo(details.getAadhaarNo());
		}
		if (null != details.getAadhaarCard()) {
			studentDocuments.setAadhaarCard(details.getAadhaarCard());
		}
		if (null != details.getBirthCirtificate()) {
			studentDocuments.setBirthCertificate(details.getBirthCirtificate());
		}
		if (null != details.getOtherCertificate()) {
			studentDocuments.setOtherCertificate(details.getOtherCertificate());
		}
		if (null != details.getRemarks()) {
			studentDocuments.setRemarks(details.getRemarks());
		}
	}

	/*
	 * getUserById : This method retrieves user object from database using
	 * userid
	 * 
	 * parameters: String userid
	 * 
	 * return User Object
	 * 
	 * 
	 */

	private void updateStudentPreviousInfo(StudentPreviousInfo studentPreviousInfo, StudentPreviousInfo detail) {
		if (detail.getNoOfYears() > 0) {
			studentPreviousInfo.setNoOfYears(detail.getNoOfYears());
		}
		if (detail.getObtainedMarks() > 0) {
			studentPreviousInfo.setObtainedMarks(detail.getObtainedMarks());
		}
		if (null != detail.getPreviousClass()) {
			studentPreviousInfo.setPreviousClass(detail.getPreviousClass());
		}
		if (null != detail.getSchoolName()) {
			studentPreviousInfo.setSchoolName(detail.getSchoolName());
		}
		if (detail.getTotalMarks() > 0) {
			studentPreviousInfo.setTotalMarks(detail.getTotalMarks());
		}
	}

	/*
	 * Method: updateTeachingStaffAddressDetails ->This Method is used to update
	 * TeachingStaffAddressDetails. input : Address1, AddressDetails. Returns :
	 * no return Type
	 * 
	 */
	private void updateTeachingStaffAddressDetails(Address1 address1, AddressDetails addressDetails) {

		if (null != addressDetails.getAddressLine1() || !addressDetails.getAddressLine1().isEmpty()) {
			address1.setAddressLine1(addressDetails.getAddressLine1());
		}
		if (null != addressDetails.getAddressLine2() || !addressDetails.getAddressLine2().isEmpty()) {
			address1.setAddressLine2(addressDetails.getAddressLine2());
		}
		if (null != addressDetails.getCountry() || !addressDetails.getCountry().isEmpty()) {
			address1.setCountry(addressDetails.getCountry());
		}
		if (null != addressDetails.getState() || !addressDetails.getState().isEmpty()) {
			address1.setCountry(addressDetails.getState());
		}
		if (null != addressDetails.getDistrict() || !addressDetails.getDistrict().isEmpty()) {
			address1.setDistrict(addressDetails.getDistrict());
		}
		if (null != addressDetails.getSubDivision() || !addressDetails.getSubDivision().isEmpty()) {
			address1.setSubDivision(addressDetails.getSubDivision());
		}
		if (null != addressDetails.getTehsil() || !addressDetails.getTehsil().isEmpty()) {
			address1.setTehsil(addressDetails.getTehsil());
		}
		if (null != addressDetails.getVillage() || !addressDetails.getVillage().isEmpty()) {
			address1.setVillage(addressDetails.getVillage());
		}
		if (0 < addressDetails.getPincode()) {
			address1.setPincode(addressDetails.getPincode());
		}

	}

	/*
	 * Method: updateTeachingStaffDocumentsDetails ->This Method is used to
	 * update TeachingStaffDocumentsDetails. input : StaffDocuments1,
	 * StaffDocumentsDetails1. Returns : no return Type
	 * 
	 */
	private void updateTeachingStaffDocuments(StaffDocuments1 staffDocuments1,
			StaffDocumentsDetails1 staffDocumentsDetails) {
		if (null != staffDocumentsDetails.getNt_staff_10th_certificate()
				|| !staffDocumentsDetails.getNt_staff_10th_certificate().isEmpty()) {
			staffDocuments1.setTen10thCertificate(staffDocumentsDetails.getNt_staff_10th_certificate());
		}
		if (null != staffDocumentsDetails.getNt_staff_12th_certificate()
				|| !staffDocumentsDetails.getNt_staff_12th_certificate().isEmpty()) {
			staffDocuments1.setTwelve12thCertificate(staffDocumentsDetails.getNt_staff_12th_certificate());
		}
		if (null != staffDocumentsDetails.getNt_staff_degree_certificate()
				|| !staffDocumentsDetails.getNt_staff_degree_certificate().isEmpty()) {
			staffDocuments1.setDegreeCertificate(staffDocumentsDetails.getNt_staff_degree_certificate());
		}
		if (null != staffDocumentsDetails.getNt_staff_pg_degree_certificate()
				|| !staffDocumentsDetails.getNt_staff_pg_degree_certificate().isEmpty()) {
			staffDocuments1.setPgDegreeCertificate(staffDocumentsDetails.getNt_staff_pg_degree_certificate());
		}
		if (null != staffDocumentsDetails.getNt_staff_medical_fitness_certificate()
				|| !staffDocumentsDetails.getNt_staff_medical_fitness_certificate().isEmpty()) {
			staffDocuments1
					.setMedicalFitnessCertificate(staffDocumentsDetails.getNt_staff_medical_fitness_certificate());
		}

	}

	/*
	 * Method: updateTeachingStaffPreviousInfoDetails ->This Method is used to
	 * update updateTeachingStaffPreviousInfoDetails. input :
	 * StaffPreviousInformation1, StaffPreviousInformationDetails1. Returns : no
	 * return Type
	 * 
	 */
	private void updateTeachingStaffPreviousInfo(StaffPreviousInformation1 previousInformation1,
			StaffPreviousInformationDetails1 pIDetails1) {

		pIDetails1.setExperienceFlag(false);

		if (null != pIDetails1.getLastWorkedOrganisation() || !pIDetails1.getLastWorkedOrganisation().isEmpty()) {
			previousInformation1.setLastWorkedOrganisation(pIDetails1.getLastWorkedOrganisation());
		}
		if (null != pIDetails1.getExperienceCertificate() || !pIDetails1.getExperienceCertificate().isEmpty()) {
			previousInformation1.setExperienceCertificate(pIDetails1.getExperienceCertificate());
		}
		if (null != pIDetails1.getLastDrawnPayslip() || !pIDetails1.getLastDrawnPayslip().isEmpty()) {
			previousInformation1.setLastDrawnPayslip(pIDetails1.getLastDrawnPayslip());
		}
		if (null != pIDetails1.getResume() || !pIDetails1.getResume().isEmpty()) {
			previousInformation1.setResume(pIDetails1.getResume());
		}

	}

	/*
	 * Method: updateTeachingStaffStatutoryDetails ->This Method is used to
	 * update TeachingStaffStatutoryDetails. input : StaffStatutory1,
	 * StaffStatutoryDetails1. Returns : no return Type
	 * 
	 */
	private void updateTeachingStaffStatutoryDetails(StaffStatutory1 staffStatutory1,
			StaffStatutoryDetails1 statutoryDetails1) {
		if (null != statutoryDetails1.getBankName() || !statutoryDetails1.getBankName().isEmpty()) {
			staffStatutory1.setBankName(statutoryDetails1.getBankName());
		}
		if (null != statutoryDetails1.getBankAccountNo() || !statutoryDetails1.getBankAccountNo().isEmpty()) {
			staffStatutory1.setBankAccountNo(statutoryDetails1.getBankAccountNo());
		}
		if (null != statutoryDetails1.getBankIfscCode() || !statutoryDetails1.getBankIfscCode().isEmpty()) {
			staffStatutory1.setBankIfscCode(statutoryDetails1.getBankIfscCode());
		}
		if (null != statutoryDetails1.getPanNo() || !statutoryDetails1.getPanNo().isEmpty()) {
			staffStatutory1.setPanNo(statutoryDetails1.getPanNo());
		}
		if (null != statutoryDetails1.getPanCard() || !statutoryDetails1.getPanCard().isEmpty()) {
			staffStatutory1.setPanCard(statutoryDetails1.getPanCard());
		}
		if (null != statutoryDetails1.getAadhaarNo() || !statutoryDetails1.getAadhaarNo().isEmpty()) {
			staffStatutory1.setAadhaarNo(statutoryDetails1.getAadhaarNo());
		}
		if (null != statutoryDetails1.getAadhaarCard() || !statutoryDetails1.getAadhaarCard().isEmpty()) {
			staffStatutory1.setAadhaarCard(statutoryDetails1.getAadhaarCard());
		}
		if (null != statutoryDetails1.getPfNo() || !statutoryDetails1.getPfNo().isEmpty()) {
			staffStatutory1.setPfNo(statutoryDetails1.getPfNo());
		}

	}

	private void updateStudentAddress(StudentAddress studentAddress, StudentAddress detail) {
		if (null != detail.getpAddressLine1()) {
			studentAddress.setpAddressLine1(detail.getpAddressLine1());
		}
		if (null != detail.getpAddressLine2()) {
			studentAddress.setpAddressLine2(detail.getpAddressLine2());
		}
		if (null != detail.getpCountry()) {
			studentAddress.setpCountry(detail.getpCountry());
		}
		if (null != detail.getpDistrict()) {
			studentAddress.setpDistrict(detail.getpDistrict());
		}
		if (null != detail.getpState()) {
			studentAddress.setpState(detail.getpState());
		}
		if (null != detail.getpSubDivision()) {
			studentAddress.setpSubDivision(detail.getpSubDivision());
		}
		if (null != detail.getpTehsil()) {
			studentAddress.setpTehsil(detail.getpTehsil());
		}
		if (null != detail.getpVillage()) {
			studentAddress.setpVillage(detail.getpVillage());
		}
		if (null != detail.getpPincode()) {
			studentAddress.setcPincode(detail.getcPincode());
		}
		if (null != detail.getpLocation()) {
			studentAddress.setpLocation(detail.getpLocation());
		}
		if (null != detail.getcAddressLine1()) {
			studentAddress.setcAddressLine1(detail.getcAddressLine1());
		}
		if (null != detail.getcAddressLine2()) {
			studentAddress.setcAddressLine2(detail.getcAddressLine2());
		}
		if (null != detail.getcCountry()) {
			studentAddress.setcCountry(detail.getcCountry());
		}
		if (null != detail.getcDistrict()) {
			studentAddress.setcDistrict(detail.getcDistrict());
		}
		if (null != detail.getcState()) {
			studentAddress.setcState(detail.getcState());
		}
		if (null != detail.getcSubDivision()) {
			studentAddress.setcSubDivision(detail.getcSubDivision());
		}
		if (null != detail.getcTehsil()) {
			studentAddress.setcTehsil(detail.getcTehsil());
		}
		if (null != detail.getcVillage()) {
			studentAddress.setcVillage(detail.getcVillage());
		}
		if (null != detail.getcPincode()) {
			studentAddress.setcPincode(detail.getcPincode());
		}
		if (null != detail.getcLocation()) {
			studentAddress.setcLocation(detail.getcLocation());
		}
	}

	/*
	 * getUserById : This method retrieves user object from database using
	 * userid
	 * 
	 * parameters: String userid
	 * 
	 * return User Object
	 * 
	 * 
	 */

	private void updateNonTeachingStaffDetails(NonTeachingStaff staff, NonTeachingStaffDetails details) {
		/**
		 * @{author} karishma.k 04-Sep-2017
		 */

		if (null != details.getDesignation()) {
			staff.setDesignation(details.getDesignation());
		}
		if (null != details.getFirstName()) {
			staff.setFirstName(details.getFirstName());
		}
		if (null != details.getMiddleName()) {
			staff.setMiddleName(details.getMiddleName());
		}
		if (null != details.getLastName()) {
			staff.setLastName(details.getLastName());
		}
		if (details.getContactNo() > 0) {
			staff.setContactNo(details.getContactNo());
		}

		if (null != details.getMaritalStatus()) {
			staff.setMaritalStatus(details.getMaritalStatus());
		}
		if (null != details.getCasteCategory()) {
			staff.setCasteCategory(details.getCasteCategory());
		}
		if (null != details.getPhoto()) {
			staff.setPhoto(details.getPhoto());
		}
		if (null != details.getQualification()) {
			staff.setQualification(details.getQualification());
		}
		if (null != details.getReligion()) {
			staff.setReligion(details.getReligion());
		}
		if (null != details.getGender()) {
			staff.setGender(details.getGender());
		}

		if (null != details.getSpouseName()) {
			staff.setSpouseName(details.getSpouseName());
		}
		if (details.getSpouseContactNo() > 0) {
			staff.setSpouseContactNo(details.getSpouseContactNo());
		}
	}

	/*
	 * getUserById : This method retrieves user object from database using
	 * userid
	 * 
	 * parameters: String userid
	 * 
	 * return User Object
	 * 
	 * 
	 */

	private void updateNonTeachingStaffAddressDetails(Address address, AddressDetails addressDetails) {
		if (null != addressDetails.getAddressLine1()) {
			address.setAddressLine1(addressDetails.getAddressLine1());
		}
		if (null != addressDetails.getAddressLine2()) {
			address.setAddressLine2(addressDetails.getAddressLine2());
		}
		if (null != addressDetails.getCountry()) {
			address.setCountry(addressDetails.getCountry());
		}
		if (null != addressDetails.getDistrict()) {
			address.setDistrict(addressDetails.getDistrict());
		}
		if (null != addressDetails.getState()) {
			address.setState(addressDetails.getState());
		}
		if (null != addressDetails.getTehsil()) {
			address.setTehsil(addressDetails.getTehsil());
		}
		if (null != addressDetails.getSubDivision()) {
			address.setSubDivision(addressDetails.getSubDivision());
		}
		if (null != addressDetails.getVillage()) {
			address.setVillage(addressDetails.getVillage());
		}
		if (addressDetails.getPincode() > 0) {
			address.setPincode(addressDetails.getPincode());
		}
	}

	/*
	 * getUserById : This method retrieves user object from database using
	 * userid
	 * 
	 * parameters: String userid
	 * 
	 * return User Object
	 * 
	 * 
	 */

	private void updateNonTeachingStaffDocumentsDetails(StaffDocuments staffDocuments,
			StaffDocumentsDetails staffDocumentsDetails) {
		if (null != staffDocumentsDetails.getNt_staff_10th_certificate()) {
			staffDocuments.setTen10thCertificate(staffDocumentsDetails.getNt_staff_10th_certificate());
		}
		if (null != staffDocumentsDetails.getNt_staff_12th_certificate()) {
			staffDocuments.setTwelve12thCertificate(staffDocumentsDetails.getNt_staff_12th_certificate());
		}
		if (null != staffDocumentsDetails.getNt_staff_degree_certificate()) {
			staffDocuments.setDegreeCertificate(staffDocumentsDetails.getNt_staff_degree_certificate());
		}
		if (null != staffDocumentsDetails.getNt_staff_medical_fitness_certificate()) {
			staffDocuments
					.setMedicalFitnessCertificate(staffDocumentsDetails.getNt_staff_medical_fitness_certificate());
		}
		if (null != staffDocumentsDetails.getNt_staff_pg_degree_certificate()) {
			staffDocuments.setPgDegreeCertificate(staffDocumentsDetails.getNt_staff_pg_degree_certificate());
		}
	}

	/*
	 * getUserById : This method retrieves user object from database using
	 * userid
	 * 
	 * parameters: String userid
	 * 
	 * return User Object
	 * 
	 * 
	 */

	private void updateNonTeachingStaffStatutoryDetails(StaffStatutory staffStatutory,
			StaffStatutoryDetails staffStatutoryDetails) {
		if (null != staffStatutoryDetails.getAadhaarCard()) {
			staffStatutory.setAadhaarCard(staffStatutoryDetails.getAadhaarCard());
		}
		if (null != staffStatutoryDetails.getAadhaarNo()) {
			staffStatutory.setAadhaarNo(staffStatutoryDetails.getAadhaarNo());
		}
		if (null != staffStatutoryDetails.getBankAccountNo()) {
			staffStatutory.setBankAccountNo(staffStatutoryDetails.getBankAccountNo());
		}
		if (null != staffStatutoryDetails.getBankIfscCode()) {
			staffStatutory.setBankIfscCode(staffStatutoryDetails.getBankIfscCode());
		}
		if (null != staffStatutoryDetails.getBankName()) {
			staffStatutory.setBankName(staffStatutoryDetails.getBankName());
		}
		if (null != staffStatutoryDetails.getPanCard()) {
			staffStatutory.setPanCard(staffStatutoryDetails.getPanCard());
		}
		if (null != staffStatutoryDetails.getPanNo()) {
			staffStatutory.setPanNo(staffStatutoryDetails.getPanNo());
		}
		if (null != staffStatutoryDetails.getPfNo()) {
			staffStatutory.setPfNo(staffStatutoryDetails.getPfNo());
		}
	}

	/*
	 * getUserById : This method retrieves user object from database using
	 * userid
	 * 
	 * parameters: String userid
	 * 
	 * return User Object
	 * 
	 * 
	 */

	private void updateNonTeachingStaffPreviousInformationDetails(StaffPreviousInformation staffPreviousInformation,
			StaffPreviousInformationDetails staffPreviousInformationDetails) {
		if (null != staffPreviousInformationDetails.getExperienceCertificate()) {
			staffPreviousInformation
					.setExperienceCertificate(staffPreviousInformationDetails.getExperienceCertificate());
		}
		if (null != staffPreviousInformationDetails.getLastDrawnPayslip()) {
			staffPreviousInformation.setLastDrawnPayslip(staffPreviousInformationDetails.getLastDrawnPayslip());
		}
		if (null != staffPreviousInformationDetails.getResume()) {
			staffPreviousInformation.setResume(staffPreviousInformationDetails.getResume());
		}
		if (null != staffPreviousInformationDetails.getLastWorkedOrganisation()) {
			staffPreviousInformation
					.setLastWorkedOrganisation(staffPreviousInformationDetails.getLastWorkedOrganisation());
		}
	}

	@Override
	public List<QualificationType> getQualification() throws AsmsException {
		Session session = null;
		try {
			session = sessionFactory.withOptions().tenantIdentifier(dbProperties.getProperty("default_schema"))
					.openSession();
			String hql = "from QualificationType";

			@SuppressWarnings("unchecked")
			List<QualificationType> user = session.createQuery(hql).list();
			session.close();
			return user;

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getUserById()" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public List<ReligionTypes> getReligions() throws AsmsException {
		Session session = null;
		try {
			session = sessionFactory.withOptions().tenantIdentifier(dbProperties.getProperty("default_schema"))
					.openSession();
			String hql = "from ReligionTypes";
			@SuppressWarnings("unchecked")
			List<ReligionTypes> user = session.createQuery(hql).list();
			session.close();
			return user;

		} catch (Exception e) {

			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getUserById()" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public List<CasteTypes> getCasteName() throws AsmsException {
		Session session = null;
		try {
			session = sessionFactory.withOptions().tenantIdentifier(dbProperties.getProperty("default_schema"))
					.openSession();
			String hql = "from CasteTypes";

			@SuppressWarnings("unchecked")
			List<CasteTypes> user = session.createQuery(hql).list();
			session.close();
			return user;

		} catch (Exception e) {
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getUserById()" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		}

	}

	@Override
	public void changePassword(ChangePasswordDetails changePasswordDetails, User user, String tenant)
			throws AsmsException {

		Session session = null;

		Transaction tx = null;

		try {
			messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenant);
			if (null != schema) {
				user = getUserById(user.getUserId(), schema);

				if (!user.getUserPassword().equals(changePasswordDetails.getCurrentpassword())) {
					throw exceptionHandler.constructAsmsException(
							messages.getString("CURRENTPASSWORD_IS_SAMEAS_NEWPASSWORD_NULL_CODE"),
							messages.getString("CURRENTPASSWORD_IS_SAMEAS_NEWPASSWORD_NULL_MSG"));
				}
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				tx = session.beginTransaction();
				User user1 = (User) session.load(User.class, user.getSerialNo());
				user1.setUserPassword(changePasswordDetails.getNewpassword());
				session.update(user1);
				tx.commit();
				session.close();
			}

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			if (null != session && session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "updateUser()" + "   ", e);
			ResourceBundle message = AsmsHelper.getMessageFromBundle();
			if (e instanceof AsmsException) {
				throw exceptionHandler.constructAsmsException(((AsmsException) e).getCode(),
						((AsmsException) e).getDescription());
			} else {
				throw exceptionHandler.constructAsmsException(message.getString("SYSTEM_EXCEPTION_CODE"),
						message.getString("SYSTEM_EXCEPTION"));
			}
		} finally {
			if (null != session && session.isOpen()) {
				session.close();
			}
		}

	}

	@Override
	public void createDefaultPrivileges(String role, User user) throws AsmsException {
		Session session = null;
		try {
			session = sessionFactory.withOptions().tenantIdentifier(dbProperties.getProperty("default_schema"))
					.openSession();
			String hql = "from DefaultActivities DA where DA.role = ? ";

			// get default activities for user
			@SuppressWarnings("unchecked")
			List<DefaultActivities> defaultActivities = session.createQuery(hql).setParameter(0, role).list();

			hql = "from Activity A ";

			@SuppressWarnings("unchecked")
			List<Activity> activities = session.createQuery(hql).list();
			Privilege pr;
			for (int i = 0; i < activities.size(); i++) {
				pr = new Privilege();
				pr.setActivityCategory(activities.get(i).getCategory());
				pr.setActivityName(activities.get(i).getName());
				pr.setCreateCheck(defaultActivities.get(i).getCreateCheck());
				pr.setDeleteCheck(defaultActivities.get(i).getDeleteCheck());
				pr.setRetrieveCheck(defaultActivities.get(i).getRetrieveCheck());
				pr.setUpdateCheck(defaultActivities.get(i).getUpdateCheck());
				pr.setUserObject(user);
				user.getPrivileges().add(pr);
			}
			session.close();

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getAll()" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}

	@Override
	public void assignPrivileges(UserDetails details, String tenant) throws AsmsException {
		Session session = null;

		Transaction tx = null;
		try {
			String schema = multitenancyDao.getSchema(tenant);
			if (null == schema) {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));

			}
			Set<Privilege> privileges = details.getPrivileges();
			User user = getUserById(details.getUserId(), schema);
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();

			user = (User) session.load(User.class, user.getSerialNo());
			Set<Privilege> userPrs = user.getPrivileges();
			String name;
			for (Privilege p : userPrs) {
				name = p.getActivityName();
				for (Privilege p1 : privileges) {
					if (p1.getActivityName().equalsIgnoreCase(name)) {
						p.setCreateCheck(p1.getCreateCheck());
						p.setUpdateCheck(p1.getUpdateCheck());
						p.setRetrieveCheck(p1.getRetrieveCheck());
						p.setDeleteCheck(p1.getDeleteCheck());
					}

				}

			}
			session.update(user);
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
					+ "assignPrivileges()" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}

	@Override
	public List<Student> getStudents(String className, String tenant) throws AsmsException {
		Session session = null;
		try {
			messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenant);
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				String hql = "from  Student  where studentClass='" + className + "' ";
				@SuppressWarnings("unchecked")
				List<Student> students = session.createQuery(hql).list();
				session.close();
				return students;
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getStudents()" + "   ", e);

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
	public void updateUserAccountStatus(UserDetails userDetails, User user, String tenant) throws AsmsException {

		Session session = null;

		Transaction tx = null;
		try {
			String schema = multitenancyDao.getSchema(tenant);
			if (null == schema) {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));

			}

			ResourceBundle messages = AsmsHelper.getMessageFromBundle();

			User userObject = getUserDetailsById(userDetails.getUserId(), tenant);
			if (null == userObject || userObject.toString().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("UPDATE_DETAILS_USER_NOT_FOUND_CODE"),
						messages.getString("UPDATE_DETAILS_USER_NOT_FOUND_MSG"));
			}

			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();
			User user1 = (User) session.load(User.class, userObject.getSerialNo());
			user1.setAccountStatus(userDetails.getAccountStatus());
			// String sql="update "+schema+".user_information set
			// account_status='"+userDetails.getAccountStatus()+"' where
			// user_id='"+userDetails.getUserId()+"' ";
			session.update(user1);
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
					+ "updateUserAccountStatus()" + "   ", e);
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

	// this method will calculate the % of form registering complete

	private int calulateRegistrationProgress(int completedDevisions, int divisions) {
		int percentage = 0;
		// percentage = 1008co
		percentage = (completedDevisions * 100) / divisions;

		return percentage;
	}

	@Override
	public void createAkacartUser(AkacartUserDetails akacartUserDetails, String userId, String tenant)
			throws AsmsException {
		Session session = null;
		AkacartUser akacartuser = new AkacartUser();
		String schema = multitenancyDao.getSchema(tenant);
		if (null != schema) {
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();

			akacartuser.setUserId(akacartUserDetails.getUserId());
			akacartuser.setAkakartAccess(akacartUserDetails.isAkakartAccess());
			akacartuser.setCreatedOn(akacartUserDetails.getCreatedOn());
		}
		insertAkacartUser(akacartuser, userId, tenant);

	}

	private void insertAkacartUser(AkacartUser akacartuser, String userId, String tenant) throws AsmsException {
		Session session = null;
		Transaction tx = null;

		try {
			String schema = multitenancyDao.getSchema(tenant);
			if (null != schema) {

				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();

				tx = session.beginTransaction();

				session.save(akacartuser);
				tx.commit();
				session.close();

			}
		} catch (Exception ex) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."

					+ "insertLessonPlan()" + "   ", ex);

			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));

		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> searchSiblings(String studentClass, String studentSection, String tenant) throws AsmsException {
		Session session = null;
		try {
			messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenant);

			if (null == studentClass) {
				throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_CLASS_NULL_CODE"),
						messages.getString("STUDENT_CLASS_NULL_CODE_MSG"));
			}

			if (null == studentSection) {
				throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_SECTION_NULL_CODE"),
						messages.getString("STUDENT_SECTION_NULL_CODE_MSG"));
			}

			if (null == schema) {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));

			}
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			// String hql = "from Student S where S.class=? and
			// S.sectionName=?";
			String hql = "from Student S where S.studentClass = ? and S.studentSection = ?";
			// select i from Institution i JOIN i.creationDate d where
			// i.nameOfInstitution
			// =? and d.particularDate=?

			List<Student> students = session.createQuery(hql).setParameter(0, studentClass)
					.setParameter(1, studentSection).list();
			// .setMaxResults(1).
			// List<Student> students = session.createQuery(hql).list();
			// session.close();
			List<String> studentDetails = new ArrayList<>();
			String studentDetail = null;
			for (Student S : students) {
				studentDetail = new String();
				studentDetail = S.getStudentFirstName() + " " + S.getStudentMiddleName() + " " + S.getStudentLastName()
						+ "_" + S.getAdmissionNo();
				studentDetails.add(studentDetail);
			}
			session.close();
			return studentDetails;

		} catch (Exception ex) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getAll()" + "   ", ex);
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
	public StudentDetails getStudentBySiblingAddmisionNo(String addmisionNo, String tenant) throws AsmsException {
		Session session = null;
		try {
			messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenant);

			if (null == addmisionNo) {
				throw exceptionHandler.constructAsmsException(messages.getString("ADDMISSION_NO_NULL_CODE"),
						messages.getString("ADDMISSION_NO_NULL_CODE_MSG"));
			}

			if (null == schema) {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));

			}
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			String hql = "from Student S where S.admissionNo = ?";
			Student st = (Student) session.createQuery(hql).setParameter(0, addmisionNo).uniqueResult();
			session.close();
			// UserDetails ud = entityCreator.createStudentDetail((User)st);
			UserDetails ud = entityCreator.createStudentDetail(st);
			return ud.getStudentDetails();

		} catch (Exception ex) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + " " + "Method: " + this.getClass().getName() + "."
					+ "getAll()" + " ", ex);
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

	@SuppressWarnings("unchecked")
	private List<Sibling> getSiblings(int studentId, String schema) throws AsmsException {

		Session session = null;
		List<Sibling> siblings = null;
		try {

			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			String hql = "from Sibling S where S.studentObject.serialNo=?";
			siblings = (List<Sibling>) session.createQuery(hql).setParameter(0, studentId).list();
			session.close();
			return siblings;

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getSiblings()" + "   ", e);
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

}