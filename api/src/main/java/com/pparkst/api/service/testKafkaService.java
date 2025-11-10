package com.pparkst.api.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class testKafkaService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC_NAME = "my-topic";


    public CompletableFuture<SendResult<String, String>> sendMessage(String message) {
        log.info("Kafka Producer - Topic : '{}' / message : '{}'", TOPIC_NAME, message);
        return kafkaTemplate.send(TOPIC_NAME, message);
    }
    
}
