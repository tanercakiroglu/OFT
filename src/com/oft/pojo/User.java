package com.oft.pojo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
		

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getRoomNO() {
		return roomNO;
	}

	public void setRoomNO(int roomNO) {
		this.roomNO = roomNO;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

 	public void setName(String name) {
		this.name = name;
	}

 		
	
	String name;
	
	String middleName;
	
	String lastName;
	
	String userName;
	
	int roomNO;

	String password;
	

}
