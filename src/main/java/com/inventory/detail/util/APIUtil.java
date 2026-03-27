package com.inventory.detail.util;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import io.micrometer.common.util.StringUtils;

@Component
public class APIUtil {

	public String addPathParam(String url, String pathParam) {
		return StringUtils.isNotBlank(pathParam) ? (url + "/" + pathParam) : url;
	}
	
	public String addQueryParam(String url, Map<String, Integer> queryParams) {
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
		return url.substring(0, url.length() - 1);
	}
}
