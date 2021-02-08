package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class MessageProducer {
    @Autowired
    private KafkaTemplate<String, String> template;

    @Value(value = "${message.topic.name}")
    private String topicName;

    public void sendMessage(String str) {
        ListenableFuture<SendResult<String, String>> future = template.send(topicName, str);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Something went wrong...");
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println(str);
            }
        });
    }
}
