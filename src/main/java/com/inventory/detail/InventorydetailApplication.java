package com.inventory.detail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class InventorydetailApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventorydetailApplication.class, args);
	}

}
