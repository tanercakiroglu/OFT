package com.oft.dao.idao;

import java.util.List;

import com.oft.pojo.MenuPojo;

public interface IMenuDAO {

	List<MenuPojo> getMenu();
	List<MenuPojo> getSubmenu(int submenuID);
}
