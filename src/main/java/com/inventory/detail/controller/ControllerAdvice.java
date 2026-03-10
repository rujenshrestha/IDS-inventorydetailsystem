package com.inventory.detail.controller;

import static com.inventory.detail.constants.IDSConstants.INV_NOT_FOUND_CODE;
import static com.inventory.detail.constants.IDSConstants.SERVER_ERROR;
import static com.inventory.detail.constants.IDSConstants.SERVER_ERROR_CODE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.inventory.detail.exception.InventoryNotFoundException;
import com.inventory.detail.model.InventoryResponse;

@RestControllerAdvice
public class ControllerAdvice {

	private static Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);
	
	@ExceptionHandler(InventoryNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	ResponseEntity<InventoryResponse> inventoryNotFoundExceptionHandler(InventoryNotFoundException ex) {
		logger.error(ex.getMessage());
		return new ResponseEntity<>(getInventoryNotFoundErrorResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	ResponseEntity<InventoryResponse> serverErrorHandler(Exception e) {
		logger.error(e.getMessage());
		return new ResponseEntity<>(getErrorResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private InventoryResponse getInventoryNotFoundErrorResponse(String errorMsg) {
		InventoryResponse response = new InventoryResponse();
		response.setResponseCode(INV_NOT_FOUND_CODE);
		response.setResponseMsg(errorMsg);
		return response;
	}

	private InventoryResponse getErrorResponse() {
		InventoryResponse response = new InventoryResponse();
		response.setResponseCode(SERVER_ERROR_CODE);
		String errorMsg = SERVER_ERROR;
		response.setResponseMsg(errorMsg);
		return response;
	}
}
