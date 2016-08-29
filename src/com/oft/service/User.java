package com.oft.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.oft.aspect.logger.Loggable;
import com.oft.dao.idao.IUserDAO;
import com.oft.excpetion.BusinessException;
import com.oft.util.Constants;
import com.oft.util.Util;

@Path("/user")
public class User {

   
	@Autowired
	private IUserDAO userDAO;
	
	@Path("/login/{username}/{password}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Loggable(message=Constants.USER_SERVICE_GET_USER_LOG_ALIAS)
	public JSONObject getUser(@PathParam("username") String username,@PathParam("password") String password ) throws BusinessException{
		if(Util.isNotNull(username) && Util.isNotNull("password") && password.length()<5){
			return userDAO.login(username,password);
		}else{
			throw new BusinessException("Invalid Parameters");
		}
	}

	
	@Path("/register")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Loggable(message=Constants.USER_SERVICE_GET_USER_LOG_ALIAS)
	public JSONObject register(com.oft.pojo.User user ){
		
		return userDAO.register(user);
	}
}
