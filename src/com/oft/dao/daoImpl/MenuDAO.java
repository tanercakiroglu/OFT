package com.oft.dao.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import com.oft.dao.BaseJdbcDAO;
import com.oft.dao.idao.IMenuDAO;
import com.oft.pojo.Menu;
import com.oft.util.Util;

public class MenuDAO extends BaseJdbcDAO implements IMenuDAO {

	 private final RowMapper<Menu> rowMapperUser = new RowMapper<Menu>(){

		@Override
		public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
			Menu menu = new Menu();
			menu.setDesc(rs.getString("DESCRIPTION"));
			menu.setPrice(rs.getDouble("PRICE_IN_YTL"));
			menu.setName(rs.getString("ITEM_NAME"));
			menu.setSubmenu(rs.getString("SUBMENU"));
			
			return menu;
		}
		 
	 };
	
	
	@Override
	public JSONObject getMenu() {
		String query = "select  submenu_t.NAME as SUBMENU,menu_t.ITEM_NAME,menu_t.PRICE_IN_YTL,menu_t.DESCRIPTION from menu menu_t ,submenu submenu_t where menu_t.SUBMENU_ID=submenu_t.ID";
				
		try {
			List<Menu> menu =  jdbcTemplate.query(query,rowMapperUser);
			return Util.constructJSON("ok", true, menu);
		} catch (Exception ex) {
			ex.printStackTrace();
			return Util.constructJSON("fail", false, ex.getStackTrace().toString());
		}
		
	}

}