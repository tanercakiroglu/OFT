package com.oft.dao.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import com.oft.aspect.exceptionhandler.HandleException;
import com.oft.dao.BaseJdbcDAO;
import com.oft.dao.idao.IOrderDAO;
import com.oft.pojo.Menu;
import com.oft.pojo.Order;
import com.oft.util.Constants;
import com.oft.util.Util;

public class OrderDAO extends BaseJdbcDAO implements IOrderDAO {

	
	private final RowMapper<Menu> rowMapperOrder = new RowMapper<Menu>(){

		@Override
		public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
			Menu menu = new Menu();
			menu.setDesc(rs.getString("DESCRIPTION"));
			menu.setPrice(rs.getDouble("PRICE_IN_YTL"));
			menu.setName(rs.getString("ITEM_NAME"));
			menu.setPriceEUR(rs.getDouble("PRICE_IN_EURO"));
			menu.setPriceUSD(rs.getDouble("PRICE_IN_DOLAR"));
			menu.setDescEN(rs.getString("DESCRIPTION_EN"));
			menu.setNameEN(rs.getString("ITEM_NAME_EN"));
			
			return menu;
		}
		 
	 };
	
	
	
	@HandleException(handleExcpetion=Constants.ORDER_SERVICE_GET_ORDER_DAO_ALIAS)
	@Override
	public JSONObject addOrder(List<Order> orderList) {
		
		int isRowAffected=0;
		
		for (Order order : orderList) {
			String query = "INSERT INTO OFT_ORDER (ROOM_NO,ITEM_ID) VALUES (:roomNO,:itemID)";
			Map<String, Object> namedParameters = new HashMap<String, Object>();
			namedParameters.put("roomNO",order.getRoomNO());
			namedParameters.put("itemID", order.getItemID());
			isRowAffected=namedParameterJdbcTemplate.update(query, namedParameters);		
		} 
			
		return Util.constructJSON(Constants.ORDER_SERVICE_GET_ORDER_DAO_ALIAS, true,isRowAffected);
	}

	@HandleException(handleExcpetion=Constants.ORDER_SERVICE_GET_ORDER_DAO_ALIAS)
	@Override
	public JSONObject deleteOrder(int itemID,int roomID) {
	
		int isRowAffected=0;
		String query = "DELETE FROM OFT_ORDER WHERE ROOM_NO=:roomNO and ITEM_ID=:itemID and SESSION_STATUS=:sessionStatus";
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("roomNO",roomID);
		namedParameters.put("itemID", itemID);
		namedParameters.put("sessionStatus", 1);
		isRowAffected=namedParameterJdbcTemplate.update(query, namedParameters);
		return Util.constructJSON(Constants.ORDER_SERVICE_GET_ORDER_DAO_ALIAS, true,isRowAffected);
	}

	@HandleException(handleExcpetion=Constants.ORDER_SERVICE_GET_ORDER_DAO_ALIAS)
	@Override
	public JSONObject getOrder(int roomNO) {
		// ; 
		String query = "SELECT  me.ITEM_NAME_EN, me.ITEM_NAME,me.PRICE_IN_DOLAR,me.PRICE_IN_YTL,me.PRICE_IN_EURO,me.DESCRIPTION,me.DESCRIPTION_EN   FROM OFT_ORDER o,menu me where me.ID=o.ITEM_ID and o.ROOM_NO=:roomNO and o.SESSION_STATUS=:sessionStatus";
		Map<String,Object> parameters= new HashMap<String, Object>();
		parameters.put("roomNO",roomNO);
		parameters.put("sessionStatus", 1);
			
		List<Menu> userFromDB =  namedParameterJdbcTemplate.query(query, parameters,rowMapperOrder);
	    return Util.constructJSON(Constants.ORDER_SERVICE_GET_ORDER_DAO_ALIAS, true, userFromDB);
	}



}
