package com.oft.dao.idao;

import java.util.List;

import org.codehaus.jettison.json.JSONObject;

import com.oft.pojo.Order;

public interface IOrderDAO {

	public JSONObject addOrder(List<Order> orderList);
	public JSONObject deleteOrder(int itemID,int roomID);
	public JSONObject getOrder(int roomNO);
}
