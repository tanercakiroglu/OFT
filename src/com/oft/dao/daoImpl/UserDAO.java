package com.oft.dao.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.oft.dao.BaseJdbcDAO;
import com.oft.dao.idao.IUserDAO;
import com.oft.excpetion.BusinessException;
import com.oft.pojo.UserPojo;


public class UserDAO extends BaseJdbcDAO implements IUserDAO {

	 private final RowMapper<UserPojo> rowMapperUser = new RowMapper<UserPojo>() {
	     
			@Override
			public UserPojo mapRow(ResultSet rs, int rowNumber) throws SQLException {
				UserPojo user  = new UserPojo(); 
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("pass_word"));
				user.setRoomNO(rs.getInt("room_no"));
				return user;
			}
	    };
	
	@Override
	public int  register(UserPojo user) {
		int rowAffected =0;
		String query = "INSERT INTO OFT_USER(ROOM_NO,NAME,MIDDLE_NAME,LAST_NAME,USER_NAME,PASS_WORD) VALUES(:roomNO,:name,:middleName,:lastName,:userName,:password)";
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("roomNO",user.getRoomNO());
		namedParameters.put("name", user.getName());
		namedParameters.put("middleName", user.getMiddleName());
		namedParameters.put("lastName", user.getLastName());
		namedParameters.put("userName", user.getUserName());
		namedParameters.put("password", user.getPassword());
		rowAffected= namedParameterJdbcTemplate.update(query, namedParameters);		
		return rowAffected;

	}

	
	
	@Override
	public List<UserPojo> login(String username, String password) throws BusinessException {
		
		String query = "select * from oft_user where user_name=:name and pass_word=:pass ";
		Map<String,Object> parameters= new HashMap<String, Object>();
		parameters.put("name",username);
		parameters.put("pass", password);
			
		return  namedParameterJdbcTemplate.query(query, parameters,rowMapperUser);
		
	}

}
