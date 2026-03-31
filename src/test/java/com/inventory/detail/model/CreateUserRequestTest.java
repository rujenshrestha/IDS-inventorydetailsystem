package com.inventory.detail.model;

import org.junit.jupiter.api.Test;
import com.inventory.detail.util.PojoTestUtil;

public class CreateUserRequestTest {

	@Test
	void testPojo() {
		PojoTestUtil.testPojo(CreateUserRequest.class);
	}
}
