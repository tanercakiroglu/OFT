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
import com.oft.util.Constants;

@Path("/user")
public class User {

   
	@Autowired
	private IUserDAO userDAO;
	
	@Path("/getuser")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Loggable(message=Constants.USER_SERVICE_GET_USER_LOG_ALIAS)
	public JSONObject getUser(com.oft.pojo.User user ){
		
		return userDAO.getUser(user);
		
		 
		
	}
}
