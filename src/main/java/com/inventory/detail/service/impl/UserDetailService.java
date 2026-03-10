package com.inventory.detail.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventory.detail.model.CreateUserRequest;
import com.inventory.detail.model.CreateUserResponse;
import com.inventory.detail.model.UserDetail;
import com.inventory.detail.model.UserDetailResponse;

import io.micrometer.common.util.StringUtils;

@Service(value = "UserDetailService")
public class UserDetailService {

	@Value("${REQRES_USER_URL}")
	private String userUrl;

	@Autowired
	private RestTemplate restTemplate;

	private WebClient webClient;

	private static Logger logger = LoggerFactory.getLogger(UserDetailService.class);

	/*
	 * Calling UserDetail GET API via WebClient, queryParam containing page and page
	 * number, pathParam containing userId
	 */
	public UserDetailResponse callUserDetail(Map<String, Integer> queryParam, String pathParam) throws Exception {

		String url = addPathParam(userUrl, pathParam);
		url = addQueryParam(url, queryParam);

		try {
			logger.info("calling UserDetail API with URL: {}", url);
			webClient = WebClient.builder().baseUrl(url).build();
			String response = webClient.get().retrieve().bodyToMono(String.class).block();

			return getUserDetailResponse(response);

		} catch (WebClientException ex) {
			throw new Exception("User Detail Service GET call failed " + ex.getMessage());
		}
	}

	/*
	 * Calling UserDetail GET API via WebClient, queryParam containing page and page
	 * number
	 */
	public UserDetailResponse callUserDetailWithPage(Map<String, Integer> queryParam) throws Exception {

		try {
			logger.info("calling UserDetail API with URL: {}", userUrl);
			webClient = WebClient.builder().baseUrl(userUrl).build();

			String response = webClient.get().uri(uriBuilder -> uriBuilder.queryParam("page", 2).build()).retrieve()
					.bodyToMono(String.class).block();

			return getUserDetailResponse(response);

		} catch (WebClientException ex) {
			throw new Exception("User Detail Service GET call failed " + ex.getMessage());
		}
	}

	/*
	 * Calling UserDetail POST API via WebClient, request containing name and job
	 */
	public CreateUserResponse addUserDetail(CreateUserRequest request) throws Exception {

		try {
			logger.info("calling UserDetail API with URL: {}", userUrl);
			webClient = WebClient.builder().baseUrl(userUrl).build();
			CreateUserResponse response = webClient.post().contentType(MediaType.APPLICATION_JSON).bodyValue(request)
					.retrieve().bodyToMono(CreateUserResponse.class).block();

			return response;
		} catch (WebClientException ex) {
			throw new Exception("User Detail Service POST call failed " + ex.getMessage());
		}
	}

	/*
	 * Calling UserDetail GET API via RestTemplate, queryParam containing page and
	 * page number, pathParam containing userId
	 */
	public UserDetailResponse getUserDetail(Map<String, Integer> queryParam, String pathParam) throws Exception {

		String url = addQueryParam(userUrl, queryParam);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		// headers.set("X-Request-Source", "Desktop"); // custom header

		HttpEntity request = new HttpEntity(headers);

		try {
			logger.info("calling UserDetail API with URL: {}", url);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

			if (response.getStatusCode() == HttpStatus.OK) {
				return getUserDetailResponse(response.getBody());
			} else {
				throw new Exception("User detail GET call failed with status: " + response.getStatusCode());
			}

		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * Calling UserDetail POST API via RestTemplate, request containing name and job
	 */
	public CreateUserResponse createUserDetail(CreateUserRequest request) throws Exception {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity entity = new HttpEntity(headers);

		try {
			logger.info("calling UserDetail API with URL: {}", userUrl);
			ResponseEntity<CreateUserResponse> response = restTemplate.exchange(userUrl, HttpMethod.POST, entity,
					CreateUserResponse.class);

			if (response.getStatusCode() == HttpStatus.OK) {
				return response.getBody();

			} else {
				throw new Exception("User detail POST call failed with status: " + response.getStatusCode());
			}

		} catch (Exception e) {
			throw e;
		}
	}

	private String addPathParam(String url, String pathParam) {
		return StringUtils.isNotBlank(pathParam) ? (url + "/" + pathParam) : url;
	}

	private String addQueryParam(String url, Map<String, Integer> queryParams) {
		if (CollectionUtils.isEmpty(queryParams))
			return url;

		StringBuilder sb = new StringBuilder(url);
		sb.append("?");

		for (Map.Entry<String, Integer> entry : queryParams.entrySet()) {
			if (StringUtils.isNotBlank(entry.getKey())) {
				sb.append(entry.getKey() + "=" + entry.getValue());
				sb.append("&");
			}

		}
		url = sb.toString();
		if (url.charAt(url.length() - 1) == '&' || url.charAt(url.length() - 1) == '?')
			return url.substring(0, url.length() - 1);

		return url;

	}

	private UserDetailResponse getUserDetailResponse(String response) throws Exception {
		UserDetailResponse userDetailResponse = new UserDetailResponse();
		JSONObject jsonObject = new JSONObject(response);
		ObjectMapper objectMapper = new ObjectMapper();

		try {

			JSONArray jsonArray = jsonObject.getJSONArray("data");
			UserDetail[] userDetailArray = objectMapper.readValue(jsonArray.toString(), UserDetail[].class);
			userDetailResponse.setData(Arrays.asList(userDetailArray));

		} catch (JSONException exc) {
			try {
				JSONObject data = jsonObject.getJSONObject("data");
				UserDetail userDetail = objectMapper.readValue(data.toString(), UserDetail.class);
				userDetailResponse.getData().add(userDetail);

			} catch (JSONException ex) {
				throw new Exception("Unable to parse User Detail response: " + response);
			}

		}
		return userDetailResponse;
	}
}
