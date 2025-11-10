package com.pparkst.consumer.consumer;

import java.util.Map;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.pparkst.consumer.dto.MemberCreateRequestKafkaDto;
import com.pparkst.consumer.service.MembersProcessorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MembersConsumer {
    // DTO Null Check
    private final MembersProcessorService membersProcessorService;

    @KafkaListener(
        topics = "members-add",
        groupId = "demokafka-consumer-group",
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void addConsume(String key, MemberCreateRequestKafkaDto memberCreateRequestKafkaDto, @Header Map<String, Object> headers) {
        log.info("Kafka Message Received ==> Key: '{}', message: '{}", key, memberCreateRequestKafkaDto);
        membersProcessorService.add(memberCreateRequestKafkaDto);
    }
}
