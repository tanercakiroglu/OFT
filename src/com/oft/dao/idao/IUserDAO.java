package com.oft.dao.idao;

import java.util.List;

import com.oft.excpetion.BusinessException;
import com.oft.pojo.UserPojo;


public interface IUserDAO {

	List<UserPojo> login(String username,String password,String roomNO) throws BusinessException;
	
	int register(UserPojo user)throws BusinessException ;
	
	int asda();
 
}
