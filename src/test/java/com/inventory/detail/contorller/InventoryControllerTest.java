package com.inventory.detail.contorller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.inventory.detail.controller.InventoryController;
import com.inventory.detail.model.Inventory;
import com.inventory.detail.model.Laptop;
import com.inventory.detail.service.InventoryService;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(controllers = InventoryController.class)
public class InventoryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private InventoryService service;

	@InjectMocks
	private InventoryController controllerUnderTest;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controllerUnderTest).build();
	}

	@Test
	public void testGetAll() throws Exception {
		when(service.getSelectedInventoryTypeDetails(anyString())).thenReturn(mockInventory());
		mockMvc.perform(get("/inventory/laptop").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.inventoryList[0].name").value("DELL"));
		verify(service).getSelectedInventoryTypeDetails("laptop");
	}
	
	@Test
	public void testFindById() throws Exception {
		when(service.getSelectedItemDetail(anyString(), anyLong())).thenReturn(mockInventory().get(0));
		mockMvc.perform(get("/inventory/laptop/1").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.inventoryList[0].name").value("DELL"));
		verify(service).getSelectedItemDetail("laptop", 1L);
	}

	private List<Inventory> mockInventory() {
		List<Inventory> inventoryList = new ArrayList<>();
		Inventory inventory = new Laptop();
		inventory.setId(1L);
		inventory.setName("DELL");
		inventory.setPrice(799.99);
		inventoryList.add(inventory);
		return inventoryList;
	}

}
