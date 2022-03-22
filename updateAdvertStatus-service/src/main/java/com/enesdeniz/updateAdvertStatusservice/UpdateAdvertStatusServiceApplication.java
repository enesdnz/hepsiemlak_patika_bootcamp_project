package com.enesdeniz.updateAdvertStatusservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UpdateAdvertStatusServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpdateAdvertStatusServiceApplication.class, args);
	}

}
