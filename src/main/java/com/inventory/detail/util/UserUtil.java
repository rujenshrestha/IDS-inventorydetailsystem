package com.inventory.detail.util;

import com.inventory.detail.entity.UserInfo;

public class UserUtil {

	public static UserInfo getUser() {
		return new UserInfo(1L, "sresta", "1234abc", "John Doe", "C", 3612285959L, true);
	}
}
