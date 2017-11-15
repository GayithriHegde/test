package com.asms.CountryMgmt.Response;

import java.util.List;

import com.asms.CountryMgmt.Entity.Country;
import com.asms.CountryMgmt.Entity.District;
import com.asms.CountryMgmt.Entity.StateEntity;
import com.asms.CountryMgmt.Entity.SubDivision;
import com.asms.CountryMgmt.Entity.Tehsil;
import com.asms.CountryMgmt.Entity.Village;
import com.asms.common.response.SuccessResponse;

/*
 * Class name: CountrySuccessResponse to return list of countries
 */
public class CountrySuccessResponse extends SuccessResponse {
	
	//private ArrayList<Country> countries;
	
	
	private List<StateEntity> stateEntities;
	
	private List<Country> countries;
	
	private List<String> districtNames,tehsilNames,villageNames,subDivisionNames;
	
	private List<District> districts;
	
	private List<Tehsil> tehsils;
	
	private List<Village> villages;
	
	private List<SubDivision> subDivisions;

	

	

	

	public List<StateEntity> getStateEntities() {
		return stateEntities;
	}

	public void setStateEntities(List<StateEntity> stateEntities) {
		this.stateEntities = stateEntities;
	}

	public List<String> getDistrictNames() {
		return districtNames;
	}

	public void setDistrictNames(List<String> districtNames) {
		this.districtNames = districtNames;
	}

	public List<String> getTehsilNames() {
		return tehsilNames;
	}

	public void setTehsilNames(List<String> tehsilNames) {
		this.tehsilNames = tehsilNames;
	}

	public List<String> getVillageNames() {
		return villageNames;
	}

	public void setVillageNames(List<String> villageNames) {
		this.villageNames = villageNames;
	}

	public List<String> getSubDivisionNames() {
		return subDivisionNames;
	}

	public void setSubDivisionNames(List<String> subDivisionNames) {
		this.subDivisionNames = subDivisionNames;
	}

	public List<District> getDistricts() {
		return districts;
	}

	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}

	public List<Tehsil> getTehsils() {
		return tehsils;
	}

	public void setTehsils(List<Tehsil> tehsils) {
		this.tehsils = tehsils;
	}

	public List<Village> getVillages() {
		return villages;
	}

	public void setVillages(List<Village> villages) {
		this.villages = villages;
	}

	public List<SubDivision> getSubDivisions() {
		return subDivisions;
	}

	public void setSubDivisions(List<SubDivision> subDivisions) {
		this.subDivisions = subDivisions;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public List<Country> getCountries() {
		return countries;
	}

	

}
