package com.headissue.fate;

import com.headissue.fate.model.World;
import com.headissue.fate.repository.WorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
