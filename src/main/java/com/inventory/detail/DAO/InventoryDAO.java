package com.inventory.detail.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.inventory.detail.exception.InventoryNotFoundException;
import com.inventory.detail.model.Inventory;
import com.inventory.detail.model.Laptop;

@Repository
public class InventoryDAO {

	public List<Inventory> getInventoryDetail(String type) {

		List<Inventory> inventoryList = new ArrayList<>();
		Laptop laptop = new Laptop();
		laptop.setType("laptop");
		laptop.setName("DELL");
		laptop.setModel("Inspiron N5110");
		laptop.setPrice(63000.00);

		inventoryList.add(laptop);
		return inventoryList;
	}

	public Inventory getItem(String type, int id) {
		return null;
	}

	public Inventory saveItem(Inventory inventory) {
		return inventory;
	}

	public Inventory updateItem(Inventory inventory, int id) throws InventoryNotFoundException {
		/*
		 * return laptopRepo.findById(id).map(l -> { l.setModel(inventory.getModel());
		 * l.setName(inventory.getName()); l.setPrice(inventory.getPrice()); return
		 * repository.save(l); }).orElseGet(() -> { return repository.save(inventory);
		 * });
		 */
		return inventory;
	}

	public void deleteItem(String type, int id) throws InventoryNotFoundException {

	}
}
