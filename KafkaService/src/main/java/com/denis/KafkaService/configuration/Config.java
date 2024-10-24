package com.denis.KafkaService.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
    @Bean
    public static RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
