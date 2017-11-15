package com.asms.CountryMgmt.dao;

import java.util.List;

import com.asms.CountryMgmt.Entity.Country;
import com.asms.CountryMgmt.Entity.District;
import com.asms.CountryMgmt.Entity.StateEntity;
import com.asms.CountryMgmt.Entity.SubDivision;
import com.asms.CountryMgmt.Entity.Tehsil;
import com.asms.CountryMgmt.Entity.Village;
import com.asms.Exception.AsmsException;

/*
 * Interface name : CountryNamesDto
 * This interface contains set of operations to be performed on country table
 */
public interface CountryNamesDao
{
	//Following is the method declaration to get all countries names from DB
   public List<Country> getCountries() throws AsmsException;;

 //Following is the method declaration to get all States names from DB
	public List<StateEntity> getStates() throws AsmsException;
	
public List<District> getDistrict(int stateId) throws AsmsException;
	
	public List<Tehsil> getTehsil(int districtId) throws AsmsException;
	
	public List<Village> getVillage(int tehsilId) throws AsmsException;
	
	public List<SubDivision> getSubDivision(int districtId) throws AsmsException;
	
}
