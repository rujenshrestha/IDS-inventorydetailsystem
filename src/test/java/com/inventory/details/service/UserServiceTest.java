package com.inventory.details.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.inventory.detail.model.User;
import com.inventory.detail.service.UserService;
import com.inventory.detail.service.impl.UserServiceImpl;

public class UserServiceTest {
	
	private UserService serviceToTest;
	private String username = "test";
	private String password = "test1234";
	
	@Before
	public void setup() {
		serviceToTest = new UserServiceImpl();
		//serviceToTest = EasyMock.createMock(UserServiceImpl.class);
	}
	
//	@Test
//	public void testValidateLogin() {
//		User user = serviceToTest.validateLogin(username, password);
//		assertNotNull(user);
//	}

}
