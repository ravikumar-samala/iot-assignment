package com.ravi.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ItoDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItoDemoApplication.class, args);
	}

}
