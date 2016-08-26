package com.oft.dao.idao;

import org.codehaus.jettison.json.JSONObject;

import com.oft.pojo.User;


public interface IUserDAO {

	JSONObject getUser(User user);

 
}
