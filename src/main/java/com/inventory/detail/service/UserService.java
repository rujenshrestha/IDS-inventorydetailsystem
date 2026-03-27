package com.inventory.detail.service;

import java.util.List;

import com.inventory.detail.entity.UserInfo;
import com.inventory.detail.model.User;

public interface UserService {
	
	public List<User> getUsers(String page);

	public List<User> getUserById(String id);
	
	public List<User> register(UserInfo user);
	
	public List<User> updateUser(UserInfo user);
	
}
