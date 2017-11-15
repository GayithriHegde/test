package com.asms.messagemgmt.response;

import java.util.List;

import com.asms.common.response.SuccessResponse;
import com.asms.usermgmt.request.UserBasicDetails;
import com.mysql.jdbc.Messages;

public class MessageSuccessResponse extends SuccessResponse{
	
	
	
	private List<UserBasicDetails> userBasicDetails;
	
	private List<MessageDetails> messageDetails;
	
	private List<Messages> messages;
	
	

	public List<UserBasicDetails> getUserBasicDetails() {
		return userBasicDetails;
	}

	public void setUserBasicDetails(List<UserBasicDetails> userDetails) {
		this.userBasicDetails = userDetails;
	}

	public List<MessageDetails> getMessageDetails() {
		return messageDetails;
	}

	public void setMessageDetails(List<MessageDetails> messageDetails) {
		this.messageDetails = messageDetails;
	}

	public List<Messages> getMessages() {
		return messages;
	}

	public void setMessages(List<Messages> messages) {
		this.messages = messages;
	}

	



	
	

}
