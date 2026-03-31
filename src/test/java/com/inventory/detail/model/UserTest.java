package com.inventory.detail.model;

import org.junit.jupiter.api.Test;

import com.inventory.detail.util.PojoTestUtil;

public class UserTest {

	@Test
	void testPojo() {
		PojoTestUtil.testPojo(User.class);
	}
}
