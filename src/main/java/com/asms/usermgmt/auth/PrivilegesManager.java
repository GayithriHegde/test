package com.asms.usermgmt.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asms.Exception.AsmsException;
import com.asms.common.helper.Constants;
import com.asms.usermgmt.dao.UserMgmtDao;
import com.asms.usermgmt.entity.Privilege;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.helper.PrincipalUser;

/*
 * PrivilegesManager : For each request this class checks whether
 * 					   user who sent request is privileged to do the
 * 					   requested action 
 * 
 */

@Component
public class PrivilegesManager {

	@Autowired
	UserMgmtDao userMgmtDao;

	/*
	 * Method : isPrivileged parameters: String userEmail, String role, String
	 * action return : boolean
	 * 
	 * This method checks if the logged in user is privileged to do the
	 * requested action
	 */
	public PrincipalUser isPrivileged(User user, String category, String action) throws AsmsException {

		PrincipalUser pUser = new PrincipalUser();
		pUser.setPrivileged(false);
		pUser.setLoggedInUser(user);
		for (Privilege p : user.getPrivileges()) {
			if ((p.getActivityName()).equalsIgnoreCase(category)) {
				if(action.equalsIgnoreCase(Constants.privileges.create_check.toString())){
					if(p.getCreateCheck().equalsIgnoreCase("true")){
						pUser.setPrivileged(true);
					}
				}
				if(action.equalsIgnoreCase(Constants.privileges.update_check.toString())){
					if(p.getUpdateCheck().equalsIgnoreCase("true")){
						pUser.setPrivileged(true);
					}
				}
				if(action.equalsIgnoreCase(Constants.privileges.delete_check.toString())){
					if(p.getDeleteCheck().equalsIgnoreCase("true")){
						pUser.setPrivileged(true);
					}
				}
				if(action.equalsIgnoreCase(Constants.privileges.retrieve_check.toString())){
					if(p.getRetrieveCheck().equalsIgnoreCase("true")){
						pUser.setPrivileged(true);
					}
				}
			}
		}
		
		return pUser;
	}

}
