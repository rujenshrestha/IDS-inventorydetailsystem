package com.inventory.detail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.detail.model.User;
import com.inventory.detail.model.UsersAPIResponse;
import com.inventory.detail.service.UserService;

@Service(value = "UserService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserDetailService userDetailService;

	@Override
	public List<User> getUsers() {
		try {
			UsersAPIResponse usersAPIResponse = userDetailService.callUserDetail(null, null);
			return usersAPIResponse.getUsers();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public List<User> getUserById(String id) {
		try {
			UsersAPIResponse usersAPIResponse = userDetailService.callUserDetail(null, id); // uses WebClient
			//UsersAPIResponse usersAPIResponse = userDetailService.getUserDetail(null, id); // uses RestTemplate
			return usersAPIResponse.getUsers();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}


	@Override
	public List<User> saveUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<User> updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
