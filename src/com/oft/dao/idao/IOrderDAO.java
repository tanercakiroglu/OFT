package com.oft.dao.idao;

import java.util.List;

import org.codehaus.jettison.json.JSONObject;

import com.oft.excpetion.BusinessException;
import com.oft.pojo.MenuPojo;
import com.oft.pojo.OrderPojo;

public interface IOrderDAO {

	
	int addOrderDetail(List<OrderPojo> orderList, String orderID);
	int deleteOrderDetail(int itemID,String orderID);
	int controlDeleteCount(int itemID,String orderID);
	int decreaseItemCount(int itemID,String orderID);
	List<MenuPojo> getOrder(int roomNO);
	JSONObject closeOrder(int roomNO);
	OrderPojo controlExistingOpenOrder(int roomNO) throws BusinessException ;
	int addOrder(int roomNO);
}
