package com.inventory.detail.exception;

public class InventoryNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InventoryNotFoundException(Long id, String inventoryType){
		super("NO INVENTORY FOUND OF TYPE "+inventoryType+ " FOR ID: "+ id);
	}
}
