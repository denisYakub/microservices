package com.denis.KafkaService.controller;

import com.denis.KafkaService.service.KafkaProducer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kafka")
@AllArgsConstructor
public class KafkaController {
    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping("/bdInsertMessageBroker")
    public void sendMessage(@RequestBody String request){
        kafkaProducer.sendMessage("saveToBd", request);
    }
}
