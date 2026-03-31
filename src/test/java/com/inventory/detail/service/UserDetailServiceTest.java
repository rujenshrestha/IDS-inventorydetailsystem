package com.inventory.detail.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.net.URI;

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
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.RequestBodyUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.util.UriBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.inventory.detail.model.User;
import com.inventory.detail.model.UsersAPIResponse;
import com.inventory.detail.service.impl.UserDetailService;

import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class UserDetailServiceTest {

    @Mock
    private WebClient webClient;

    @Mock
    private RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private RequestHeadersSpec requestHeadersSpec;

    @Mock
    private RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    private RequestBodySpec requestBodySpec;

    @Mock
    private ResponseSpec responseSpec;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private UserDetailService userDetailService;

    private final String BASE_URL = "http://test.com/api/users";
    private UsersAPIResponse mockResponse;
    private User mockUser;

    @BeforeEach
    void setup() throws Exception {
        ReflectionTestUtils.setField(userDetailService, "userUrl", BASE_URL);
        ReflectionTestUtils.setField(userDetailService, "restTemplate", restTemplate);
        mockResponse = mockUsersAPIResponse();
        mockUser = mockUser();
    }


    @Test
    void testCallUserDetail() throws Exception {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        UriBuilder uriBuilder = mock(UriBuilder.class);

        when(uriBuilder.path("/{id}")).thenReturn(uriBuilder);
        when(uriBuilder.queryParam("page", 1)).thenReturn(uriBuilder);
        when(uriBuilder.build("10")).thenReturn(URI.create("/10?page=1"));

        when(requestHeadersUriSpec.uri(ArgumentMatchers.<Function<UriBuilder, URI>>any())).thenAnswer(invocation -> {
            Function<UriBuilder, URI> func = invocation.getArgument(0);
            func.apply(uriBuilder); //for executing lambda
            return requestHeadersSpec;
        });
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(UsersAPIResponse.class))
                .thenReturn(Mono.just(mockResponse));

        UsersAPIResponse result =
                userDetailService.callUserDetail(Map.of("page", 1), "10");
        assertNotNull(result);
        assertTrue(result.getUsers().get(0).getName().equals("TEST_NAME"));
        
        verify(webClient).get();
    }
    
    @Test
    void testCallUserDetail_PathParamNull() throws Exception {
    	 when(webClient.get()).thenReturn(requestHeadersUriSpec);
         UriBuilder uriBuilder = mock(UriBuilder.class);

         when(uriBuilder.queryParam("page", 1)).thenReturn(uriBuilder);
         when(uriBuilder.build()).thenReturn(URI.create("?page=1"));

         when(requestHeadersUriSpec.uri(ArgumentMatchers.<Function<UriBuilder, URI>>any())).thenAnswer(invocation -> {
             Function<UriBuilder, URI> func = invocation.getArgument(0);
             func.apply(uriBuilder); //for executing lambda
             return requestHeadersSpec;
         });
         when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
         when(responseSpec.bodyToMono(UsersAPIResponse.class))
                 .thenReturn(Mono.just(mockResponse));

         UsersAPIResponse result =
                 userDetailService.callUserDetail(Map.of("page", 1), null);
         assertNotNull(result);
         assertTrue(result.getUsers().get(0).getName().equals("TEST_NAME"));
         
         verify(webClient).get();
    }
    
    @Test
    void testCallUserDetail_QueryParamNull() throws Exception {
    	  when(webClient.get()).thenReturn(requestHeadersUriSpec);
          UriBuilder uriBuilder = mock(UriBuilder.class);
          when(uriBuilder.build()).thenReturn(URI.create(""));

          when(requestHeadersUriSpec.uri(ArgumentMatchers.<Function<UriBuilder, URI>>any())).thenAnswer(invocation -> {
              Function<UriBuilder, URI> func = invocation.getArgument(0);
              func.apply(uriBuilder); //for executing lambda
              return requestHeadersSpec;
          });
          when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
          when(responseSpec.bodyToMono(UsersAPIResponse.class))
                  .thenReturn(Mono.just(mockResponse));

          UsersAPIResponse result =
                  userDetailService.callUserDetail(null, null);
          assertNotNull(result);
          assertTrue(result.getUsers().get(0).getName().equals("TEST_NAME"));
          
          verify(webClient).get();
    }
    
    @Test
    void testCallUserDetail_NullValue() throws Exception {
    	Map<String, Integer> map = new HashMap<>();
    	map.put("page", 1);
    	map.put("line", null);
    	
    	 when(webClient.get()).thenReturn(requestHeadersUriSpec);
         UriBuilder uriBuilder = mock(UriBuilder.class);

         when(uriBuilder.queryParam("page", 1)).thenReturn(uriBuilder);
         when(uriBuilder.build()).thenReturn(URI.create("?page=1"));

         when(requestHeadersUriSpec.uri(ArgumentMatchers.<Function<UriBuilder, URI>>any())).thenAnswer(invocation -> {
             Function<UriBuilder, URI> func = invocation.getArgument(0);
             func.apply(uriBuilder); //for executing lambda
             return requestHeadersSpec;
         });
         when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
         when(responseSpec.bodyToMono(UsersAPIResponse.class))
                 .thenReturn(Mono.just(mockResponse));

         UsersAPIResponse result =
                 userDetailService.callUserDetail(map, null);
         assertNotNull(result);
         assertTrue(result.getUsers().get(0).getName().equals("TEST_NAME"));
         
         verify(webClient).get();
    }
    
    @Test
    void testCallUserDetail_WebClientException() throws Exception {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(
                ArgumentMatchers.<Function<UriBuilder, URI>>any()
        )).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenThrow(mock(WebClientException.class));

       assertTrue(assertThrows(Exception.class, () -> {
        	userDetailService.callUserDetail(Map.of("page", 1), "1");
        }).getLocalizedMessage().contains("User Detail Service GET call failed")) ;

        verify(webClient).get();
    }


    @Test
    void testAddUserDetail() throws Exception {
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.contentType(MediaType.APPLICATION_JSON))
                .thenReturn(requestBodySpec);
        when(requestBodySpec.bodyValue(any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(UsersAPIResponse.class))
                .thenReturn(Mono.just(mockResponse));

        UsersAPIResponse result = userDetailService.addUserDetail(mockUser);
        assertNotNull(result);
        
        verify(webClient).post();
    }
    
    @Test
    void testAddUserDetail_WebClientException() throws Exception {

        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.contentType(MediaType.APPLICATION_JSON))
                .thenReturn(requestBodySpec);
        when(requestBodySpec.bodyValue(any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenThrow(mock(WebClientException.class));

        assertTrue(assertThrows(Exception.class, () -> {
        	userDetailService.addUserDetail(mockUser);
        }).getMessage().contains("User Detail Service POST call failed"));
        
        verify(webClient).post();
    }


    @Test
    void testGetUserDetail() throws Exception {
        ResponseEntity<UsersAPIResponse> responseEntity =
                new ResponseEntity<>(mockResponse, HttpStatus.OK);

        when(restTemplate.exchange(
        		 argThat(uri -> 
                 uri.toString().contains("/2") &&
                 uri.toString().contains("page=1")
             ),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(UsersAPIResponse.class)))
                .thenReturn(responseEntity);

        UsersAPIResponse result =
                userDetailService.getUserDetail(Map.of("page", 1), "2");

        assertNotNull(result);
        verify(restTemplate).exchange(
                any(URI.class),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(UsersAPIResponse.class));
    }
    
    @Test
    void testGetUserDetail_PathParamNull() throws Exception {
        ResponseEntity<UsersAPIResponse> responseEntity =
                new ResponseEntity<>(mockResponse, HttpStatus.OK);

        when(restTemplate.exchange(
                any(URI.class),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(UsersAPIResponse.class)))
                .thenReturn(responseEntity);

        UsersAPIResponse result =
                userDetailService.getUserDetail(Map.of("page", 1), null);

        assertNotNull(result);
        verify(restTemplate).exchange(
                any(URI.class),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(UsersAPIResponse.class));
    }
    
    @Test
    void testGetUserDetail_QueryParamNull() throws Exception {
        ResponseEntity<UsersAPIResponse> responseEntity =
                new ResponseEntity<>(mockResponse, HttpStatus.OK);

        when(restTemplate.exchange(
                any(URI.class),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(UsersAPIResponse.class)))
                .thenReturn(responseEntity);

        UsersAPIResponse result =
                userDetailService.getUserDetail(null, "2");

        assertNotNull(result);
        verify(restTemplate).exchange(
                any(URI.class),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(UsersAPIResponse.class));
    }
    
    @Test
    void testGetUserDetail_Non200Response() throws Exception {
        ResponseEntity<UsersAPIResponse> responseEntity =
                new ResponseEntity<>(mockResponse, HttpStatus.INTERNAL_SERVER_ERROR);

        when(restTemplate.exchange(
                any(URI.class),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(UsersAPIResponse.class)))
                .thenReturn(responseEntity);

        assertTrue(
        	    assertThrows(Exception.class, () ->
        	    userDetailService.getUserDetail(Map.of("page", 1), "2")
        	    ).getMessage().contains("GET User call failed with status")
        	);
        
        verify(restTemplate).exchange(
                any(URI.class),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(UsersAPIResponse.class));
    }


    @Test
    void testCreateUserDetail() throws Exception {
        ResponseEntity<UsersAPIResponse> responseEntity =
                new ResponseEntity<>(mockResponse, HttpStatus.OK);

        when(restTemplate.exchange(
                eq(BASE_URL),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(UsersAPIResponse.class)))
                .thenReturn(responseEntity);

        UsersAPIResponse result =
                userDetailService.saveUser(mockUser);
        assertNotNull(result);
        
        verify(restTemplate).exchange(
                eq(BASE_URL),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(UsersAPIResponse.class));
    }
    
    @Test
    void testCreateUserDetail_Non200Response() throws Exception {

        ResponseEntity<UsersAPIResponse> responseEntity =
                new ResponseEntity<>(mockResponse, HttpStatus.SERVICE_UNAVAILABLE);

        when(restTemplate.exchange(
                eq(BASE_URL),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(UsersAPIResponse.class)))
                .thenReturn(responseEntity);

        assertTrue(
        	    assertThrows(Exception.class, () ->
        	    userDetailService.saveUser(mockUser)
        	    ).getMessage().contains("User detail POST call failed with status")
        	);
        
        verify(restTemplate).exchange(
                eq(BASE_URL),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(UsersAPIResponse.class));
    }
    
	private UsersAPIResponse mockUsersAPIResponse() {
		List<User> users = new ArrayList<>();
		users.add(mockUser());
		return new UsersAPIResponse(users);
	}
	
	private User mockUser() {
		User u = new User();
		u.setName("TEST_NAME");
		u.setPhone("9999999999");
		u.setUsername("TEST_USERNAME");
		return u;
	}

}
