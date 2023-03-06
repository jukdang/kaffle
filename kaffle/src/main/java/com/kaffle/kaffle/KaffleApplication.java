package com.kaffle.kaffle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EntityScan("com.kaffle.kaffle")
@EnableJpaRepositories("com.kaffle.kaffle")
public class KaffleApplication {

	public static void main(String[] args) {
		SpringApplication.run(KaffleApplication.class, args);
	}



}
