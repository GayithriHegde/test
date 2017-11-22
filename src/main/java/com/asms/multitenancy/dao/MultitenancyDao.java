package com.asms.multitenancy.dao;
import java.util.List;

import com.asms.Exception.AsmsException;
import com.asms.multitenancy.entity.Trust;
import com.asms.schoolmgmt.entity.AcademicYear;

public interface MultitenancyDao {
	

	
	
	/**
	 * 
	 * @param name
	 * @return
	 * @throws AsmsException
	 */
	public boolean createSchema(String name) throws AsmsException;
	
	/**
	 * @param id
	 * @param name
	 * @return
	 * @throws AsmsException
	 */
	public String createTenantId(String id,String name, String subDomain) throws AsmsException;
	
	

	/**
	 * @param tenantId
	 * @return String tenant schema name
	 * @throws AsmsException
	 */
	public String getSchema(String tenantId) throws AsmsException;
	
	/**
	 * @param TrustDetails
	 * @return
	 * @throws AsmsException
	 */
	//public void createTrust(TrustDetails trustDetails, String schema) throws AsmsException;
	
	/**
	 * @param 
	 * @return List of  Trust 
	 * @throws AsmsException
	 */
	public List<Trust> getTrust(String schema) throws AsmsException;
	
	
	/**
	 * @param schema 
	 * @param 
	 * @return List of AcademicYear 
	 * @throws AsmsException
	 */

	public List<AcademicYear> getAcademicYear(String tenantId) throws AsmsException;

	public boolean updateSchema(String name) throws AsmsException;
	
	public void insertTrust(Trust trust, String schema) throws AsmsException;
	
	public String getSchemaByDomain(String domain) throws AsmsException;


}
