package com.mksistemas.supplychain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JacksonAutoConfiguration {
	@Bean
	@Primary
	ObjectMapper objectMapper() {
		return new ObjectMapper().registerModule(new JpaApiModule());
	}
}
