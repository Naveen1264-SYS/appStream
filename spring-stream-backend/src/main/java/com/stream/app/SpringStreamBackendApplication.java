package com.stream.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringStreamBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringStreamBackendApplication.class, args);
	}

	Logger logger = LogManager.getLogger(SpringStreamBackendApplication.class);

}
