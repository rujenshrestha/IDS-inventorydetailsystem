package com.inventory.detail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCaching
public class InventorydetailApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventorydetailApplication.class, args);
	}

	// Bean Configuration for RestTemplate
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
