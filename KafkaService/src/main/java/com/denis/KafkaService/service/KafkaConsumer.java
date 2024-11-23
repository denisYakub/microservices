package com.denis.KafkaService.service;

import com.denis.KafkaService.configuration.Config;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    @Value("${application.URL_BD_SERVICE}")
    private String URL_BD_SERVICE;

    @Autowired
    private Config config;

    @KafkaListener(topics = "saveToBd", groupId = "myGroup")
    public void listen(String request){
        this.config.restTemplate()
                .postForEntity(this.URL_BD_SERVICE, request, Void.class);
    }
}
