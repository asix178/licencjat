package com.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Indicates that this class is a Spring Boot application configuration class
@Slf4j // Lombok annotation for logging
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args); // Entry point of the application
    }

}