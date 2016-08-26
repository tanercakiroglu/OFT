package com.oft.dao.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import com.oft.dao.BaseJdbcDAO;
import com.oft.dao.idao.IUserDAO;
import com.oft.pojo.User;
import com.oft.util.Util;


public class UserDAO extends BaseJdbcDAO implements IUserDAO {

	 private final RowMapper<User> rowMapperUser = new RowMapper<User>() {
	     
			@Override
			public User mapRow(ResultSet rs, int rowNumber) throws SQLException {
				User user  = new User(); 
				user.setName(rs.getString("NAME"));
				user.setUname(rs.getString("UNAME"));
				user.setPass(rs.getString("PASS"));
				return user;
			}
	    };
	
	public JSONObject getUser(User user) {

		String query = "select * from userinfo where name=:name and uname=:uname and pass=:pass ";
		Map<String,Object> parameters= new HashMap<String, Object>();
		parameters.put("name",user.getName());
		parameters.put("uname", user.getUname());
		parameters.put("pass", user.getPass());
		
		try {
			List<User> userFromDB =  namedParameterJdbcTemplate.query(query, parameters,rowMapperUser);
			return Util.constructJSON("ok", true, userFromDB);
		} catch (Exception ex) {
			ex.printStackTrace();
			Util.constructJSON("fail", false, ex.getStackTrace().toString());
		}
		
		return null;
	}

}
