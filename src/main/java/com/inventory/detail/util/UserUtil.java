package com.inventory.detail.util;

import com.inventory.detail.model.User;

public class UserUtil {

	public static User getUser() {
		return new User(1, "sresta", "1234abc", "John Doe", "C", 3612285959L, true);
	}
}
