package com.denis.KafkaService.service;

import com.denis.KafkaService.configuration.Config;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    @Value("${application.URL_BD_SERVICE}")
    private String URL_BD_SERVICE;

    @KafkaListener(topics = "saveToBd", groupId = "myGroup")
    public void listen(String request){
        this.postRequest(this.URL_BD_SERVICE, request, Void.class);
    }

    public <T>ResponseEntity postRequest(String url, Object body, Class<T> responseType){
        return Config.restTemplate().postForEntity(
                url,
                body,
                responseType
        );
    }
}
