// src/main/java/com/example/k8_learning/MessageEntity.java
package com.example.k8_learning.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "my_messages")
public class MessageEntity {
    @Id
    private String id;
    private String message;

    // Constructors, getters, setters
    public MessageEntity() {}

    public MessageEntity(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}