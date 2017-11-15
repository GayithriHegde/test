package com.asms.common.service;

import java.util.Properties;
import javax.annotation.Resource;



/*
 * BaseService.java is the base class for all service classes.
 * All service classes should extend this class.
 * 
 * Fields are,
 * 
 * properties:   This represents the properties defined in asms.properties file
 * 
 *
 * 
 */

public class BaseService {
	
	
	// properties represent asms.properties file 
	@Resource(name = "asmsProperties")
	private Properties properties;
	
	
	
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	

}
