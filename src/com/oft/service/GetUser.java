package com.oft.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.oft.aspect.logger.Loggable;
import com.oft.dao.idao.IUserDAO;
import com.oft.pojo.User;

@Path("/user")
public class GetUser {

   
	@Autowired
	private IUserDAO userDAO;
	
	@Path("/getUser")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Loggable(message="121111")
	public JSONObject getUser(User user ){
		
		return userDAO.getUser(user);
		
		 
		
	}
}
