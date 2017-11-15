package com.asms.rolemgmt.dao;

import java.util.List;

import com.asms.Exception.AsmsException;
import com.asms.rolemgmt.entity.Role;
import com.asms.rolemgmt.entity.SubRole;

public interface RoleMgmtDao {

	/**
	 * @param Role
	 * @return
	 * @throws AsmsException
	 */
	public void createDefaultRoles(String schema) throws AsmsException;

	
	
	public void insertSubRole(String roleName, String subRoleName,String tenantId) throws AsmsException;
	

	public List<Role> getRole(String tenantId) throws AsmsException;

	public List<SubRole> getSubRole(String Role, String tenantId) throws AsmsException;


}
