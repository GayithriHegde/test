package com.asms.messagemgmt.dao;

import java.util.List;

import com.asms.Exception.AsmsException;
import com.asms.messagemgmt.entity.Message;
import com.asms.messagemgmt.entity.MessageReceiver;
import com.asms.messagemgmt.request.BroadCasteSearchTypesDetails;
import com.asms.messagemgmt.response.MessageDetails;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.request.UserBasicDetails;


public interface MessageMgmtDao {





	
	public List<UserBasicDetails> searchForBroadcastmessages(BroadCasteSearchTypesDetails details, String tenant) throws AsmsException;


	public void createBoradCasteMessage(BroadCasteSearchTypesDetails details, User user, String tenantId) throws AsmsException;


	public List<MessageDetails> getSentMessages(int serialNo, String tenant) throws AsmsException;
	
	public List<MessageDetails> getReceivedMessages(String serialNo,String type, String tenant) throws AsmsException;
	
	public void updateReadStatus(int messageId, User user, String tenant )throws AsmsException;
	
	public List<Message>  getUnreadMessages(User user, String tenant) throws AsmsException;


	


	

}
