package com.example.innowise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })public class InnowiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(InnowiseApplication.class, args);
	}

}
