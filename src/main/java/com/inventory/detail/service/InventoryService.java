package com.inventory.detail.service;

import java.util.List;

import com.inventory.detail.exception.InventoryNotFoundException;
import com.inventory.detail.model.Inventory;

public interface InventoryService {

	public List<Inventory> getSelectedInventoryTypeDetails(String type) throws Exception;

	public Inventory getSelectedItemDetail(String type, int id) throws InventoryNotFoundException, Exception;

	public void saveItemDetail(Inventory inventory) throws Exception;

	public void updateItemDetail(Inventory inventory, String type, int id) throws InventoryNotFoundException, Exception;

	public void deleteItemDetail(String type, int id) throws InventoryNotFoundException, Exception;

}
