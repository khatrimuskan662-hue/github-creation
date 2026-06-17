package com.example.creation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CreationApplication {
    public static void main(String[] args) {
        SpringApplication.run(CreationApplication.class, args);
    }
}