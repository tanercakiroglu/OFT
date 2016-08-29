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
import com.oft.dao.idao.IUserDAO;
import com.oft.excpetion.BusinessException;
import com.oft.pojo.User;
import com.oft.util.Constants;
import com.oft.util.Util;


public class UserDAO extends BaseJdbcDAO implements IUserDAO {

	 private final RowMapper<User> rowMapperUser = new RowMapper<User>() {
	     
			@Override
			public User mapRow(ResultSet rs, int rowNumber) throws SQLException {
				User user  = new User(); 
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("pass_word"));
				user.setRoomNO(rs.getInt("room_no"));
				return user;
			}
	    };
	
	@HandleException(handleExcpetion=Constants.USER_SERVICE_GET_USER_DAO_ALIAS)
	@Override
	public JSONObject register(User user) {
		String query = "INSERT INTO OFT_USER(ROOM_NO,NAME,MIDDLE_NAME,LAST_NAME,USER_NAME,PASS_WORD) VALUES(:roomNO,:name,:middleName,:lastName,:userName,:password)";
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("roomNO",user.getRoomNO());
		namedParameters.put("name", user.getName());
		namedParameters.put("middleName", user.getMiddleName());
		namedParameters.put("lastName", user.getLastName());
		namedParameters.put("userName", user.getUserName());
		namedParameters.put("password", user.getPassword());
				
		return Util.constructJSON(Constants.USER_SERVICE_GET_USER_DAO_ALIAS, true, namedParameterJdbcTemplate.update(query, namedParameters));

	}

	
	@HandleException(handleExcpetion=Constants.USER_SERVICE_GET_USER_DAO_ALIAS)
	@Override
	public JSONObject login(String username, String password) throws BusinessException {
		String query = "select * from oft_user where user_name=:name and pass_word=:pass ";
		Map<String,Object> parameters= new HashMap<String, Object>();
		parameters.put("name",username);
		parameters.put("pass", password);
			
		List<User> userFromDB =  namedParameterJdbcTemplate.query(query, parameters,rowMapperUser);
		if(userFromDB!= null && userFromDB.size()==1){
		return Util.constructJSON(Constants.USER_SERVICE_GET_USER_DAO_ALIAS, true, userFromDB);
		}
		else{
	     throw new BusinessException(Constants.LOGIN_FAILED);	
		}
	}

}
