package com.inventory.detail.model;

import org.junit.jupiter.api.Test;

import com.inventory.detail.util.PojoTestUtil;

public class UserResponseTest {

	@Test
	void testPojo() {
		PojoTestUtil.testPojo(UserResponse.class);
	}
}
