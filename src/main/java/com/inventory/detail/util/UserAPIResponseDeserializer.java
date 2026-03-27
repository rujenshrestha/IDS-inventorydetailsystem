package com.inventory.detail.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventory.detail.model.User;
import com.inventory.detail.model.UsersAPIResponse;

public class UserAPIResponseDeserializer extends JsonDeserializer<UsersAPIResponse> {

	@Override
	public UsersAPIResponse deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JacksonException {
		ObjectMapper mapper = (ObjectMapper) p.getCodec();
		JsonNode node = mapper.readTree(p);

		List<User> users = new ArrayList<>();

		if (node.isArray()) {
			for (JsonNode n : node) {
				users.add(mapper.treeToValue(n, User.class));
			}
		} else {
			users.add(mapper.treeToValue(node, User.class));
		}
		return new UsersAPIResponse(users);
	}

}
