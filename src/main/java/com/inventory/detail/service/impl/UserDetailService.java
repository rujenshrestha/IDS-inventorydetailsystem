package com.inventory.detail.service.impl;

import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import com.inventory.detail.model.CreateUserRequest;
import com.inventory.detail.model.CreateUserResponse;
import com.inventory.detail.model.UsersAPIResponse;
import com.inventory.detail.util.APIUtil;

import io.micrometer.common.util.StringUtils;

@Service(value = "UserDetailService")
public class UserDetailService {

	@Value("${REQRES_USER_URL}")
	private String userUrl;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private APIUtil util;

	private WebClient webClient;

	private static Logger logger = LoggerFactory.getLogger(UserDetailService.class);

	public UserDetailService(@Qualifier("userWebClient") WebClient webClient) {
		this.webClient = webClient;
	}

	/*
	 * Calling User GET API passing either page number as queryParam or User ID as path param
	 */
	public UsersAPIResponse callUserDetail(Map<String, Integer> queryParam, String id) throws Exception {
		try {
			logger.info("calling UserDetail API with URL: {}", userUrl);
			UsersAPIResponse response = webClient.get()
					.uri(StringUtils.isNotBlank(id) ? "/{id}": "", id)
					.retrieve()
					.bodyToMono(UsersAPIResponse.class).block();
			return response;

		} catch (WebClientException ex) {
			throw new Exception("User Detail Service GET call failed " + ex.getMessage());
		}
	}

	/*
	 * Calling UserDetail GET API via WebClient, queryParam containing page and page number
	 */
	public UsersAPIResponse callUserDetailWithPage(Map<String, Integer> queryParam) throws Exception {
		try {
			logger.info("calling UserDetail API with URL: {}", userUrl);
			return webClient.get()
				.uri(uriBuilder -> {
						queryParam.forEach(uriBuilder::queryParam);
				return uriBuilder.build();
				})
				.retrieve()
				.bodyToMono(UsersAPIResponse.class)
				.block();

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
	public UsersAPIResponse getUserDetail(Map<String, Integer> queryParam, String pathParam) throws Exception {
		String url = util.addQueryParam(userUrl, queryParam);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		// headers.set("X-Request-Source", "Desktop"); // custom header

		HttpEntity request = new HttpEntity(headers);

		try {
			logger.info("calling UserDetail API with URL: {}", url);
			ResponseEntity<UsersAPIResponse> response = restTemplate.exchange(url, HttpMethod.GET, request, UsersAPIResponse.class);

			if (response.getStatusCode() == HttpStatus.OK) {
				return response.getBody();
			} else {
				throw new Exception("GET User call failed with status: " + response.getStatusCode());
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
}
