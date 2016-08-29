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
import com.oft.dao.idao.IMenuDAO;
import com.oft.pojo.Menu;
import com.oft.util.Constants;
import com.oft.util.Util;

public class MenuDAO extends BaseJdbcDAO implements IMenuDAO {

	 private final RowMapper<Menu> rowMapperMenu = new RowMapper<Menu>(){

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
			menu.setSubmenu(rs.getString("SUBMENU"));
			menu.setID(rs.getString("ID"));
			
			return menu;
		}
		 
	 };
	
	@HandleException(handleExcpetion=Constants.MENU_SERVICE_GET_MENU_DAO_ALIAS)
	@Override
	public JSONObject getMenu() {
		
		    String query = "select  submenu_t.NAME as SUBMENU,menu_t.ID,menu_t.ITEM_NAME,menu_t.ITEM_NAME_EN,menu_t.DESCRIPTION_EN,menu_t.PRICE_IN_YTL,menu_t.PRICE_IN_DOLAR,menu_t.PRICE_IN_EURO,menu_t.DESCRIPTION from menu menu_t ,submenu submenu_t where menu_t.SUBMENU_ID=submenu_t.ID";
			
		    List<Menu> menu =  jdbcTemplate.query(query,rowMapperMenu);
			return Util.constructJSON(Constants.MENU_SERVICE_GET_MENU_DAO_ALIAS, true, menu);
	}
	
	@HandleException(handleExcpetion=Constants.MENU_SERVICE_GET_MENU_DAO_ALIAS)
	@Override
	public JSONObject getSubmenu(int submenuID) {
	    String query = "select  submenu_t.NAME as SUBMENU,menu_t.ID,menu_t.ITEM_NAME,menu_t.ITEM_NAME_EN,menu_t.DESCRIPTION_EN,menu_t.PRICE_IN_YTL,menu_t.PRICE_IN_DOLAR,menu_t.PRICE_IN_EURO,menu_t.DESCRIPTION from menu menu_t ,submenu submenu_t where menu_t.SUBMENU_ID=submenu_t.ID and submenu_t.ID=:submenuID";
		Map<String,Object> parameters= new HashMap<String, Object>();
		parameters.put("submenuID",submenuID);
					
		List<Menu> submenu =  namedParameterJdbcTemplate.query(query, parameters,rowMapperMenu);
		return Util.constructJSON(Constants.MENU_SERVICE_GET_MENU_DAO_ALIAS, true, submenu);
	}
	
	
	
}
