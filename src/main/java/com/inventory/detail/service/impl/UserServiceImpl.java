package com.inventory.detail.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.detail.entity.UserInfo;
import com.inventory.detail.model.User;
import com.inventory.detail.model.UsersAPIResponse;
import com.inventory.detail.service.UserService;

@Service(value = "UserService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserDetailService userDetailService;

	@Override
	public List<User> getUsers(String page) {
		try {
			Map<String, Integer> queryParam = new HashMap<>();
			queryParam.put("page", Integer.parseInt(page));
			
			UsersAPIResponse usersAPIResponse = userDetailService.callUserDetail(queryParam, null); // uses WebClient
			//return mapper.mapUserDetail(userDetailService.getUserDetail(queryParam, null)); // uses RestTemplate
			
			return usersAPIResponse.getUsers();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public List<User> getUserById(String id) {
		try {
			UsersAPIResponse usersAPIResponse = userDetailService.callUserDetail(null, id);
			return usersAPIResponse.getUsers();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}


	@Override
	public List<User> register(UserInfo user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> updateUser(UserInfo user) {
		// TODO Auto-generated method stub
		return null;
	}

}
