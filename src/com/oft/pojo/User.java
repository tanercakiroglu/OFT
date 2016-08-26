package com.oft.pojo;

import java.io.Serializable;


public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public User(String name,String username, String password) {
		this.name = name;
		this.uname = username;
		this.pass = password;
	}
	
	public User (){}
	

	public String getName() {
		return name;
	}

 	public void setName(String name) {
		this.name = name;
	}

 		

	public String getUname() {
		return uname;
	}


	public void setUname(String uname) {
		this.uname = uname;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}

	
	String name;
	
	String uname;

	String pass;

}
