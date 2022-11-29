package com.prepreproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PrepreprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrepreprojectApplication.class, args);
	}

}
