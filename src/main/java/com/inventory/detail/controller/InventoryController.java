package com.inventory.detail.controller;

import static com.inventory.detail.constants.IDSConstants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.detail.exception.InventoryNotFoundException;
import com.inventory.detail.model.InventoryResponse;
import com.inventory.detail.model.Laptop;
import com.inventory.detail.service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	private InventoryService service;

	@GetMapping("/{type}")
	public ResponseEntity<InventoryResponse> getAll(@PathVariable String type) throws Exception {
		InventoryResponse response = new InventoryResponse();
		response.setInventoryList(service.getSelectedInventoryTypeDetails(type));
		response.setResponseCode(SUCCESS_CODE);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	
	@GetMapping("/{type}/{id}")
	public ResponseEntity<InventoryResponse> findById(@PathVariable String type, @PathVariable int id)
			throws InventoryNotFoundException, Exception {
		InventoryResponse response = new InventoryResponse();
		response.getInventoryList().add(service.getSelectedItemDetail(type, id));
		response.setResponseCode(SUCCESS_CODE);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	
	@PostMapping
	public ResponseEntity<InventoryResponse> save(@RequestBody Laptop laptop) throws Exception {
		InventoryResponse response = new InventoryResponse();
		service.saveItemDetail(laptop);
		response.setResponseCode(SAVE_CODE);
		response.setResponseMsg(INV_SAVED_MSG);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	

	@PutMapping("/{type}/{id}")
	public ResponseEntity<InventoryResponse> update(@RequestBody Laptop laptop, @PathVariable String type,
			@PathVariable int id) throws InventoryNotFoundException, Exception {
		InventoryResponse response = new InventoryResponse();
		service.updateItemDetail(laptop, type, id);
		response.setResponseCode(UPDATE_CODE);
		response.setResponseMsg(INV_UPDATED_MSG);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	
	@DeleteMapping("/{type}/{id}")
	public ResponseEntity<InventoryResponse> delete(@PathVariable String type, @PathVariable int id)
			throws InventoryNotFoundException, Exception {
		InventoryResponse response = new InventoryResponse();

		service.deleteItemDetail(type, id);
		response.setResponseCode(DELETE_CODE);
		response.setResponseMsg(INV_DELETED_MSG);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
