package com.inventory.detail.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.detail.constants.IDSConstants;
import com.inventory.detail.model.User;
import com.inventory.detail.model.UserResponse;
import com.inventory.detail.service.UserService;

@RestController
@Produces(MediaType.APPLICATION_JSON_VALUE)
@Consumes(MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<UserResponse> getAllUsers() {
		UserResponse response = new UserResponse();

		try {
			response.setUsers(service.getUsers());
			response.setResponseCode(IDSConstants.SUCCESS_CODE);
			response.setResponseMsg("");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {

		}
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
		UserResponse response = new UserResponse();

		try {
			response.setUsers(service.getUserById(id));
			response.setResponseCode(IDSConstants.SUCCESS_CODE);
			response.setResponseMsg("");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {

		}
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping()
	public ResponseEntity<UserResponse> saveUser(@RequestBody User user) {
		UserResponse response = new UserResponse();
		try {
			response.setUsers(service.saveUser(user));
			response.setResponseCode(IDSConstants.SUCCESS_CODE);
			response.setResponseMsg("");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {

		}
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping()
	public ResponseEntity<UserResponse> updateUserDetails(@RequestBody User user) {
		UserResponse response = new UserResponse();

		try {
			response.setUsers(service.updateUser(user));
			response.setResponseCode(IDSConstants.SUCCESS_CODE);
			response.setResponseMsg("");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {

		}
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}
