package com.inventory.detail.controller;

import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.detail.constants.IDSConstants;
import com.inventory.detail.model.CreateUserRequest;
import com.inventory.detail.model.CreateUserResponse;
import com.inventory.detail.model.User;
import com.inventory.detail.model.UserResponse;
import com.inventory.detail.service.UserService;

import io.micrometer.common.util.StringUtils;

@RestController
@Produces(MediaType.APPLICATION_JSON_VALUE)
@Consumes(MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<UserResponse> getUserByPage(@RequestParam String page) {
		UserResponse response = new UserResponse();

		try {
			response.setUser(service.getAllUsers(page));
			response.setResponseCode(IDSConstants.SUCCESS_CODE);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {

		}
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserResponse> getUserDetail(@PathVariable String id) {
		UserResponse response = new UserResponse();

		try {
			response.setUser(service.getUserDetail(id));
			response.setResponseCode(IDSConstants.SUCCESS_CODE);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {

		}
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping()
	public ResponseEntity<CreateUserResponse> registerUser(@RequestBody CreateUserRequest request) {
		CreateUserResponse response = new CreateUserResponse();
		try {
			response  = service.createUser(request);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {

		}
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping()
	public ResponseEntity<UserResponse> updateUserDetails(@RequestBody User user) {
		UserResponse response = new UserResponse();

		try {
			//response.setUser(service.updateUserInfo(user));
			response.setResponseCode(IDSConstants.SUCCESS_CODE);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {

		}
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/login")
	public ResponseEntity<UserResponse> login(@RequestHeader Map<String, String> headerMap){
		UserResponse response = new UserResponse();

		try {
			
			String username = headerMap.get("username");
			String password = headerMap.get("password");
			
			if(StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
				response.setResponseCode(IDSConstants.ERROR_CODE);
				response.setResponseMsg("Username or Password cannot be empty");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
			
			//response.setUser(service.validateLogin(username, password) );
			response.setResponseCode(IDSConstants.SUCCESS_CODE);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {

		}
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}
