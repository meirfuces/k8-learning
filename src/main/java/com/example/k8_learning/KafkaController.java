package com.example.k8_learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {
    private final KafkaProducerService producerService;

    @Autowired
    public KafkaController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping("/publish")
    public String publishMessage(@RequestParam String id, @RequestParam String message) {
        producerService.sendMessage(id, message);
        return "Message sent: " + message + " with id: " + id;
    }
} 