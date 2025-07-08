package com.example.k8_learning;

import com.example.k8_learning.entities.MessageEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String id, String message) {
        try {
            String json = objectMapper.writeValueAsString(new MessageEntity(id, message));
            kafkaTemplate.send("test-topic", json);
        } catch (Exception e) {
            // handle error
        }
    }
} 
