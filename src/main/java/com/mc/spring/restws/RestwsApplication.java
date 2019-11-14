package com.mc.spring.restws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class RestwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestwsApplication.class, args);
	}

}
