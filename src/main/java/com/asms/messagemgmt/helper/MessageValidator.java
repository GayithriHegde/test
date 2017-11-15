package com.asms.messagemgmt.helper;

import java.util.ResourceBundle;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asms.Exception.AsmsException;
import com.asms.Exception.ExceptionHandler;
import com.asms.messagemgmt.request.BroadCasteSearchTypesDetails;

/*
 * Validator.java does the validation of requests
 * 
 */

@Component
public class MessageValidator {

	@Autowired
	private ExceptionHandler exceptionHandler;

	//private static final Logger logger = LoggerFactory.getLogger(MessageValidator.class);

	

	
	/*
	 * Method : validateMessageDetails - > validates BroadCasteSearchTypesDetails fields input
	 * : BroadCasteSearchTypesDetails, messages output : void
	 */
	public void validateMessageDetails(BroadCasteSearchTypesDetails details, ResourceBundle messages) throws AsmsException {
		if (null == details) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_NULL_CODE"),
					messages.getString("REQUEST_NULL"));
		}
		if (null == details.getMessage() || details.getMessage().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("MESSAGE_NULL_CODE"),
					messages.getString("MESSAGE_NULL_MSG"));
		}

		if (null == details.getSubject() || details.getSubject().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("MESSAGE_SUBJECT_NULL_CODE"),
					messages.getString("MESSAGE_SUBJECT_NULL_MSG"));
		}
		
	}


	


}