package com.asms.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.asms.CountryMgmt.dao.CountryMgmtDaoImpl;
import com.asms.common.service.AuthenticationService;

public class AuthorisationFilter implements Filter {

	//private static final Logger logger = LoggerFactory.getLogger(AuthorisationFilter.class);

	public static final String AUTHENTICATION_HEADER = "Authorization";
	
	private static final Logger logger = LoggerFactory.getLogger(AuthorisationFilter.class);


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			HttpSession session = httpServletRequest.getSession();
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;

			// keeping session id in MDC so that it can be taken and used for login purpose
			MDC.put("sessionId", session.getId());
			
			httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
			httpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
			httpServletResponse.addHeader("Access-Control-Allow-Headers",
					"access-control-allow-Origin,content-type, accept");

			// get authenrication header
			String authCredentials = httpServletRequest.getHeader(AUTHENTICATION_HEADER);
			String pathInfo = httpServletRequest.getPathInfo();
			// skip login flow for authorization header check as only after login requests
			// are checked for authorization header
			logger.info(".....path info..." + pathInfo);
			if (pathInfo.contains("login") || pathInfo.contains("country") || pathInfo.contains("state") || pathInfo.contains("district")
					|| pathInfo.contains("village") || pathInfo.contains("tehsil") || pathInfo.contains("subDivision") ||  pathInfo.contains("trust/register") ||  pathInfo.contains("trusts") || pathInfo.contains("boards")){
				logger.info(".....    enetring if...for no authorisation header check...");
				chain.doFilter(request, response);
			} else {
				logger.info(".....    enetring else...for authorisation header check...");
				AuthenticationService authenticationService = new AuthenticationService();
				Object user = session.getAttribute("ap_user");
				boolean authenticationStatus = authenticationService.authenticate(authCredentials, user);
				if (authenticationStatus) {
					chain.doFilter(request, response);
				} else {
					if (response instanceof HttpServletResponse) {
						httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
						response.setContentType("application/json");
						// Get the printwriter object from response to write the required json object to the output stream      
						PrintWriter out = response.getWriter();
						// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
						String jsonObject = "{ \"Status\": \"Failure\", \"errorDescription\": \"UnAuthorized\" }";
						out.print(jsonObject);
						out.flush();
					}
				}
			}
		}

	}

	@Override
	public void destroy() {

	}

}
