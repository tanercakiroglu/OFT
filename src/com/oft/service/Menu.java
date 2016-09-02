package com.oft.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.oft.aspect.exceptionhandler.HandleException;
import com.oft.aspect.logger.Loggable;
import com.oft.dao.idao.IMenuDAO;
import com.oft.excpetion.BusinessException;
import com.oft.pojo.MenuPojo;
import com.oft.pojo.SubmenuPojo;
import com.oft.util.Constants;
import com.oft.util.Util;

@Path("/menu")
public class Menu {
	
	
	@Autowired
	private IMenuDAO menuDAO;
	
	    @Path("/getmenu")  
	    @GET
	    @Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Loggable(message=Constants.MENU_SERVICE_GET_MENU_LOG_ALIAS)
	    @HandleException(handleExcpetion=Constants.MENU_SERVICE_GET_MENU_LOG_ALIAS)
		public JSONObject getMenu(){
				    	
	    	List<MenuPojo> menu =  menuDAO.getMenu() ;
	    	return Util.constructJSON(Constants.MENU_SERVICE_GET_MENU_DAO_ALIAS, true, menu);
			
			
		}
	    
	    @Path("/getsubmenulist")  
	    @GET
	    @Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Loggable(message=Constants.MENU_SERVICE_GET_MENU_LOG_ALIAS)
	    @HandleException(handleExcpetion=Constants.MENU_SERVICE_GET_MENU_LOG_ALIAS)
	    public JSONObject getSubmenulist()throws BusinessException{
		   
	    	List<SubmenuPojo> submenu= menuDAO.getSubmenuList();
	    	return Util.constructJSON(Constants.MENU_SERVICE_GET_MENU_DAO_ALIAS, true, submenu);
	    	
	   }
	    
	    @Path("/getsubmenu/{id}")  
	    @POST
	    @Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Loggable(message=Constants.MENU_SERVICE_GET_MENU_LOG_ALIAS)
	    @HandleException(handleExcpetion=Constants.MENU_SERVICE_GET_MENU_LOG_ALIAS)
	    public JSONObject getSubmenu(@PathParam("id") int submenuID)throws BusinessException{
		   
	    	if(Util.isNotNullOREmpty(Integer.toString(submenuID))){
	    	List<MenuPojo> submenu= menuDAO.getSubmenu(submenuID);
	    	return Util.constructJSON(Constants.MENU_SERVICE_GET_MENU_DAO_ALIAS, true, submenu);
	    	}
	    	else{
	    		throw new BusinessException(Constants.INVALID_PARAMETERS);
	    	}
	   }

}
