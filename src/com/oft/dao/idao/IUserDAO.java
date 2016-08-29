package com.oft.dao.idao;

import org.codehaus.jettison.json.JSONObject;

import com.oft.excpetion.BusinessException;
import com.oft.pojo.User;


public interface IUserDAO {

	JSONObject login(String username,String password) throws BusinessException;
	
	JSONObject register(User user);
 
}
