package com.oft.dao.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.oft.dao.BaseJdbcDAO;
import com.oft.dao.idao.IOrderDAO;
import com.oft.excpetion.BusinessException;
import com.oft.pojo.MenuPojo;
import com.oft.pojo.OrderDetailPojo;
import com.oft.pojo.OrderPojo;
import com.oft.util.Util;

public class OrderDAO extends BaseJdbcDAO implements IOrderDAO {

	
	private final RowMapper<MenuPojo> rowMapperMenuDetail = new RowMapper<MenuPojo>(){

		@Override
		public MenuPojo mapRow(ResultSet rs, int rowNum) throws SQLException {
			MenuPojo menu = new MenuPojo();
			menu.setDesc(rs.getString("DESCRIPTION"));
			menu.setPrice(rs.getDouble("PRICE_IN_YTL"));
			menu.setName(rs.getString("ITEM_NAME"));
			menu.setPriceEUR(rs.getDouble("PRICE_IN_EURO"));
			menu.setPriceUSD(rs.getDouble("PRICE_IN_DOLAR"));
			menu.setDescEN(rs.getString("DESCRIPTION_EN"));
			menu.setNameEN(rs.getString("ITEM_NAME_EN"));
			menu.setItemCount(rs.getInt("ITEM_COUNT"));
			
			return menu;
		}
		 
	 };
		private final RowMapper<OrderPojo> rowMapperOrder = new RowMapper<OrderPojo>(){

			@Override
			public OrderPojo mapRow(ResultSet rs, int rowNum) throws SQLException {
				OrderPojo order = new OrderPojo();
				order.setID(rs.getString("ID"));
				return order;
			}
		 };
		 
			private final RowMapper<OrderDetailPojo> rowMapperOrderDetail = new RowMapper<OrderDetailPojo>(){

				@Override
				public OrderDetailPojo mapRow(ResultSet rs, int rowNum) throws SQLException {
					OrderDetailPojo orderDetail = new OrderDetailPojo();
					orderDetail.setOrderID(rs.getString("ID"));
					orderDetail.setItemCount(rs.getInt("ITEM_COUNT"));
					orderDetail.setItemID(rs.getInt("ITEM_ID"));
					return orderDetail;
				}
			 };
	
	@Override
	public int addOrderDetail(List<OrderPojo> orderList,String orderID) {
		
		int isRowAffected=0;
		for (OrderPojo order : orderList) {
		String query = "INSERT INTO OFT_ORDER_DETAIL (ORDER_ID,ITEM_ID,ITEM_COUNT) VALUES (:orderID,:itemID,:itemCount)";
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("itemID",order.getItemID());
		namedParameters.put("itemCount",order.getItemCount());
		namedParameters.put("orderID",orderID);
		namedParameterJdbcTemplate.update(query, namedParameters);		
		isRowAffected++;
		} 
		return isRowAffected;
	}


	@Override
	public List<MenuPojo> getOrder(int roomNO) {
	
		String query = "SELECT  oft.item_count, me.ITEM_NAME_EN,  me.ITEM_NAME,  me.PRICE_IN_DOLAR,  me.PRICE_IN_YTL,  me.PRICE_IN_EURO,"
				+ " me.DESCRIPTION,  me.DESCRIPTION_EN FROM OFT_ORDER o,  menu me,  oft_order_detail oft " 
				+ " WHERE  oft.ORDER_ID=o.ID " 
				+ " AND oft.order_id = (SELECT id FROM oft_order WHERE room_no=:roomNO AND session_status=:sessionStatus )"
				+ " AND me.ID=oft.ITEM_ID";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("roomNO", roomNO);
		parameters.put("sessionStatus", 1);
		List<MenuPojo> orderDetail = namedParameterJdbcTemplate.query(query,parameters, rowMapperMenuDetail);
		return orderDetail;
	}
	

	@Override
	public int closeOrder(int roomNO) {
		
		int rowAffected =0;
		String query = "UPDATE OFT_ORDER set session_Status=:sessionStatus, order_session_closed_date=:timestamp  where ROOM_NO=:roomNO";
		Map<String,Object> parameters= new HashMap<String, Object>();
		parameters.put("roomNO",roomNO);
		parameters.put("timestamp", Util.getCurrentTimeStamp());
		parameters.put("sessionStatus",0);
		rowAffected=namedParameterJdbcTemplate.update(query, parameters);
		return rowAffected;
	}
	
	public OrderPojo controlExistingOpenOrder(int roomNO) throws BusinessException{
	
		String query = "SELECT * from OFT_ORDER WHERE ROOM_NO=:roomNO and SESSION_STATUS=:sessionStatus";
		Map<String,Object> parameters= new HashMap<String, Object>();
		parameters.put("roomNO",roomNO);
		parameters.put("sessionStatus", 1);
			
		List<OrderPojo> order =  namedParameterJdbcTemplate.query(query, parameters,rowMapperOrder);
		if(order!=null && order.size()==1)
			return order.get(0);
		else if(order==null || order.size()==0)
			return null;
		else
			throw new BusinessException("hacı database karışık soyleyim");
	}

	@Override
	public int addOrder(int roomNO) {
	
		int rowAffected =0;
		String query = "INSERT INTO  OFT_ORDER (ROOM_NO) VALUES(:roomNO)";
		Map<String,Object> parameters= new HashMap<String, Object>();
		parameters.put("roomNO",roomNO);
		rowAffected=namedParameterJdbcTemplate.update(query, parameters);
		return rowAffected;
	}


	@Override
	public int controlDeleteCount(int itemID, String orderID) throws BusinessException {
		
		String query = "select * from oft_order_detail where item_id =:itemID and order_id=:orderID";
		Map<String,Object> parameters= new HashMap<String, Object>();
		System.out.println(itemID + Integer.parseInt(orderID));
		parameters.put("itemID",itemID);
		parameters.put("orderID", Integer.parseInt(orderID));
		List<OrderDetailPojo> orderDetail =  namedParameterJdbcTemplate.query(query, parameters,rowMapperOrderDetail);
		if(orderDetail!=null && orderDetail.size()==1)
			return orderDetail.get(0).getItemCount();
		else
			throw new BusinessException("hacı database karışık soyleyim");
	}
	
	@Override
	public int deleteOrderDetail(int itemID,String orderID) {
		
		int rowAffected = 0;
		String query = "DELETE FROM OFT_ORDER_DETAIL WHERE ORDER_ID=:orderID and ITEM_ID=:itemID";
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("orderID",orderID);
		namedParameters.put("itemID", itemID);
		rowAffected=namedParameterJdbcTemplate.update(query, namedParameters);
		return rowAffected ;
	}
	
	@Override
	public int decreaseItemCount(int itemID, String orderID) {
		
		int rowAffected = 0;
		String query = "UPDATE  OFT_ORDER_DETAIL SET ITEM_COUNT=ITEM_COUNT-1 WHERE ORDER_ID=:orderID and ITEM_ID=:itemID";
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("orderID",orderID);
		namedParameters.put("itemID", itemID);
		rowAffected=namedParameterJdbcTemplate.update(query, namedParameters);
		return rowAffected ;
	}
	
	
}



