package com.oft.dao.idao;

import java.util.List;

import com.oft.pojo.MenuPojo;
import com.oft.pojo.SubmenuPojo;

public interface IMenuDAO {

	List<MenuPojo> getMenu();

	List<MenuPojo> getSubmenu(int submenuID);

	List<SubmenuPojo> getSubmenuList();
}
