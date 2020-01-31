package com.training.petfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@IntegrationComponentScan
@EnableIntegration
public class PetfoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetfoodApplication.class, args);
	}

}
