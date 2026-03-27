package com.inventory.detail.model;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.inventory.detail.util.UserAPIResponseDeserializer;

@JsonDeserialize(using = UserAPIResponseDeserializer.class)
public class UsersAPIResponse {

	private List<User> users;

	public UsersAPIResponse() {
	}

	public UsersAPIResponse(List<User> users) {
		this.users = users;
	}

	public List<User> getUsers() {
		return users;
	}

	@Override
	public String toString() {
		return "UsersAPIResponse [users=" + users + "]";
	}

}
