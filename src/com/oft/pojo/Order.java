package com.oft.pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
	
	
	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getRoomNO() {
		return roomNO;
	}

	public void setRoomNO(String roomNO) {
		this.roomNO = roomNO;
	}

	private String itemID;
	
	private String roomNO;
	
	

}
