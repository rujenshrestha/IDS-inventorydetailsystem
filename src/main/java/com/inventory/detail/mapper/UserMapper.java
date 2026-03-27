package com.inventory.detail.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.inventory.detail.entity.UserInfo;
import com.inventory.detail.model.UsersAPIResponse;

public class UserMapper {

//	public List<UserInfo> mapUserDetail(UsersAPIResponse response){
//		
//		if(response == null || CollectionUtils.isEmpty(response.getData())) {
//			throw new IllegalArgumentException("Data is null or emtpy");
//		}
//		List<UserInfo> userList= new ArrayList<UserInfo>();
//		response.getData().stream().forEach(user -> {
//			UserInfo u = new UserInfo();
//			u.setFullName(user.getFirstName()+" "+user.getLastName());
//			u.setId(user.getId());
//			u.setActive(true);
//			userList.add(u);
//		});
//		
//		return userList;
//	}
}
