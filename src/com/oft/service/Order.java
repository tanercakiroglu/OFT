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

import com.oft.aspect.logger.Loggable;
import com.oft.dao.idao.IOrderDAO;

@Path("/order")
public class Order {
	
		@Autowired
		private IOrderDAO orderDAO;
	
	    @Path("/give")  
	    @POST
	    @Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Loggable(message="/ORDER/GIVEORDER")
		public JSONObject addOrder(List<com.oft.pojo.Order> list){
				    	
		    System.out.println(list.toString());
			return null ;
			
		}
	    
	    @Path("/get/{roomNO}")  
	    @POST
	    @Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Loggable(message="/ORDER/GIVEORDER")
		public JSONObject getOrder(@PathParam("roomNO") int roomNO){
				    	
			return orderDAO.getOrder(roomNO) ;
		}
	    
	    @Path("/delete/{itemID}/{roomNO}")  
	    @POST
	    @Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Loggable(message="/ORDER/GIVEORDER")
		public JSONObject deleteOrder(@PathParam("roomNO") int roomID,@PathParam("itemID") int itemID){
	    	
			return orderDAO.deleteOrder(itemID, roomID) ;
			
		}

}
