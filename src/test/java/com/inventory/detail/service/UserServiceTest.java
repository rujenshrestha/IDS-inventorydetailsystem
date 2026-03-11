package com.inventory.detail.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.inventory.detail.model.User;
import com.inventory.detail.service.UserService;
import com.inventory.detail.service.impl.UserServiceImpl;

public class UserServiceTest {

	private UserService serviceToTest;
	private String username = "test";
	private String password = "test1234";

	@BeforeEach
	public void setup() {
		serviceToTest = new UserServiceImpl();
		// serviceToTest = EasyMock.createMock(UserServiceImpl.class);
	}

	@Test
	public void testValidateLogin() {
		//User user = serviceToTest.validateLogin(username, password);
		//assertNotNull(user);
	}

}
