package com.oft.dao.idao;

import java.util.List;

import com.oft.excpetion.BusinessException;
import com.oft.pojo.UserPojo;


public interface IUserDAO {

	List<UserPojo> login(String username,String password) throws BusinessException;
	
	int register(UserPojo user)throws BusinessException ;
 
}
