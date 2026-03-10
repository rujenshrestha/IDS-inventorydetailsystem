package com.inventory.detail.service;

import java.util.List;

import com.inventory.detail.model.CreateUserRequest;
import com.inventory.detail.model.CreateUserResponse;
import com.inventory.detail.model.User;

public interface UserService {
	
	public List<User> getAllUsers(String page);

	public List<User> getUserDetail(String id);
	
	public CreateUserResponse createUser(CreateUserRequest request);
	
	public List<User> validateLogin(String username, String password);
	
	public List<User> register(User user);
	
	public List<User> updateUserInfo(User user);
	
}
