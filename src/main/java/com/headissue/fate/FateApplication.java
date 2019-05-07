package com.headissue.fate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FateApplication {

	public static void main(String[] args) {
		SpringApplication.run(FateApplication.class, args);
	}

}
