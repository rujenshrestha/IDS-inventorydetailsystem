package com.inventory.detail.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class APIUtilTest {

	private APIUtil utilToTest;
	private final String api = "https://test-api/test-resource";
	private final String pathParam = "path-param";
	private final String urlWithPathParam = "https://test-api/test-resource/path-param";
	private final String urlWithQueryParam = "https://test-api/test-resource?query-value=1";
	private Map<String, Integer> queryMap;
	
	@BeforeEach
	public void setup() {
		utilToTest = new APIUtil();
		queryMap = new HashMap<>();
		queryMap.put("query-value", 1);
	}
	
	@Test
	public void testAddPathParam() {
		assertTrue(urlWithPathParam.equals(utilToTest.addPathParam(api, pathParam)));
	}
	
	@Test
	public void testAddPathParam_EmptyPathParam() {
		assertTrue(api.equals(utilToTest.addPathParam(api, "")));
	}
	
	@Test
	public void testAddQueryParam() {
		assertTrue(urlWithQueryParam.equals(utilToTest.addQueryParam(api, queryMap)));
	}
	
	@Test
	public void testAddQueryParam_MultipleQueryParams() {
		String urlWithQueryParam1 = "https://test-api/test-resource?query-value1=1&query-value2=2";
		String urlWithQueryParam2 = "https://test-api/test-resource?query-value2=2&query-value1=1";
		Map<String, Integer> queryMap = new HashMap<>();
		queryMap.put("query-value1", 1);
		queryMap.put("query-value2", 2);
		assertTrue(urlWithQueryParam1.equals(utilToTest.addQueryParam(api, queryMap)) || 
				urlWithQueryParam2.equals(utilToTest.addQueryParam(api, queryMap)));
	}
	
	@Test
	public void testAddQueryParam_EmptyQueryParams() {
		assertTrue(api.equals(utilToTest.addQueryParam(api, new HashMap<String, Integer>())));
	}
	
	@Test
	public void testAddQueryParam_NullKey() {
		Map<String, Integer> queryMap = new HashMap<>();
		queryMap.put(null, 1);
		assertTrue(api.equals(utilToTest.addQueryParam(api, queryMap)));
	}
}
