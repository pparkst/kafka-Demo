package com.pparkst.api.web.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pparkst.api.service.MemberKafkaService;
import com.pparkst.api.web.dto.MemberCreateRequestKafkaDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka/members")
public class MemberKafkaController {

    private final MemberKafkaService memberKafkaService;

    @PostMapping("/test")
    public CompletableFuture<ResponseEntity<String>> test(@RequestBody String testMessage) {
        CompletableFuture<SendResult<String, String>> future = memberKafkaService.sendMessage(testMessage);

        return future.handle((result, ex) -> {
            if (ex == null) {
                log.info("kafka 전송 성공 TOPIC: '{}' / offset: '{}' ", result.getRecordMetadata().topic(), result.getRecordMetadata().offset());
            } else {
                log.error("kafka 전송 실패 TOPIC: '{}' / commandNo: '{}' / err_message: '{}' ", result.getRecordMetadata().topic(), 9898989, ex.getMessage());
                throw new RuntimeException("kafka 전송 실패");
            }

            return ResponseEntity.ok("처리중 입니다.");
        });
    }

    @PostMapping("/add")
    public CompletableFuture<ResponseEntity<String>> addMember(@RequestBody MemberCreateRequestKafkaDto memberCreateRequestKafkaDto) {
        CompletableFuture<SendResult<String, String>> future = memberKafkaService.sendMessage(memberCreateRequestKafkaDto.getName());
        //CompletableFuture<SendResult<String, MemberCreateRequestKafkaDto>> future = memberKafkaService.sendMessage(memberCreateRequestKafkaDto);

        return future.handle((result, ex) -> {
            if (ex == null) {
                log.info("kafka 전송 성공 TOPIC: '{}' / offset: '{}' ", result.getRecordMetadata().topic(), result.getRecordMetadata().offset());
            } else {
                log.error("kafka 전송 실패 TOPIC: '{}' / commandNo: '{}' / err_message: '{}' ", result.getRecordMetadata().topic(), memberCreateRequestKafkaDto.getCommandNo(), ex.getMessage());
                throw new RuntimeException("kafka 전송 실패");
            }

            return ResponseEntity.ok("처리중 입니다.");
        });
    }


}
