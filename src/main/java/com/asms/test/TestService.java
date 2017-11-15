package com.asms.test;


import java.util.Locale;
import java.util.ResourceBundle;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@Path("/test")
public class TestService {
	

	@Path("/get")
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getUserInfo(@PathParam("language") String language,@PathParam("userid") String userid) {
		           
		Locale currentLocale = new Locale("en", "US");
		ResourceBundle messages = ResourceBundle.getBundle("spring.MessageBundles.ErrorMessages", currentLocale);
		
		String output = messages.getString("USERNAME_NOT_FOUND");
		
		
		
		
		output = output+"hi";
		
		return Response.status(200).entity(output).build();


	}
	

	

	

}
