package com.example.k8_learning.repositories;


import com.example.k8_learning.entities.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageEntity, String> {}
