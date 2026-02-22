package com.fida.fida_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class FidaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FidaBackendApplication.class, args);
	}

}
