package com.pparkst.api.service;

import com.pparkst.api.urtil.KafkaTopicProperties;

import org.springframework.stereotype.Service;
import com.pparkst.common.MemberCreateRequestKafkaDto;
import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaService<T> {
    private final KafkaTemplate<String, T> kafkaTemplate;
    private final KafkaTopicProperties kafkaTopicProperties;

    public CompletableFuture<SendResult<String, T>> sendMessage(T data) {
        String topicName;

        switch (data) {
            case MemberCreateRequestKafkaDto memberCreateRequestKafkaDto -> {
                topicName = kafkaTopicProperties.getMembersAdd();
            }
                
            default -> {
                topicName = kafkaTopicProperties.getTest();
            }
        }

        log.info("Kafka Producer - Topic : '{}' / messqge : '{}", topicName, data.toString());
        return kafkaTemplate.send(topicName, data);
    }
}
