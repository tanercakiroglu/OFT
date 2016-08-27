package com.oft.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.oft.aspect.logger.Loggable;
import com.oft.dao.idao.IMenuDAO;
import com.oft.util.Constants;

@Path("/menu")
public class Menu {
	
	
	@Autowired
	private IMenuDAO menuDAO;
	
	    @Path("/getmenu")  
	    @GET
	    @Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Loggable(message=Constants.MENU_SERVICE_GET_MENU_LOG_ALIAS)
		public JSONObject getMenu(){
				    	
			return  menuDAO.getMenu() ;
			
		}

}
