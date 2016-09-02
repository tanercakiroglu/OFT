package com.oft.dao.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.oft.dao.BaseJdbcDAO;
import com.oft.dao.idao.IMenuDAO;
import com.oft.pojo.MenuPojo;
import com.oft.pojo.SubmenuPojo;

public class MenuDAO extends BaseJdbcDAO implements IMenuDAO {

	 private final RowMapper<MenuPojo> rowMapperMenu = new RowMapper<MenuPojo>(){

		@Override
		public MenuPojo mapRow(ResultSet rs, int rowNum) throws SQLException {
			MenuPojo menu = new MenuPojo();
			menu.setDesc(rs.getString("DESCRIPTION"));
			menu.setPrice(rs.getDouble("PRICE_IN_YTL"));
			menu.setName(rs.getString("ITEM_NAME"));
			menu.setPriceEUR(rs.getDouble("PRICE_IN_EUR"));
			menu.setPriceUSD(rs.getDouble("PRICE_IN_USD"));
			menu.setDescEN(rs.getString("DESCRIPTION_EN"));
			menu.setNameEN(rs.getString("ITEM_NAME_EN"));
			menu.setSubmenu(rs.getString("SUBMENU"));
			menu.setID(rs.getString("ID"));
			
			return menu;
		}
		 
	 };
	 
	 private final RowMapper<SubmenuPojo> rowMapperSubMenu = new RowMapper<SubmenuPojo>(){

			@Override
			public SubmenuPojo mapRow(ResultSet rs, int rowNum) throws SQLException {
				SubmenuPojo submenu = new SubmenuPojo();

				submenu.setId(rs.getString("id"));
				submenu.setName(rs.getString("NAME"));
				
				return submenu;
			}
			 
		 };
	
	@Override
	public List<MenuPojo> getMenu() {
	
		String query = "select  submenu_t.NAME as SUBMENU,menu_t.ID,menu_t.ITEM_NAME,menu_t.ITEM_NAME_EN,menu_t.DESCRIPTION_EN,menu_t.PRICE_IN_YTL,menu_t.PRICE_IN_USD,menu_t.PRICE_IN_EUR,menu_t.DESCRIPTION from menu menu_t ,submenu submenu_t where menu_t.SUBMENU_ID=submenu_t.ID";
		return  jdbcTemplate.query(query,rowMapperMenu);
	}
	
	@Override
	public List<MenuPojo> getSubmenu(int submenuID) {
	    String query = "select  submenu_t.NAME as SUBMENU,menu_t.ID,menu_t.ITEM_NAME,menu_t.ITEM_NAME_EN,menu_t.DESCRIPTION_EN,menu_t.PRICE_IN_YTL,menu_t.PRICE_IN_USD,menu_t.PRICE_IN_EUR,menu_t.DESCRIPTION from menu menu_t ,submenu submenu_t where menu_t.SUBMENU_ID=submenu_t.ID and submenu_t.ID=:submenuID";
		Map<String,Object> parameters= new HashMap<String, Object>();
		parameters.put("submenuID",submenuID);
					
		return namedParameterJdbcTemplate.query(query, parameters,rowMapperMenu);
		
	}

	@Override
	public List<SubmenuPojo> getSubmenuList() {
		String query="select * from submenu";
		return  jdbcTemplate.query(query,rowMapperSubMenu);
	}
	
	
	
}
