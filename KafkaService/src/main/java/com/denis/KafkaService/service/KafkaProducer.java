package com.denis.KafkaService.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaProducer {
    @Autowired
    public KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String request){
        kafkaTemplate.send(topic, request);
    }
}
