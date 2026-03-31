package com.inventory.detail.service;

import java.util.List;

import com.inventory.detail.model.User;

public interface UserService {
	
	public List<User> getUsers();

	public List<User> getUserById(String id);
	
	public List<User> saveUser(User user);
	
	public List<User> updateUser(User user);
	
}
