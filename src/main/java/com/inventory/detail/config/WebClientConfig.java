package com.inventory.detail.config;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
	
	@Value("${REQRES_USER_URL}")
	private String userUrl;
	
	@Bean
	public WebClient userWebClient() {
	    return WebClient.builder()
	            .baseUrl(userUrl)
	            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
	            .build();
	}

}
