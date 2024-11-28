package com.denis.KafkaService.services;

import com.denis.KafkaService.configuration.Config;
import com.denis.KafkaService.service.KafkaConsumer;
import com.denis.KafkaService.service.KafkaProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
public class KafkaConsumerTests {
    @Mock
    private RestTemplate Con;

    @InjectMocks
    private KafkaConsumer consumer;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    /*@Test
    public void test_listen_method(){
        doNothing().when(Config.restTemplate()).postForEntity(anyString(), anyString(), eq(Void.class));

        this.consumer.listen("Say_hi");
    }*/
}
