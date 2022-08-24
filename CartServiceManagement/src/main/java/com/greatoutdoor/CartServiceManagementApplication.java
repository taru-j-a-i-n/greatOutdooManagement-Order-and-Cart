package com.greatoutdoor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CartServiceManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartServiceManagementApplication.class, args);
	}

}
