package com.asms.Exception;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.asms.common.helper.AsmsHelper;
/*ExceptionHandler class : creates Exception Objects based on the Exception occurred*/ 

@Component
public class ExceptionHandler 
{
	private static final Logger logger = LoggerFactory
			.getLogger(ExceptionHandler.class);
	
	
	/*
	 * Method Name:constructRuntimeExceptionObject
	 * Parameters : Exception 
	 * return : AsmsException
	 * 
	 */
	public AsmsException constructRuntimeExceptionObject(Exception ex)
	{
		AsmsException asmsException=new AsmsException();
		logger.error(ex.toString());
		//get the error code and description from resource bundles
		ResourceBundle messages =AsmsHelper.getMessageFromBundle();
		String code = messages.getString("SYSTEM_EXCEPTION_CODE");
		String description = messages.getString("SYSTEM_EXCEPTION");
		asmsException.setCode(code);
		asmsException.setDescription(description);
		return asmsException;
		
				
	}
	/*
	 * Method Name : constructAsmsException
	 * Parameters : error code, error description
	 * return : AsmsException
	 */
	
	public AsmsException constructAsmsException(String code,String description)
	{
		AsmsException asmsException=new AsmsException();
		
		asmsException.setCode(code);
		asmsException.setDescription(description);
		return asmsException;
		
				
	}

}
