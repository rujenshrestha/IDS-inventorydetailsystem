package com.inventory.detail.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.detail.mapper.UserMapper;
import com.inventory.detail.model.CreateUserRequest;
import com.inventory.detail.model.CreateUserResponse;
import com.inventory.detail.model.User;
import com.inventory.detail.service.UserService;

@Service(value = "UserService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserDetailService userDetailService;

	private UserMapper mapper = new UserMapper();

	@Override
	public List<User> getAllUsers(String page) {
		try {
			Map<String, Integer> queryParam = new HashMap<>();
			queryParam.put("page", Integer.parseInt(page));
			
			//return mapper.mapUserDetail(userDetailService.callUserDetail(queryParam, null));
			return mapper.mapUserDetail(userDetailService.getUserDetail(queryParam, null));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public List<User> getUserDetail(String id) {
		try {
			return mapper.mapUserDetail(userDetailService.callUserDetail(null, id));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public List<User> validateLogin(String username, String password) {
		return null;//UserUtil.getUser();
	}

	@Override
	public List<User> register(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> updateUserInfo(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateUserResponse createUser(CreateUserRequest request) {
		try {
			//return userDetailService.addUserDetail(request);
			return userDetailService.createUserDetail(request);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
