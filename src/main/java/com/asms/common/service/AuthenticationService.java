package com.asms.common.service;

import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.asms.filter.AuthorisationFilter;
import com.asms.multitenancy.entity.SuperAdmin;
import com.asms.usermgmt.dao.UserMgmtDao;
import com.asms.usermgmt.entity.User;

public class AuthenticationService {

	private static final Logger logger = LoggerFactory.getLogger(AuthorisationFilter.class);

	@SuppressWarnings("unused")
	@Autowired
	private UserMgmtDao userMgmtDao;

	public boolean authenticate(String authCredentials, Object user) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

		if (null == authCredentials) {
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "authenticate()" + "   " + "Authorization header not sent ");

			return false;
		}
		if (null == user) {
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "authenticate()" + "   " + "user not set in session ");

			return false;
		}
		// header value format will be "Basic encodedstring" for Basic
		// authentication. Example "Basic YWRtaW46YWRtaW4="
		final String encodedUserPassword = authCredentials.replaceFirst("Basic" + " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "authenticate()" + "   ", e);
		}
		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String email = tokenizer.nextToken();
		final String password = tokenizer.nextToken();
		if (user instanceof User) {
			User user1 = (User)user;
			if (email.equalsIgnoreCase(user1.getEmail()) && password.equalsIgnoreCase(user1.getUserPassword())) {
				return true;
			}else{
				return false;
			}
		} else {
			return false;
		}

	}
}
