package com.inventory.detail.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.detail.exception.InventoryNotFoundException;
import com.inventory.detail.model.Inventory;
import com.inventory.detail.model.Laptop;
import com.inventory.detail.repository.LaptopRepository;
import com.inventory.detail.service.InventoryService;
import com.inventory.detail.service.UserService;

@Service(value = "InventoryService")
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private LaptopRepository repo;

	@Autowired
	UserService userService;
	

	@Override
	public List<Inventory> getSelectedInventoryTypeDetails(String type) throws Exception {
		List<Inventory> inventoryList = new ArrayList<>((List<Laptop>) repo.findAll());
		return inventoryList;
	}

	@Override
	public Inventory getSelectedItemDetail(String type, int id) throws InventoryNotFoundException, Exception {
		Laptop laptop = repo.findById(id).orElse(null);
		if (laptop == null)
			throw new InventoryNotFoundException(id, type);

		return laptop;
	}

	@Override
	public void saveItemDetail(Inventory inventory) throws Exception {
		repo.save((Laptop) inventory);
	}

	@Override
	public void updateItemDetail(Inventory inventory, String type, int id)
			throws InventoryNotFoundException, Exception {
		Laptop laptop = (Laptop) getSelectedItemDetail(type, id);
		Laptop newLaptop = (Laptop) inventory;
		laptop.setName(newLaptop.getName());
		laptop.setModel(newLaptop.getModel());
		laptop.setPrice(newLaptop.getPrice());
		repo.save(laptop);
	}

	@Override
	public void deleteItemDetail(String type, int id) throws InventoryNotFoundException, Exception {
		getSelectedItemDetail(type, id);
		repo.deleteById(id);
	}

}
