package com.asms.CountryMgmt.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.asms.CountryMgmt.Entity.Country;
import com.asms.CountryMgmt.Entity.District;
import com.asms.CountryMgmt.Entity.StateEntity;
import com.asms.CountryMgmt.Entity.SubDivision;
import com.asms.CountryMgmt.Entity.Tehsil;
import com.asms.CountryMgmt.Entity.Village;
import com.asms.CountryMgmt.Response.CountrySuccessResponse;
import com.asms.CountryMgmt.dao.CountryNamesDao;
import com.asms.Exception.AsmsException;
import com.asms.common.helper.AsmsHelper;
import com.asms.common.response.FailureResponse;
import com.asms.common.service.BaseService;

/* Service class name: CountryMgmtService 
 * purpose : To manage country names
 * This class is the Web service end point.
 * The root path for CountryMgmtService is  API is /countries
 */

@Service
@Component
@Path("/countries")
public class CountryMgmtService extends BaseService {
	@Autowired
	CountryNamesDao countryNamesDao;
	
	
	ArrayList<Country> countryList;

	@Path("/country")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getCountriesName(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse)
	{
		
			try
			{
				List<Country> countries= countryNamesDao.getCountries();
					
				 CountrySuccessResponse countrySuccessResponse = new CountrySuccessResponse();
				 countrySuccessResponse.setCountries(countries);
				 return Response.status(Status.OK).entity(countrySuccessResponse).build();
					
			
			}	catch(AsmsException e) {
				FailureResponse failureResponse = new FailureResponse(e);
				return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
				
				
			
			}
		
	
	}
	
	@Path("/state")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getStatesName(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse)
	{

		try
		{
		
			List<StateEntity> states= countryNamesDao.getStates();
				
			 CountrySuccessResponse countrySuccessResponse = new CountrySuccessResponse();
			 countrySuccessResponse.setStateEntities(states);
			 return Response.status(Status.OK).entity(countrySuccessResponse).build();
				
		
		}	catch(AsmsException e) {
			FailureResponse failureResponse = new FailureResponse(e);
			return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
			
			
	}}
	
	@Path("/district")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getDistrict(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse, @QueryParam("id") int stateId){
		ResourceBundle messages;
		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			if(stateId == 0 )
			{
				failureResponse.setCode(Integer.parseInt(messages.getString("USERID_NULL_CODE")));
				failureResponse.setErrorDescription(messages.getString("USERID_NULL_MSG"));
				return Response.status(200).entity(failureResponse).build();
			
			}
			
			
			
		
			List<District> districts =	countryNamesDao.getDistrict(stateId);
			 CountrySuccessResponse countrySuccessResponse = new CountrySuccessResponse();
			 countrySuccessResponse.setDistricts(districts);
			 return Response.status(Status.OK).entity(countrySuccessResponse).build();
			 
		}
			catch (AsmsException ex) 
		{
			// construct failure response
			FailureResponse failureResponse = new FailureResponse(ex);
			return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
		}
	}
	
	@Path("/tehsil")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getTehsil(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse, @QueryParam("id") int districtId){
		ResourceBundle messages;
		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			if(districtId == 0 )
			{
				failureResponse.setCode(Integer.parseInt(messages.getString("USERID_NULL_CODE")));
				failureResponse.setErrorDescription(messages.getString("USERID_NULL_MSG"));
				return Response.status(200).entity(failureResponse).build();
			
			}
			
		
			
			List<Tehsil> tehsils =	countryNamesDao.getTehsil(districtId);
			 CountrySuccessResponse countrySuccessResponse = new CountrySuccessResponse();
			 countrySuccessResponse.setTehsils(tehsils);
			 return Response.status(Status.OK).entity(countrySuccessResponse).build();
			 
		}
			catch (AsmsException ex) 
		{
			// construct failure response
			FailureResponse failureResponse = new FailureResponse(ex);
			return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
		}
	}
	
	@Path("/village")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getVillage(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse, @QueryParam("id") int tehsilId){
		ResourceBundle messages;
		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			if(tehsilId == 0 )
			{
				failureResponse.setCode(Integer.parseInt(messages.getString("USERID_NULL_CODE")));
				failureResponse.setErrorDescription(messages.getString("USERID_NULL_MSG"));
				return Response.status(200).entity(failureResponse).build();
			
			}
		
			
			
			List<Village> villages =	countryNamesDao.getVillage(tehsilId);
			 CountrySuccessResponse countrySuccessResponse = new CountrySuccessResponse();
			 countrySuccessResponse.setVillages(villages);
			 return Response.status(Status.OK).entity(countrySuccessResponse).build();
			 
		}
			catch (AsmsException ex) 
		{
			// construct failure response
			FailureResponse failureResponse = new FailureResponse(ex);
			return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
		}
	}
	
	@Path("/subDivision")
	@GET
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getSubDivision(@Context HttpServletRequest hRequest, @Context HttpServletResponse hResponse, @QueryParam("id") int districtId){
		ResourceBundle messages;
		try {
			FailureResponse failureResponse = new FailureResponse();
			// get bundles for error messages
			messages = AsmsHelper.getMessageFromBundle();
			if(districtId == 0 )
			{
				failureResponse.setCode(Integer.parseInt(messages.getString("USERID_NULL_CODE")));
				failureResponse.setErrorDescription(messages.getString("USERID_NULL_MSG"));
				return Response.status(200).entity(failureResponse).build();
			
			}
			
		
	
			List<SubDivision> subDivisions =	countryNamesDao.getSubDivision(districtId);
			 CountrySuccessResponse countrySuccessResponse = new CountrySuccessResponse();
			 countrySuccessResponse.setSubDivisions(subDivisions);
			 return Response.status(Status.OK).entity(countrySuccessResponse).build();
		}
			catch (AsmsException ex) 
		{
			// construct failure response
			FailureResponse failureResponse = new FailureResponse(ex);
			return Response.status(Status.EXPECTATION_FAILED).entity(failureResponse).build();
		}
	}
}
