package com.example.ONG;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.example.ONG.repositories")
@EntityScan("com.example.ONG.entities")
@SpringBootApplication
public class OngApplication {

	public static void main(String[] args) {
		SpringApplication.run(OngApplication.class, args);
	}

}
