package com.asms.feemgmt.dao;

import java.util.ArrayList;
import java.util.List;
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
import com.asms.feemgmt.entity.FeeCategory;
import com.asms.feemgmt.entity.FeeMaster;
import com.asms.feemgmt.entity.PaymentMode;
import com.asms.feemgmt.entity.PaymentType;
import com.asms.feemgmt.request.FeeCategoryDetails;
import com.asms.feemgmt.request.FeeMasterDetails;
import com.asms.multitenancy.dao.MultitenancyDao;

@Service
@Component
public class FeeMgmtDaoImpl implements FeeMgmtDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ExceptionHandler exceptionHandler;

	@Autowired
	private MultitenancyDao multitenancyDao;

	ResourceBundle messages;

	private static final Logger logger = LoggerFactory.getLogger(FeeMgmtDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.asms.feemgmt.dao.FeeMgmtDao#getFeeCategory(String tenantId)
	 */
	@Override
	public List<FeeCategory> getFeeCategory(String tenantId) throws AsmsException {

		Session session = null;
		Transaction tx = null;

		try {

			messages = AsmsHelper.getMessageFromBundle();

			String schema = multitenancyDao.getSchema(tenantId);
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();

				String hqry = "from FeeCategory F where F.display=true";

				@SuppressWarnings("unchecked")
				List<FeeCategory> feeCategories = session.createQuery(hqry).list();
				tx = session.beginTransaction();

				tx.commit();
				session.close();
				return feeCategories;

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
					+ "getFeeCategory()" + "   ", ex);
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
	 * @see com.asms.feemgmt.dao.FeeMgmtDao#createDefaultFee(String schema)
	 */
	@Override
	public void createDefaultFee(String schema) throws AsmsException {

		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();

			FeeCategory feeCategory = new FeeCategory();
			feeCategory.setCategory("Canteen");
			feeCategory.setDisplay(true);

			FeeCategory feeCategory1 = new FeeCategory();
			feeCategory1.setCategory("General");
			feeCategory1.setDisplay(true);

			FeeCategory feeCategory2 = new FeeCategory();
			feeCategory2.setCategory("Transport Fee");
			feeCategory2.setDisplay(true);

			FeeCategory feeCategory3 = new FeeCategory();
			feeCategory3.setCategory("Application & Prospectus");
			feeCategory3.setDisplay(true);

			FeeCategory feeCategory4 = new FeeCategory();
			feeCategory4.setCategory("Other");
			feeCategory4.setDisplay(true);

			PaymentType paymentType = new PaymentType();
			paymentType.setType("Yearly");
			paymentType.setDisplay(true);

			paymentType.setFeeCategoryObject(feeCategory);

			PaymentType paymentType1 = new PaymentType();
			paymentType1.setType("Quarterly");
			paymentType1.setDisplay(true);

			paymentType1.setFeeCategoryObject(feeCategory);

			PaymentType paymentType2 = new PaymentType();
			paymentType2.setType("Monthly");
			paymentType2.setDisplay(true);

			paymentType2.setFeeCategoryObject(feeCategory);

			List<PaymentType> paymentTypes = new ArrayList<PaymentType>();
			paymentTypes.add(paymentType);
			paymentTypes.add(paymentType1);
			paymentTypes.add(paymentType2);

			feeCategory.setPaymentTypes(paymentTypes);

			session.save(feeCategory);
			session.save(feeCategory1);
			session.save(feeCategory2);
			session.save(feeCategory3);
			session.save(feeCategory4);
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

					+ "createDefaultFee()" + "   ", ex);

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
	 * @see com.asms.feemgmt.dao.FeeMgmtDao#insertFee(com.asms.feemgmt.request.
	 * FeeCategoryDetails)
	 */
	@Override
	public void insertFee(FeeCategoryDetails categoryDetails, String tenant) throws AsmsException {

		FeeCategory feeCategory = new FeeCategory();

		feeCategory.setCategory(categoryDetails.getCategory());

		feeCategory.setDisplay(categoryDetails.isDisplay());

		for (int i = 0; i < categoryDetails.getPaymentTypes().size(); i++) {
			PaymentType paymentType = new PaymentType();

			paymentType.setType(categoryDetails.getPaymentTypes().get(i).getType());
			paymentType.setDisplay(categoryDetails.getPaymentTypes().get(i).isDisplay());
			paymentType.setFeeCategoryObject(feeCategory);
			feeCategory.getPaymentTypes().add(paymentType);

		}
		insertNewFee(feeCategory, tenant);

	}

	private void insertNewFee(FeeCategory feeCategory, String tenant) throws AsmsException {
		Session session = null;
		Transaction tx = null;

		try {

			messages = AsmsHelper.getMessageFromBundle();

			String schema = multitenancyDao.getSchema(tenant);
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				tx = session.beginTransaction();

				session.save(feeCategory);

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
					+ "insertNewFee()" + "   ", ex);
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
	 * @see com.asms.feemgmt.dao.FeeMgmtDao#setupFee(com.asms.feemgmt.request.
	 * FeeMasterDetails)
	 */

	@Override
	public void setupFee(FeeMasterDetails feeMasterDetails, String tenant) throws AsmsException {
		List<FeeMaster> feeMasters = new ArrayList<FeeMaster>();
		for (int i = 0; i < feeMasterDetails.getPaymentTypes().size(); i++) {
			FeeMaster feeMaster = new FeeMaster();

			feeMaster.setFeeCategory(feeMasterDetails.getFeeCategory());
			feeMaster.setPaymentTypes(feeMasterDetails.getPaymentTypes().get(i));
			feeMaster.setNoOfInstallments(feeMasterDetails.getNoOfInstallments());
			feeMaster.setNoOfMonths(feeMasterDetails.getNoOfMonths());
			feeMasters.add(feeMaster);

			
		}
		
		createFeeMaster(feeMasters, tenant);

	}

	private void createFeeMaster(List<FeeMaster> feeMasters, String tenant) throws AsmsException {
		Session session = null;
		Transaction tx = null;

		try {

			messages = AsmsHelper.getMessageFromBundle();

			String schema = multitenancyDao.getSchema(tenant);
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				tx = session.beginTransaction();

				for (FeeMaster feeMaster2 : feeMasters) {
	            session.save(feeMaster2);
	
                 }
			

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
					+ "createFeeMaster()" + "   ", ex);
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
	 * @see com.asms.feemgmt.dao.FeeMgmtDao#viewSetupFee(String tenant)
	 */

	@Override
	public List<FeeMaster> viewSetupFee(String tenant) throws AsmsException {
		Session session = null;
		Transaction tx = null;

		try {

			messages = AsmsHelper.getMessageFromBundle();

			String schema = multitenancyDao.getSchema(tenant);
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();

				String hqry = "from FeeMaster";

				@SuppressWarnings("unchecked")
				List<FeeMaster> feeMasters = session.createQuery(hqry).list();
				tx = session.beginTransaction();

				tx.commit();
				session.close();
				return feeMasters;

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
					+ "getFeeCategory()" + "   ", ex);
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
	public void createDefaultPaymentMode(String schema) throws AsmsException {
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();
			
			PaymentMode paymentMode = new PaymentMode();
			paymentMode.setPaymentMode("Text");
			
			PaymentMode paymentMode1 = new PaymentMode();
			paymentMode1.setPaymentMode("Online-payment");
			
			PaymentMode paymentMode2 = new PaymentMode();
			paymentMode2.setPaymentMode("Creadit/Debit");
			
			PaymentMode paymentMode3 = new PaymentMode();
			paymentMode3.setPaymentMode("Cash");
			
			PaymentMode paymentMode4 = new PaymentMode();
			paymentMode4.setPaymentMode("Include All");
			
			session.save(paymentMode);
			session.save(paymentMode1);
			session.save(paymentMode2);
			session.save(paymentMode3);
			session.save(paymentMode4);
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

					+ "createDefaultFee()" + "   ", ex);

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
	 * @see com.asms.feemgmt.dao.FeeMgmtDao#getPaymentMode(String tenantId)
	 */
	@Override
	public List<PaymentMode> getPaymentMode(String tenantId) throws AsmsException {

		Session session = null;
		Transaction tx = null;

		try {

			messages = AsmsHelper.getMessageFromBundle();

			String schema = multitenancyDao.getSchema(tenantId);
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();

				String hqry = "from PaymentMode P";

				@SuppressWarnings("unchecked")
				List<PaymentMode> paymentModes = session.createQuery(hqry).list();
				tx = session.beginTransaction();

				tx.commit();
				session.close();
				return paymentModes;

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
					+ "getFeeCategory()" + "   ", ex);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));

		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}

}
