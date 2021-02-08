package com.example.demo;

import org.springframework.kafka.annotation.KafkaListener;

public class MessageListener {

    @KafkaListener(topics = "${message.topic.name}", groupId = "one", containerFactory = "oneKafkaListenerContainerFactory")
    public void listenGroupOne(String message) {
        System.out.println(message);
    }

    @KafkaListener(topics = "${message.topic.name}", groupId = "two", containerFactory = "twoKafkaListenerContainerFactory")
    public void listenGroupTwo(String message) {
        System.out.println(message);
    }
}
