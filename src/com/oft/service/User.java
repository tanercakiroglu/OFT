package com.oft.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.oft.aspect.exceptionhandler.HandleException;
import com.oft.aspect.logger.Loggable;
import com.oft.dao.idao.IUserDAO;
import com.oft.excpetion.BusinessException;
import com.oft.pojo.UserPojo;
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
	@HandleException(handleExcpetion=Constants.USER_SERVICE_GET_USER_DAO_ALIAS)
	public JSONObject getUser(@PathParam("username") String username,@PathParam("password") String password) throws BusinessException {

		if (Util.isNotNullOREmpty(username) && Util.isNotNullOREmpty(password)) {

			List<UserPojo> userFromDB = userDAO.login(username,password);

			if (userFromDB != null && userFromDB.size() == 1) {
				return Util.constructJSON(Constants.USER_SERVICE_GET_USER_DAO_ALIAS, true,userFromDB);
			} else {
				throw new BusinessException(Constants.LOGIN_FAILED);
			}
		} else {
			throw new BusinessException(Constants.INVALID_PARAMETERS);
		}

	}

	
	@Path("/register")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Loggable(message=Constants.USER_SERVICE_GET_USER_LOG_ALIAS)
	@HandleException(handleExcpetion=Constants.USER_SERVICE_GET_USER_DAO_ALIAS)
	public JSONObject register(UserPojo user )throws BusinessException {
		
		if( user!=null && Util.isNotNullOREmpty(user.getUserName()) &&  Util.isNotNullOREmpty(user.getPassword()) && Util.isNotNullOREmpty(Integer.toString(user.getRoomNO())))
		{	
		return Util.constructJSON(Constants.USER_SERVICE_GET_USER_DAO_ALIAS, true,userDAO.register(user));
		}
		else{
			throw new BusinessException(Constants.INVALID_PARAMETERS);
		}
	}
}
