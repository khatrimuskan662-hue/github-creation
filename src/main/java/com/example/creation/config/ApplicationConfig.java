package com.example.creation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration //contain bean managed by spring,(spring boot start->scan config class->create beans ->store in IoC container)
public class ApplicationConfig {
    @Bean // create object of BcryptPasswordEncoder
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}
