package com.inventory.detail.model;

import java.util.ArrayList;
import java.util.List;

public class InventoryResponse {

	private List<Inventory> inventoryList = new ArrayList<Inventory>();

	private String responseCode;
	private String responseMsg;

	public List<Inventory> getInventoryList() {
		return inventoryList;
	}

	public void setInventoryList(List<Inventory> inventoryList) {
		this.inventoryList = inventoryList;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

}
