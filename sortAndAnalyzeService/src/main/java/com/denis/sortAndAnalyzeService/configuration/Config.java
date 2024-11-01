package com.denis.sortAndAnalyzeService.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
    @Bean
    static public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
