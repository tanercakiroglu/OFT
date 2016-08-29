package com.oft.dao.idao;

import org.codehaus.jettison.json.JSONObject;

public interface IMenuDAO {

	JSONObject getMenu();
	JSONObject getSubmenu(int submenuID);
}
