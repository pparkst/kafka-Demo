package com.pparkst.api.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberKafkaService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC_NAME = "my-topic";

    public CompletableFuture<SendResult<String, String>> sendMessage(String message) {
        log.info("Kafka Producer - Topic : '{}' / messqge : '{}", TOPIC_NAME, message);
        return kafkaTemplate.send(TOPIC_NAME, message);
    }

    // public CompletableFuture<SendResult<String, String>> sendMessage(T data) {
    //     log.info("Kafka Producer - Topic : '{}' / messqge : '{}", TOPIC_NAME, data.toString());
    //     return kafkaTemplate.send(TOPIC_NAME, data);
    // }
}
