package com.denis.KafkaService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConsumerConfig {
    @Value("kafka.server")
    private String kafkaServer;

    @Value("${kafka.group.id}")
    private String kafkaGroupId;
}
