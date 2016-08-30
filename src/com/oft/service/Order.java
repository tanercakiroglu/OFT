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
import com.oft.dao.idao.IOrderDAO;
import com.oft.excpetion.BusinessException;
import com.oft.pojo.MenuPojo;
import com.oft.pojo.OrderPojo;
import com.oft.util.Constants;
import com.oft.util.Util;

@Path("/order")
public class Order {
	
		@Autowired
		private IOrderDAO orderDAO;
	
	    @Path("/give/{roomNO}")  
	    @POST
	    @Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Loggable(message="/ORDER/GIVEORDER")
	    @HandleException(handleExcpetion=Constants.ORDER_SERVICE_GET_ORDER_DAO_ALIAS)
		public JSONObject addOrder(List<OrderPojo> list,@PathParam("roomNO") int roomNO) throws BusinessException{
				    	
		if (list != null && list.size() > 0 && Util.isNotNullOREmpty(Integer.toString(roomNO))) {
			 OrderPojo previousOpenOrder = orderDAO.controlExistingOpenOrder(roomNO);
			    if(previousOpenOrder!=null){
				System.out.println(previousOpenOrder.getID());
				return Util.constructJSON(Constants.ORDER_SERVICE_GET_ORDER_DAO_ALIAS, true,orderDAO.addOrderDetail(list,previousOpenOrder.getID()));
			    }else{
				 orderDAO.addOrder(roomNO);
				 OrderPojo previousOpenOrderNew = orderDAO.controlExistingOpenOrder(roomNO);
				 return Util.constructJSON(Constants.ORDER_SERVICE_GET_ORDER_DAO_ALIAS, true,orderDAO.addOrderDetail(list,previousOpenOrderNew.getID()));
			    }
			
		} else {
			throw new BusinessException(Constants.INVALID_PARAMETERS);
		}
			
		}
	    
	    @Path("/get/{roomNO}")  
	    @POST
	    @Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Loggable(message="/ORDER/GIVEORDER")
	    @HandleException(handleExcpetion=Constants.ORDER_SERVICE_GET_ORDER_DAO_ALIAS)
		public JSONObject getOrder(@PathParam("roomNO") int roomNO){
	    	List<MenuPojo>  bill= 	orderDAO.getOrder(roomNO);
			return  Util.constructJSON(Constants.ORDER_SERVICE_GET_ORDER_DAO_ALIAS, true, bill) ;
		}
	    
	    @Path("/delete/{itemID}/{roomNO}")  
	    @POST
	    @Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Loggable(message="/ORDER/GIVEORDER")
	    @HandleException(handleExcpetion=Constants.ORDER_SERVICE_GET_ORDER_DAO_ALIAS)
		public JSONObject deleteOrder(@PathParam("roomNO") int roomNO,@PathParam("itemID") int itemID) throws BusinessException{
	    	
	    int deleteCount=0;	
		OrderPojo previousOpenOrder = orderDAO.controlExistingOpenOrder(roomNO);
		if (previousOpenOrder != null) {
			System.out.println(previousOpenOrder.getID());
			deleteCount=orderDAO.controlDeleteCount(itemID, previousOpenOrder.getID());
			if(deleteCount==1){
			return Util.constructJSON(Constants.ORDER_SERVICE_GET_ORDER_DAO_ALIAS, true,orderDAO.deleteOrderDetail(itemID, previousOpenOrder.getID()));
			}else if(deleteCount==0){
				throw new BusinessException(Constants.DELETE_BUSSINES_EXCEPTION);
			}else{
			return Util.constructJSON(Constants.ORDER_SERVICE_GET_ORDER_DAO_ALIAS, true,orderDAO.decreaseItemCount(itemID, previousOpenOrder.getID()));
			}
		} else {
			throw new BusinessException(Constants.DELETE_BUSSINES_EXCEPTION);
		}	
			
		}

	    @Path("/close/{roomNO}")  
	    @POST
	    @Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Loggable(message="/ORDER/GIVEORDER")
	    @HandleException(handleExcpetion=Constants.ORDER_SERVICE_GET_ORDER_DAO_ALIAS)
		public JSONObject closeOrder(@PathParam("roomNO") int roomNO) {
	    	
	    	return Util.constructJSON(Constants.ORDER_SERVICE_GET_ORDER_DAO_ALIAS, true,orderDAO.closeOrder(roomNO));
	    }
	    	
}
