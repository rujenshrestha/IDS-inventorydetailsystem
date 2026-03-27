package com.inventory.detail.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.el.lang.FunctionMapperImpl.Function;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.inventory.detail.model.UsersAPIResponse;
import com.inventory.detail.service.impl.UserDetailService;
import com.inventory.detail.util.APIUtil;

import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class UserDetailServiceTest {

	@InjectMocks
	private UserDetailService service;

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private APIUtil util;

	@Mock
	private Builder builder;

	@Mock
	private WebClient webClient;

	@Mock
	private RequestHeadersUriSpec<?> requestHeadersUriSpec;

	@Mock
	private RequestHeadersSpec<?> requestHeadersSpec;

	@Mock
	private ResponseSpec responseSpec;

	private static final String PATH_PARAM = "123";
	private static final String FINAL_URL = "http://test.com/users/123?page=1";
	private static final String BASE_URL = "http://test.com/users";
	private final String jsonResponse = "{\"id\":123}";
	private Map<String, Integer> queryParams;

	@BeforeEach
	void setUp() {
		ReflectionTestUtils.setField(service, "userUrl", BASE_URL);
		queryParams = new HashMap<>();
		queryParams.put("page", 1);
	}

	@Test
    void testCallUserDetail_success() throws Exception {
    	
        when(util.addPathParam(anyString(), eq(PATH_PARAM))).thenReturn("http://test.com/users/123");
        when(util.addQueryParam(anyString(), eq(queryParams))).thenReturn(FINAL_URL);

        when(builder.baseUrl(FINAL_URL)).thenReturn(builder);
        when(builder.build()).thenReturn(webClient);

        //when(webClient.get()).thenReturn(requestHeadersUriSpec);
        doReturn(requestHeadersUriSpec).when(webClient).get();
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(jsonResponse));

        UserDetailService spyService = spy(service);
        UsersAPIResponse mockResponse = new UsersAPIResponse();

        doReturn(mockResponse).when(spyService).getUserDetailResponse(jsonResponse);

        UsersAPIResponse result = spyService.callUserDetail(queryParams, PATH_PARAM);

        assertNotNull(result);
        assertEquals(mockResponse, result);

        verify(util).addPathParam(anyString(), eq(PATH_PARAM));
        verify(util).addQueryParam(anyString(), eq(queryParams));
        verify(builder).baseUrl(FINAL_URL);
        verify(webClient).get();
    }

	@Test
	void testCallUserDetail_webClientException() {
		Map<String, Integer> queryParams = new HashMap<>();

		when(util.addPathParam(anyString(), eq(PATH_PARAM))).thenReturn("url");
		when(util.addQueryParam(anyString(), eq(queryParams))).thenReturn("url");

		when(builder.baseUrl(anyString())).thenReturn(builder);
		when(builder.build()).thenReturn(webClient);

		when(webClient.get()).thenThrow(new WebClientException("Error") {
		});

		Exception ex = assertThrows(Exception.class, () -> service.callUserDetail(queryParams, PATH_PARAM));

		assertTrue(ex.getMessage().contains("User Detail Service GET call failed"));
	}
	
//	@Test
//    void testCallUserDetailWithPage_success() throws Exception {
//
//        when(builder.baseUrl(BASE_URL)).thenReturn(builder);
//        when(builder.build()).thenReturn(webClient);
//        doReturn(requestHeadersUriSpec).when(webClient).get();
//        when(requestHeadersUriSpec.uri(
//                ArgumentMatchers.<Function<UriBuilder, URI>>any()
//        )).thenReturn(requestHeadersSpec);
//        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
//        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(jsonResponse));
//
//        UserDetailService spyService = spy(service);
//        UserDetailResponse mockResponse = new UserDetailResponse();
//
//        doReturn(mockResponse).when(spyService).getUserDetailResponse(jsonResponse);
//
//        UserDetailResponse result = spyService.callUserDetailWithPage(queryParams);
//
//        assertNotNull(result);
//        assertEquals(mockResponse, result);
//
//        verify(builder).baseUrl(FINAL_URL);
//        verify(webClient).get();
//    }

	@Test
	void testGetUserDetail_success() throws Exception {
		Map<String, Integer> queryParams = new HashMap<>();
		queryParams.put("page", 1);

		String finalUrl = BASE_URL + "?page=1";
		String mockResponseBody = "{\"data\":{\"id\":1,\"name\":\"John\"}}";

		when(util.addQueryParam(BASE_URL, queryParams)).thenReturn(finalUrl);

		ResponseEntity<String> mockResponse = new ResponseEntity<>(mockResponseBody, HttpStatus.OK);

		when(restTemplate.exchange(eq(finalUrl), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
				.thenReturn(mockResponse);

		UserDetailService spyService = spy(service);
		UsersAPIResponse mockParsedResponse = new UsersAPIResponse();

		doReturn(mockParsedResponse).when(spyService).getUserDetailResponse(mockResponseBody);

		UsersAPIResponse result = spyService.getUserDetail(queryParams, "1");

		assertNotNull(result);
		assertEquals(mockParsedResponse, result);

		verify(util).addQueryParam(BASE_URL, queryParams);
		verify(restTemplate).exchange(eq(finalUrl), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class));
	}

	@Test
	void testGetUserDetail_non200Response_shouldThrowException() {
		Map<String, Integer> queryParams = new HashMap<>();
		String finalUrl = BASE_URL;

		when(util.addQueryParam(BASE_URL, queryParams)).thenReturn(finalUrl);

		ResponseEntity<String> mockResponse = new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);

		when(restTemplate.exchange(eq(finalUrl), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
				.thenReturn(mockResponse);
		Exception exception = assertThrows(Exception.class, () -> service.getUserDetail(queryParams, "1"));
		assertTrue(exception.getMessage().contains("User detail GET call failed"));
	}

	@Test
	void testGetUserDetail_restTemplateThrowsException() {
		Map<String, Integer> queryParams = new HashMap<>();
		String finalUrl = BASE_URL;

		when(util.addQueryParam(BASE_URL, queryParams)).thenReturn(finalUrl);

		when(restTemplate.exchange(eq(finalUrl), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
				.thenThrow(new RuntimeException("API down"));
		Exception exception = assertThrows(Exception.class, () -> service.getUserDetail(queryParams, "1"));
		assertTrue(exception.getMessage().contains("API down"));
	}
}
