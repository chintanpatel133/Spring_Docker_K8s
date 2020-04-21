package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class SpringBootDockerApplication extends SpringBootServletInitializer {
	
	@GetMapping("/hello")
	public String getMessage() {
		return "Hello World !!";
		
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDockerApplication.class, args);
	}

	@Override
    	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        	return builder.sources(SpringBootDockerApplication.class);
    	}

}
