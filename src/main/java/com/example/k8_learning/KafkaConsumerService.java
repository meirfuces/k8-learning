package com.example.k8_learning;

import com.example.k8_learning.entities.MessageEntity;
import com.example.k8_learning.repositories.MessageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);
    private final MessageRepository messageRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public KafkaConsumerService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @KafkaListener(topics = "test-topic", groupId = "test-group")
    public void listen(ConsumerRecord<String, String> record) {
        logger.info("Received message: {}", record.value());
        try {
            MessageEntity msg = objectMapper.readValue(record.value(), MessageEntity.class);
            messageRepository.save(msg);
            logger.info("Saved message with id: {}", msg.getId());
        } catch (Exception e) {
            logger.error("Failed to parse or save message", e);
        }
    }
} 
