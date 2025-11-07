package com.pparkst.api.web.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pparkst.api.service.testKafkaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka/test")
public class testKafkaController {
    private final testKafkaService testKafkaService;

    @PostMapping("")
    public CompletableFuture<ResponseEntity<String>> test(@RequestBody String testMessage) {
        CompletableFuture<SendResult<String, String>> future = testKafkaService.sendMessage(testMessage);

        log.info("kafka/test RequestBody '{}'", testMessage);

        return future.handle((result, ex) -> {
            if (ex == null) {
                log.info("kafka 전송 성공 TOPIC: '{}' / offset: '{}' ", result.getRecordMetadata().topic(), result.getRecordMetadata().offset());
            } else {
                log.error("kafka 전송 실패 TOPIC: '{}' / commandNo: '{}' / err_message: '{}' ", result.getRecordMetadata().topic(), 9898989, ex.getMessage());
                throw new RuntimeException("kafka 전송 실패");
            }

            return ResponseEntity.ok("전송 성공.");
        });
    }
}
