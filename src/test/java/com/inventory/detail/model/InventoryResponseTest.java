package com.inventory.detail.model;

import org.junit.jupiter.api.Test;

import com.inventory.detail.util.PojoTestUtil;

public class InventoryResponseTest {

	@Test
	void testPojo() {
		PojoTestUtil.testPojo(InventoryResponse.class);
	}
}
