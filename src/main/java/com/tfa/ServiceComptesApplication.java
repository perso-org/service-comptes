package com.tfa;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServiceComptesApplication {

	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(ServiceComptesApplication.class, args);
	}

}
