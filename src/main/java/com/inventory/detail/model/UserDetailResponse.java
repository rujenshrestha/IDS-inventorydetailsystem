package com.inventory.detail.model;

import java.util.ArrayList;
import java.util.List;

public class UserDetailResponse {

	List<UserDetail> data = new ArrayList<>();

	public List<UserDetail> getData() {
		return data;
	}

	public void setData(List<UserDetail> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "UserDetailResponse [data=" + data + "]";
	}

}
