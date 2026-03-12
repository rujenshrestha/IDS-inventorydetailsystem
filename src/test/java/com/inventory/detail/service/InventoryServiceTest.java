package com.inventory.detail.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.inventory.detail.exception.InventoryNotFoundException;
import com.inventory.detail.model.Inventory;
import com.inventory.detail.model.Laptop;
import com.inventory.detail.repository.LaptopRepository;
import com.inventory.detail.service.impl.InventoryServiceImpl;

public class InventoryServiceTest {

	private InventoryService serviceToTest;
	private LaptopRepository repo;
	private final String TYPE = "type";
	private final Long inventoryID = 1L;

	@BeforeEach
	public void setup() {
		serviceToTest = new InventoryServiceImpl();
		repo = EasyMock.createMock(LaptopRepository.class);
		ReflectionTestUtils.setField(serviceToTest, "repo", repo);
	}

	@Test
	public void testGetSelectedInventoryTypeDetails() throws Exception {
		EasyMock.expect(repo.findAll())
				.andReturn(getInventoryList())
				.times(1);
		EasyMock.replay(repo);
		List<Inventory> inventoryList = serviceToTest.getSelectedInventoryTypeDetails(TYPE);
		assertNotNull(inventoryList);
		EasyMock.verify(repo);
	}

	@Test
	public void testGetSelectedItemDetail() throws Exception {
		EasyMock.expect(repo.findById(EasyMock.anyLong()))
				.andReturn(Optional.of(getInventoryList().get(0)))
				.times(1);
		EasyMock.replay(repo);
		Inventory inventory = serviceToTest.getSelectedItemDetail(TYPE, inventoryID);
		assertNotNull(inventory);
		assertTrue(inventory.getName().equals("Acer Predator"));
		EasyMock.verify(repo);
	}

	@Test
	public void testGetSelectedItemDetail_NoInventoryFoundExcception() throws Exception {
		EasyMock.expect(repo.findById(EasyMock.anyLong()))
				.andReturn(Optional.empty())
				.times(1);
		EasyMock.replay(repo);
		assertThrows(InventoryNotFoundException.class, () -> {
			serviceToTest.getSelectedItemDetail(TYPE, inventoryID);
		});
		EasyMock.verify(repo);
	}

	@Test
	public void testSaveItemDetail() throws Exception {
		EasyMock.expect(repo.save(EasyMock.anyObject(Laptop.class)))
				.andReturn(getInventoryList().get(0))
				.times(1);
		EasyMock.replay(repo);
		serviceToTest.saveItemDetail(getInventoryList().get(0));
		EasyMock.verify(repo);
	}

	@Test
	public void testUpdateItemDetail() throws Exception {
		EasyMock.expect(repo.findById(EasyMock.anyLong()))
				.andReturn(Optional.of(getInventoryList().get(0)))
				.times(1);
		EasyMock.expect(repo.save(EasyMock.anyObject(Laptop.class)))
				.andReturn(getInventoryList().get(0))
				.times(1);
		EasyMock.replay(repo);
		serviceToTest.updateItemDetail(getInventoryList().get(0), TYPE, inventoryID);
		EasyMock.verify(repo);
	}

	@Test
	public void testDeleteItemDetail() throws Exception {
		EasyMock.expect(repo.findById(EasyMock.anyLong()))
				.andReturn(Optional.of(getInventoryList().get(0)))
				.times(1);
		repo.deleteById(EasyMock.anyLong());
		EasyMock.expectLastCall()
			.times(1);
		EasyMock.replay(repo);
		serviceToTest.deleteItemDetail(TYPE, inventoryID);
		EasyMock.verify(repo);
	}

	private List<Laptop> getInventoryList() {
		List<Laptop> laptopList = new ArrayList<Laptop>();
		Laptop l = new Laptop("Acer Predator", "Laptop", "Acer", 1000.00);
		l.setId(1L);
		laptopList.add(l);
		return laptopList;
	}
}
