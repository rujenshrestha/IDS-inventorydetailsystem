package com.inventory.detail.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.inventory.detail.model.User;
import com.inventory.detail.model.UserDetailResponse;

public class UserMapper {

	public List<User> mapUserDetail(UserDetailResponse response){
		
		if(response == null || CollectionUtils.isEmpty(response.getData())) {
			throw new IllegalArgumentException("Data is null or emtpy");
		}
		List<User> userList= new ArrayList<User>();
		response.getData().stream().forEach(user -> {
			User u = new User();
			u.setFullName(user.getFirstName()+" "+user.getLastName());
			u.setId(user.getId());
			u.setActive(true);
			userList.add(u);
		});
		
		return userList;
	}
}
