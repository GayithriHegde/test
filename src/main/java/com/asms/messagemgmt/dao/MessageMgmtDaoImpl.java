package com.asms.messagemgmt.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.annotation.Resource;
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
import com.asms.common.helper.AsmsHelper;
import com.asms.common.helper.Constants;
import com.asms.common.mail.EmailSender;
import com.asms.messagemgmt.entity.Message;
import com.asms.messagemgmt.entity.MessageReceiver;
import com.asms.messagemgmt.request.BroadCasteSearchTypesDetails;
import com.asms.messagemgmt.response.MessageDetails;
import com.asms.multitenancy.dao.MultitenancyDao;
import com.asms.schoolmgmt.entity.School;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.entity.management.Management;
import com.asms.usermgmt.entity.nonTeachingStaff.NonTeachingStaff;
import com.asms.usermgmt.entity.student.Student;
import com.asms.usermgmt.entity.teachingStaff.TeachingStaff;
import com.asms.usermgmt.helper.EntityCreator;
import com.asms.usermgmt.request.UserBasicDetails;

@Service
@Component
public class MessageMgmtDaoImpl implements MessageMgmtDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ExceptionHandler exceptionHandler;

	private static final Logger logger = LoggerFactory.getLogger(MessageMgmtDaoImpl.class);

	@Resource(name = "asmsdbProperties")
	private Properties dbProperties;

	@Resource(name = "asmsProperties")
	private Properties properties;

	@Autowired
	private MultitenancyDao multitenancyDao;

	@Autowired
	private EmailSender emailSender;

	ResourceBundle messages;

	/*
	 * Method : createBoradCasteMessage input: Message, list of receivers, list
	 * of emails, list of users ( selected from front end), and session
	 * 
	 * description : cretae message entities and receiver entities for selected
	 * students
	 * 
	 */

	@Override
	public void createBoradCasteMessage(BroadCasteSearchTypesDetails details, User user, String tenantId)
			throws AsmsException {
		Session session = null;
		Transaction tx = null;

		messages = AsmsHelper.getMessageFromBundle();

		try {
			// Date aLD = edtFormat.parse(sDate1);
			String schema = multitenancyDao.getSchema(tenantId);
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();

				String hql = " from School S";
				School school = (School) session.createQuery(hql).uniqueResult();

				/*
				 * List<String> toEmailIdQry = new ArrayList<>();
				 * 
				 * String hql = "select S.name from School S"; String school =
				 * (String) session.createQuery(hql).uniqueResult();
				 * 
				 * if (searchTypesDetails.isAllParents() == true) {
				 * toEmailIdQry.add("select fEmail from Parent"); } if
				 * (searchTypesDetails.isAllStudents() == true) {
				 * toEmailIdQry.add("select studentCreatedByWadmin from Student"
				 * ); }
				 * 
				 * if (searchTypesDetails.isAllStudents() == true &&
				 * searchTypesDetails.isAllParents() == true &&
				 * searchTypesDetails.isAllManagement() == false) {
				 * toEmailIdQry.add("select studentCreatedByWadmin from Student"
				 * ); } if (searchTypesDetails.isAllManagement() == true &&
				 * searchTypesDetails.isAllParents() == true &&
				 * searchTypesDetails.isAllStudents() == true) { toEmailIdQry.
				 * add("select mngmtCreatedByWadmin from Management"); } if
				 * (searchTypesDetails.isAllNonTeaching() == true) {
				 * toEmailIdQry.
				 * add("select createdByWadmin from NonTeachingStaff"); }
				 * 
				 * if (searchTypesDetails.isAllTeachingStaff() == true) {
				 * toEmailIdQry.add("select createdByWadmin from TeachingStaff"
				 * ); }
				 * 
				 * for (String qry : toEmailIdQry) { List<String> emails =
				 * session.createQuery(qry).list();
				 * 
				 * for (String email : emails) {
				 * 
				 * emailSender.send(school, email, "devendrasignh77@gmail.com",
				 * searchTypesDetails.getSubject(),
				 * searchTypesDetails.getMessage(), "text/html", aLD); }
				 * 
				 * } session.close();
				 */

				List<UserBasicDetails> users = details.getUsers();
				List<MessageReceiver> mReceivers = new ArrayList<MessageReceiver>();
				List<String> emails = new ArrayList<String>();

				// setting message table column values
				Message message = new Message();
				message.setAcademicYear(AsmsHelper.getCurrentAcademicYear());
				message.setDate(AsmsHelper.getTodayDate());
				message.setFromUserObject(user);
				message.setMessage(details.getMessage());

				if (details.isAllParents() == true) {
					createMessageAllParents(message, mReceivers, emails, users, session, details.getSelectAll());
				}

				if (details.isAllUsers() == true) {
					createMessageAllUsers(message, mReceivers, emails, users, session, details.getSelectAll());
				}
				if (details.isAllManagement() == true) {
					createMessageMgmtUsers(message, mReceivers, emails, users, session, details.getSelectAll());
				}

				if (details.isAllNonTeaching() == true) {
					createMessageNTUsers(message, mReceivers, emails, users, session, details.getSelectAll());
				}

				if (details.isAllTeachingStaff() == true) {
					createMessageTUsers(message, mReceivers, emails, users, session, details.getClassSelectAll());
				}

				if (details.isAllStudents() == true
						&& (null == details.getClassName() || details.getClassName().isEmpty())) {
					createMessageStudentUsers(message, mReceivers, emails, users, session, details.getClassSelectAll());
				}

				if (details.isAllStudents() == true && null != details.getClassName()
						&& !details.getClassName().isEmpty()) {
					message.setClassName(details.getClassName());
					message.setSectionName(details.getSection());
					if (details.getIsParent().equalsIgnoreCase("Yes")) {
						createMessageSelectedParents(message, mReceivers, emails, users, session,
								details.getClassSelectAll());
					} else {
						createMessageSelectedStudents(message, mReceivers, emails, users, session,
								details.getClassSelectAll());
					}

				}
				message.getMessageReceivers().addAll(mReceivers);
				tx = session.beginTransaction();
				session.save(message);
				tx.commit();

				session.close();

				for (String email : emails) {

					emailSender.send(school.getName(), email, school.getContactPersonEmailId(), details.getSubject(),
							details.getMessage(), "text/html");
				}

			} else

			{
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}
		}

		catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "createBoradCasteMessage" + "   ", e);
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
	public List<UserBasicDetails> searchForBroadcastmessages(BroadCasteSearchTypesDetails details, String tenant)
			throws AsmsException {
		Session session = null;
		messages = AsmsHelper.getMessageFromBundle();
		String schema = multitenancyDao.getSchema(tenant);
		if (null == schema) {
			throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
					messages.getString("TENANT_INVALID_CODE_MSG"));
		}
		List<UserBasicDetails> userDetails = new ArrayList<UserBasicDetails>();
		List<User> users = null;
		String hql;
		try {
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			if (details.isAllUsers() == true) {
				hql = "from User U";
				users = (List<User>) session.createQuery(hql).list();
			} else if (details.isAllManagement()) {
				hql = "from Management M";
				users = (List<User>) session.createQuery(hql).list();
			}

			else if (details.isAllNonTeaching()) {
				hql = "from NonTeachingStaff N";
				users = (List<User>) session.createQuery(hql).list();

			} // for parent also student list would be returned, but message
				// will go to parents
			else if (details.isAllStudents() || details.isAllParents()) {
				hql = "from Student S";
				users = (List<User>) session.createQuery(hql).list();

			} else if (details.isAllTeachingStaff()) {
				hql = "from TeachingStaff S";
				users = (List<User>) session.createQuery(hql).list();
			} else if (null != details.getClassName() && !details.getClassName().isEmpty()) {
				hql = "from Student S where S.studentClass = '" + details.getClassName() + "'";
				if (null != details.getSection() && !details.getSection().isEmpty()) {
					hql = hql + " and S.studentSection = '" + details.getSection() + "'";
				}
			}
			EntityCreator entityCreator = new EntityCreator();
			userDetails = entityCreator.createUserBasicDetails(users);

			session.close();
			return userDetails;

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "searchForBroadcastmessages()" + "   ", e);

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
	public List<MessageDetails> getReceivedMessages(String userId, String type, String tenant) throws AsmsException {
		Session session = null;
		messages = AsmsHelper.getMessageFromBundle();
		String schema = multitenancyDao.getSchema(tenant);
		if (null == schema) {
			throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
					messages.getString("TENANT_INVALID_CODE_MSG"));
		}

		MessageDetails messageDetails = null;
		List<MessageDetails> messageDetailsList = new ArrayList<MessageDetails>();
		String hql = null;
		Query query = null;
		try {
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			if (null == type || type.isEmpty()
					|| type.equalsIgnoreCase(Constants.message_view_types.mailbox.toString())) {
				hql = "select M.messageObject from MessageReceiver M  where M.userId = ? order by M.messageObject.date desc";
				query = session.createQuery(hql).setParameter(0, userId);
			} else if (type.equalsIgnoreCase(Constants.message_view_types.dashboard.toString())) {
				hql = "select M.messageObject from MessageReceiver M  where M.userId = ? order by M.messageObject.date desc";
				query = session.createQuery(hql).setParameter(0, userId);

			}

			try {
				query.setMaxResults(Integer.parseInt(properties.getProperty("dashboard_message_limit")));
			} catch (NumberFormatException e) {
				query.setMaxResults(10);
			}

			List<Message> messages = query.list();
			// Collections.sort(messages);
			for (Message m : messages) {
				messageDetails = new MessageDetails();
				messageDetails.setMessage(m.getMessage());
				messageDetails.setClassName(m.getClassName());
				messageDetails.setType(Constants.message_type.Inbox.toString());
				messageDetails.setSectionName(m.getSectionName());
				messageDetails.setToUserType(m.getReceiverType());
				if (m.getReceiverType().startsWith("selected")) {
					for (MessageReceiver mr : m.getMessageReceivers()) {
						messageDetails.getToUsers().add(mr.getName());
					}
				}
				messageDetailsList.add(messageDetails);

			}
			session.close();

			return messageDetailsList;

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getMessages()" + "   ", e);

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
	public List<MessageDetails> getSentMessages(int serialNo, String tenant) throws AsmsException {
		Session session = null;
		messages = AsmsHelper.getMessageFromBundle();
		String schema = multitenancyDao.getSchema(tenant);
		if (null == schema) {
			throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
					messages.getString("TENANT_INVALID_CODE_MSG"));
		}

		MessageDetails messageDetails = null;
		List<MessageDetails> messageDetailsList = new ArrayList<MessageDetails>();

		String hql = "from Message M where M.userObject.serialNo = ? order by M.date desc";
		try {
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			List<Message> messages = session.createQuery(hql).setParameter(0, serialNo).list();
			// Collections.sort(messages);
			for (Message m : messages) {
				messageDetails = new MessageDetails();
				messageDetails.setMessage(m.getMessage());
				messageDetails.setClassName(m.getClassName());
				messageDetails.setType(Constants.message_type.Sent.toString());
				messageDetails.setSectionName(m.getSectionName());
				messageDetails.setToUserType(m.getReceiverType());
				if (null != m.getReceiverType() && m.getReceiverType().startsWith("selected")) {
					for (MessageReceiver mr : m.getMessageReceivers()) {
						messageDetails.getToUsers().add(mr.getName());
					}
				}
				messageDetailsList.add(messageDetails);

			}
			session.close();

			return messageDetailsList;

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getMessages()" + "   ", e);

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
	private void createMessageAllParents(Message message, List<MessageReceiver> mReceivers, List<String> emails,
			List<UserBasicDetails> users, Session session, String selectAll) {

		MessageReceiver mr = null;
		Query q = null;
		String fMail = null;
		String mMail = null;
		String fName = null;
		String mName = null;
		String userid = null;

		if (selectAll.equalsIgnoreCase("No")) {
			message.setReceiverType(Constants.receiver_type.selected_parents.toString());
			for (UserBasicDetails ubd : users) {
				q = session
						.createQuery(
								"select P.fEmail, P.mEmail, P.fFirstName, P.fLastName,P.mFirstName, P.mLastName from Parent P where P.studentObject.userId =?")
						.setParameter(0, ubd.getUserId());
				List<Object[]> parents = (List<Object[]>) q.list();
				for (Object[] parent : parents) {
					fMail = (String) parent[0];
					mMail = (String) parent[1];
					fName = (String) parent[2] + " " + (String) parent[3];
					mName = (String) parent[4] + " " + (String) parent[5];

					mr = new MessageReceiver();
					mr.setIsParent("Yes");
					mr.setMessageObject(message);
					mr.setUserId(ubd.getUserId());
					mr.setName(fName);
					mReceivers.add(mr);
					emails.add(fMail);
					// if father's email and mother's email same
					// send mail only once
					if (!fMail.equalsIgnoreCase(mMail)) {
						mr = new MessageReceiver();
						mr.setIsParent("Yes");
						mr.setMessageObject(message);
						mr.setUserId(ubd.getUserId());
						mr.setName(mName);
						mReceivers.add(mr);
						emails.add(mMail);
					}
				}
			}

		} else {
			message.setReceiverType(Constants.receiver_type.all_parents.toString());
			q = session.createQuery(
					"select P.fEmail, P.mEmail,  P.fFirstName, P.fLastName,P.mFirstName, P.mLastName, P.studentObject.userId from Parent P ");
			List<Object[]> parents = (List<Object[]>) q.list();
			for (Object[] parent : parents) {
				fMail = (String) parent[0];
				mMail = (String) parent[1];
				fName = (String) parent[2] + " " + (String) parent[3];
				mName = (String) parent[4] + " " + (String) parent[5];
				userid = (String) parent[6];

				mr = new MessageReceiver();
				mr.setIsParent("Yes");
				mr.setMessageObject(message);
				mr.setUserId(userid);
				mr.setName(fName);
				mReceivers.add(mr);
				emails.add(fMail);
				// if father's email and mother's email same send
				// mail only once
				if (!fMail.equalsIgnoreCase(mMail)) {
					mr = new MessageReceiver();
					mr.setIsParent("Yes");
					mr.setMessageObject(message);
					mr.setUserId(userid);
					mr.setName(mName);
					mReceivers.add(mr);
					emails.add(mMail);
				}
			}
		}

	}

	/*
	 * Method : createMessageAllUsers input: Message, list of receivers, list of
	 * emails, list of users ( selected from front end), and session
	 * 
	 * description : cretae message entities and receiver entities when all
	 * users selected
	 * 
	 */

	@SuppressWarnings("unchecked")
	private void createMessageAllUsers(Message message, List<MessageReceiver> mReceivers, List<String> emails,
			List<UserBasicDetails> users, Session session, String selectAll) {

		MessageReceiver mr = null;
		Query q = null;
		String fMail = null;
		String mMail = null;
		String userid = null;
		String fName = null;
		String mName = null;

		// if the list of users is coming from front end
		if (selectAll.equalsIgnoreCase("No")) {
			message.setReceiverType(Constants.receiver_type.selected_users.toString());

			// get parents of the selected students
			for (UserBasicDetails ubd : users) {
				q = session
						.createQuery(
								"select P.fEmail, P.mEmail, P.fFirstName, P.fLastName,P.mFirstName, P.mLastName from Parent P where P.studentObject.userId =?")
						.setParameter(0, ubd.getUserId());
				List<Object[]> parents = (List<Object[]>) q.list();
				for (Object[] parent : parents) {
					fMail = (String) parent[0];
					mMail = (String) parent[1];
					fName = (String) parent[2] + " " + (String) parent[3];
					mName = (String) parent[4] + " " + (String) parent[5];

					mr = new MessageReceiver();
					mr.setIsParent("Yes");
					mr.setMessageObject(message);
					mr.setName(fName);
					mr.setUserId(ubd.getUserId());
					mReceivers.add(mr);
					emails.add(fMail);
					// if father's email and mother's email same
					// send mail only once
					if (!fMail.equalsIgnoreCase(mMail)) {
						mr = new MessageReceiver();
						mr.setIsParent("Yes");
						mr.setMessageObject(message);
						mr.setUserId(ubd.getUserId());
						mr.setName(mName);
						mReceivers.add(mr);
						emails.add(mMail);
					}
				}

				q = session.createQuery("from User U where U.userId =?").setParameter(0, ubd.getUserId());
				List<User> userList = (List<User>) q.list();
				List<UserBasicDetails> detailList = new EntityCreator().createUserBasicDetails(userList);
				for (UserBasicDetails e : detailList) {
					mr = new MessageReceiver();
					mr.setMessageObject(message);
					mr.setUserId(ubd.getUserId());
					mReceivers.add(mr);
					emails.add(e.getEmail());
					mr.setName(e.getFirstName() + " " + e.getLastName());

				}

			}
			// get all the parents

		} else {
			message.setReceiverType(Constants.receiver_type.all.toString());
			q = session.createQuery(
					"select P.fEmail, P.mEmail, P.fFirstName, P.fLastName,P.mFirstName, P.mLastName, P.studentObject.userId from Parent P ");
			List<Object[]> parents = (List<Object[]>) q.list();
			for (Object[] parent : parents) {
				fMail = (String) parent[0];
				mMail = (String) parent[1];
				fName = (String) parent[2] + " " + (String) parent[3];
				mName = (String) parent[4] + " " + (String) parent[5];
				userid = (String) parent[6];

				mr = new MessageReceiver();
				mr.setIsParent("Yes");
				mr.setMessageObject(message);
				mr.setUserId(userid);
				mr.setName(fName);
				mReceivers.add(mr);
				emails.add(fMail);
				// if father's email and mother's email same send
				// mail only once
				if (!fMail.equalsIgnoreCase(mMail)) {
					mr = new MessageReceiver();
					mr.setIsParent("Yes");
					mr.setMessageObject(message);
					mr.setUserId(userid);
					mr.setName(mName);
					mReceivers.add(mr);
					emails.add(mMail);
				}
			}

			q = session.createQuery("from User U ");
			List<User> userList = (List<User>) q.list();
			List<UserBasicDetails> detailList = new EntityCreator().createUserBasicDetails(userList);
			for (UserBasicDetails e : detailList) {
				mr = new MessageReceiver();
				mr.setMessageObject(message);
				mr.setUserId(e.getUserId());
				mReceivers.add(mr);
				emails.add(e.getEmail());
				mr.setName(e.getFirstName() + " " + e.getLastName());

			}
		}

	}
	/*
	 * Method : createMessageMgmtUsers input: Message, list of receivers, list
	 * of emails, list of users ( selected from front end), and session
	 * 
	 * description : cretae message entities and receiver entities when all
	 * management users selected
	 * 
	 */

	@SuppressWarnings("unchecked")
	private void createMessageMgmtUsers(Message message, List<MessageReceiver> mReceivers, List<String> emails,
			List<UserBasicDetails> users, Session session, String selectAll) {

		MessageReceiver mr = null;
		Query q = null;

		message.setReceiverType(Constants.receiver_type.selected_staff.toString());
		if (selectAll.equalsIgnoreCase("No")) {

			// get parents of the selected students
			for (UserBasicDetails ubd : users) {

				q = session.createQuery(" from User U where U.userId =?").setParameter(0, ubd.getUserId());
				List<User> userList = (List<User>) q.list();
				List<UserBasicDetails> detailList = new EntityCreator().createUserBasicDetails(userList);
				for (UserBasicDetails e : detailList) {
					mr = new MessageReceiver();
					mr.setMessageObject(message);
					mr.setUserId(e.getUserId());
					mReceivers.add(mr);
					emails.add(e.getEmail());
					mr.setName(e.getFirstName() + " " + e.getLastName());

				}
			}

		} else {
			message.setReceiverType(Constants.receiver_type.all_management.toString());
			q = session.createQuery("from Management U ");
			List<Management> userList = (List<Management>) q.list();
			for (Management e : userList) {
				mr = new MessageReceiver();
				mr.setMessageObject(message);
				mr.setUserId(e.getUserId());
				mReceivers.add(mr);
				emails.add(e.getEmail());
				mr.setName(e.getMngmtFirstName() + " " + e.getMngmtLastName());

			}
		}

	}

	/*
	 * Method : createMessageNTUsers input: Message, list of receivers, list of
	 * emails, list of users ( selected from front end), and session
	 * 
	 * description : cretae message entities and receiver entities when all nan
	 * teaching staff users selected
	 * 
	 */

	@SuppressWarnings("unchecked")
	private void createMessageNTUsers(Message message, List<MessageReceiver> mReceivers, List<String> emails,
			List<UserBasicDetails> users, Session session, String selectAll) {

		MessageReceiver mr = null;
		Query q = null;

		message.setReceiverType(Constants.receiver_type.selected_staff.toString());
		if (selectAll.equalsIgnoreCase("No")) {

			// get parents of the selected students
			for (UserBasicDetails ubd : users) {

				q = session.createQuery("from User U where U.userId =?").setParameter(0, ubd.getUserId());
				List<User> userList = (List<User>) q.list();
				List<UserBasicDetails> detailList = new EntityCreator().createUserBasicDetails(userList);
				for (UserBasicDetails e : detailList) {
					mr = new MessageReceiver();
					mr.setMessageObject(message);
					mr.setUserId(e.getUserId());
					mReceivers.add(mr);
					emails.add(e.getEmail());
					mr.setName(e.getFirstName() + " " + e.getLastName());

				}

			}

		} else {
			message.setReceiverType(Constants.receiver_type.all_non_teaching_staff.toString());

			q = session.createQuery("from NonTeachingStaff U ");
			List<NonTeachingStaff> userList = (List<NonTeachingStaff>) q.list();
			for (NonTeachingStaff e : userList) {
				mr = new MessageReceiver();
				mr.setMessageObject(message);
				mr.setUserId(e.getUserId());
				mReceivers.add(mr);
				emails.add(e.getEmail());
				mr.setName(e.getFirstName() + " " + e.getLastName());

			}
		}

	}

	/*
	 * Method : createMessageTUsers input: Message, list of receivers, list of
	 * emails, list of users ( selected from front end), and session
	 * 
	 * description : cretae message entities and receiver entities when all
	 * teaching staff users selected
	 * 
	 */

	@SuppressWarnings("unchecked")
	private void createMessageTUsers(Message message, List<MessageReceiver> mReceivers, List<String> emails,
			List<UserBasicDetails> users, Session session, String selectAll) {

		MessageReceiver mr = null;
		Query q = null;

		if (selectAll.equalsIgnoreCase("No")) {
			message.setReceiverType(Constants.receiver_type.selected_staff.toString());

			// get parents of the selected students
			for (UserBasicDetails ubd : users) {

				q = session.createQuery("from User U where U.userId =?").setParameter(0, ubd.getUserId());
				List<User> userList = (List<User>) q.list();
				List<UserBasicDetails> detailList = new EntityCreator().createUserBasicDetails(userList);
				for (UserBasicDetails e : detailList) {
					mr = new MessageReceiver();
					mr.setMessageObject(message);
					mr.setUserId(e.getUserId());
					mReceivers.add(mr);
					emails.add(e.getEmail());
					mr.setName(e.getFirstName() + " " + e.getLastName());

				}

			}

		} else {
			message.setReceiverType(Constants.receiver_type.all_teaching_staff.toString());
			q = session.createQuery("from TeachingStaff U ");
			List<TeachingStaff> userList = (List<TeachingStaff>) q.list();
			for (TeachingStaff e : userList) {
				mr = new MessageReceiver();
				mr.setMessageObject(message);
				mr.setUserId(e.getUserId());
				mReceivers.add(mr);
				emails.add(e.getEmail());
				mr.setName(e.getFirstName() + " " + e.getLastName());

			}
		}

	}

	/*
	 * Method : createMessageStudentUsers input: Message, list of receivers,
	 * list of emails, list of users ( selected from front end), and session
	 * 
	 * description : cretae message entities and receiver entities when all
	 * student users selected
	 * 
	 */

	@SuppressWarnings("unchecked")
	private void createMessageStudentUsers(Message message, List<MessageReceiver> mReceivers, List<String> emails,
			List<UserBasicDetails> users, Session session, String selectAll) {

		MessageReceiver mr = null;
		Query q = null;

		if (selectAll.equalsIgnoreCase("No")) {
			message.setReceiverType(Constants.receiver_type.selected_students.toString());
			for (UserBasicDetails ubd : users) {

				q = session.createQuery("from User U where U.userId =?").setParameter(0, ubd.getUserId());
				List<User> userList = (List<User>) q.list();
				List<UserBasicDetails> detailList = new EntityCreator().createUserBasicDetails(userList);
				for (UserBasicDetails e : detailList) {
					mr = new MessageReceiver();
					mr.setMessageObject(message);
					mr.setUserId(e.getUserId());
					mReceivers.add(mr);
					emails.add(e.getEmail());
					mr.setName(e.getFirstName() + " " + e.getLastName());

				}

			}

		} else {
			message.setReceiverType(Constants.receiver_type.all_students.toString());
			q = session.createQuery("from Student U ");
			List<Student> userList = (List<Student>) q.list();
			for (Student e : userList) {
				mr = new MessageReceiver();
				mr.setMessageObject(message);
				mr.setUserId(e.getUserId());
				mReceivers.add(mr);
				emails.add(e.getEmail());
				mr.setName(e.getStudentFirstName() + " " + e.getStudentLastName());

			}
		}

	}

	/*
	 * Method : createMessageSelectedParents input: Message, list of receivers,
	 * list of emails, list of users ( selected from front end), and session
	 * 
	 * description : cretae message entities and receiver entities for selected
	 * parents
	 * 
	 */

	@SuppressWarnings("unchecked")
	private void createMessageSelectedParents(Message message, List<MessageReceiver> mReceivers, List<String> emails,
			List<UserBasicDetails> users, Session session, String selectAll) {

		MessageReceiver mr = null;
		Query q = null;
		String fMail = null;
		String mMail = null;
		String userid = null;
		String fName = null;
		String mName = null;

		message.setReceiverType(
				Constants.receiver_type.selected_parents.toString() + " Class " + message.getClassName());
		if (null != message.getSectionName() && !message.getSectionName().isEmpty()) {
			message.setReceiverType(message.getReceiverType() + " Section " + message.getSectionName());
		}
		if (selectAll.equalsIgnoreCase("No")) {

			// get parents of the selected students
			for (UserBasicDetails ubd : users) {
				q = session
						.createQuery(
								"select P.fEmail, P.mEmail, P.fFirstName, P.fLastName,P.mFirstName, P.mLastName from Parent P where P.studentObject.userId =?")
						.setParameter(0, ubd.getUserId());
				List<Object[]> parents = (List<Object[]>) q.list();
				for (Object[] parent : parents) {
					fMail = (String) parent[0];
					mMail = (String) parent[1];
					fName = (String) parent[2] + " " + (String) parent[3];
					mName = (String) parent[4] + " " + (String) parent[5];

					mr = new MessageReceiver();
					mr.setIsParent("Yes");
					mr.setMessageObject(message);
					mr.setUserId(ubd.getUserId());
					mr.setName(fName);
					mReceivers.add(mr);
					emails.add(fMail);
					// if father's email and mother's email same
					// send mail only once
					if (!fMail.equalsIgnoreCase(mMail)) {
						mr = new MessageReceiver();
						mr.setIsParent("Yes");
						mr.setMessageObject(message);
						mr.setUserId(ubd.getUserId());
						mr.setName(mName);
						mReceivers.add(mr);
						emails.add(mMail);
					}
				}

			}

		} else {
			message.setReceiverType(
					Constants.receiver_type.all_parents.toString() + " Class " + message.getClassName());
			if (null != message.getSectionName() && !message.getSectionName().isEmpty()) {
				message.setReceiverType(message.getReceiverType() + " Section " + message.getSectionName());
			}
			if (null != message.getClassName() && !message.getSectionName().isEmpty()) {
				String hql = "select P.fEmail, P.mEmail,  P.fFirstName, P.fLastName,P.mFirstName, P.mLastName, P.studentObject.userId from Parent P where P.studentObject.studentClass = '"
						+ message.getClassName() + "'";
				if (null != message.getSectionName() && !message.getSectionName().isEmpty()) {
					hql = hql + " and P.studentObject.studentSection = '" + message.getSectionName() + "'";
				}
				q = session.createQuery(hql);
				List<Object[]> parents = (List<Object[]>) q.list();
				for (Object[] parent : parents) {
					fMail = (String) parent[0];
					mMail = (String) parent[1];
					fName = (String) parent[2] + " " + (String) parent[3];
					mName = (String) parent[4] + " " + (String) parent[5];
					userid = (String) parent[6];

					mr = new MessageReceiver();
					mr.setIsParent("Yes");
					mr.setMessageObject(message);
					mr.setUserId(userid);
					mr.setName(fName);
					mReceivers.add(mr);
					emails.add(fMail);
					// if father's email and mother's email same send
					// mail only once
					if (!fMail.equalsIgnoreCase(mMail)) {
						mr = new MessageReceiver();
						mr.setIsParent("Yes");
						mr.setMessageObject(message);
						mr.setUserId(userid);
						mr.setName(mName);
						mReceivers.add(mr);
						emails.add(mMail);
					}
				}
			}
		}

	}

	/*
	 * Method : createMessageSelectedStudents input: Message, list of receivers,
	 * list of emails, list of users ( selected from front end), and session
	 * 
	 * description : cretae message entities and receiver entities for selected
	 * students
	 * 
	 */

	@SuppressWarnings("unchecked")
	private void createMessageSelectedStudents(Message message, List<MessageReceiver> mReceivers, List<String> emails,
			List<UserBasicDetails> users, Session session, String selectAll) {

		MessageReceiver mr = null;
		Query q = null;

		message.setReceiverType(
				Constants.receiver_type.selected_students.toString() + " Class " + message.getClassName());
		if (null != message.getSectionName() && !message.getSectionName().isEmpty()) {
			message.setReceiverType(message.getReceiverType() + " Section " + message.getSectionName());
		}
		if (selectAll.equalsIgnoreCase("No")) {

			for (UserBasicDetails ubd : users) {

				q = session.createQuery("from User U where U.userId =?").setParameter(0, ubd.getUserId());
				List<User> userList = (List<User>) q.list();
				List<UserBasicDetails> detailList = new EntityCreator().createUserBasicDetails(userList);
				for (UserBasicDetails e : detailList) {
					mr = new MessageReceiver();
					mr.setMessageObject(message);
					mr.setUserId(e.getUserId());
					mReceivers.add(mr);
					emails.add(e.getEmail());
					mr.setName(e.getFirstName() + " " + e.getLastName());

				}

			}

		} else {
			String userid = null;
			String email = null;
			String name = null;
			message.setReceiverType(
					Constants.receiver_type.all_students.toString() + " Class " + message.getClassName());
			if (null != message.getSectionName() && !message.getSectionName().isEmpty()) {
				message.setReceiverType(message.getReceiverType() + " Section " + message.getSectionName());
			}
			if (null != message.getClassName() && !message.getClassName().isEmpty()) {
				String hql = "select U.email,U.userId,U.studentFirstName, U.studentLastName from Student U where U.studentClass = '"
						+ message.getClassName() + "'";
				if (null != message.getSectionName() && !message.getSectionName().isEmpty()) {
					hql = hql + " and U.studentSection = '" + message.getSectionName() + "'";
				}
				q = session.createQuery(hql);
				List<Object[]> students = (List<Object[]>) q.list();
				for (Object[] s : students) {
					email = (String) s[0];

					userid = (String) s[1];
					name = (String) s[2] + " " + (String) s[3];

					mr = new MessageReceiver();
					mr.setIsParent("No");
					mr.setMessageObject(message);
					mr.setUserId(userid);
					mr.setName(name);
					mReceivers.add(mr);
					emails.add(email);

				}
			}
		}
	}
	/*
	 * Post API MessageReciver updateupdateReadStatus  : This method MessageReciver  in the database.
	 * 
	 * 
	 * parameters: String messageId , String tenant id, String user
	 * 
	 * return: void
	 * 
	 * 
	 */
	@Override
	public void updateReadStatus(int messageId, User user, String tenant) throws AsmsException {
		Session session = null;

		Transaction tx = null;
		try {
			String schema = multitenancyDao.getSchema(tenant);
			if (null == schema) {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));

			}

			ResourceBundle messages = AsmsHelper.getMessageFromBundle();

			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();// get
			MessageReceiver messageReceiverObject = getmessageReceiverObject(messageId, user, schema);
			MessageReceiver messageReceiver = (MessageReceiver) session.load(MessageReceiver.class, messageReceiverObject.getSerialNo());
			if (null == messageReceiver || messageReceiver.toString().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("UPDATE_READ_NOT_FOUND_CODE"),
						messages.getString("UPDATE_READ_NOT_FOUND_MSG"));
			}
			messageReceiver.setRead(true);
			session.update(messageReceiver);
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
					+ "updateReadStatus()" + "   ", e);
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

	private MessageReceiver getmessageReceiverObject(int messageId, User user, String schema) throws AsmsException {

		Session session = null;
		Transaction tx = null;
		MessageReceiver mr = null;

		try {

			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();

			String hql = "from MessageReceiver M where M.userId=? and M.messageObject.serialNo =?";

			mr = (MessageReceiver) session.createQuery(hql).setParameter(0, user.getUserId()).setParameter(1, messageId)
					.uniqueResult();

			session.close();

			return mr;

		} catch (

		Exception ex) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."

					+ "getmessageReceiverObject()" + "   ", ex);

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
	 * Method : get Message List of unreadMessage : gets the Message input : tenant, userId
	 * return : messageReciver
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getUnreadMessages(User user, String tenant) throws AsmsException {
		Session session = null;

		try {
			String schema = multitenancyDao.getSchema(tenant);
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				String hql = "from MessageReceiver M where M.userId=? and M.isRead= ?";

				List<Message> messages = session.createQuery(hql).setParameter(0, user.getUserId())
						.setParameter(1, false).list();

				session.close();
				return messages;

			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));

			}

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getunreadmessages()" + "   ", e);
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
