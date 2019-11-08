package com.training.petfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.training.petfood")
@SpringBootApplication
public class PetfoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetfoodApplication.class, args);
	}

}
