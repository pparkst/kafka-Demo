package com.pparkst.api.web.controller;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pparkst.api.service.KafkaService;
import com.pparkst.common.MemberCreateRequestKafkaDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka/members")
public class MemberKafkaController {
    private final KafkaService<MemberCreateRequestKafkaDto> memberKafkaService;

    @PostMapping("/add")
    public CompletableFuture<ResponseEntity<Map<String, String>>> addMember(@RequestBody MemberCreateRequestKafkaDto memberCreateRequestKafkaDto) {
        System.out.println(memberCreateRequestKafkaDto.toString());
        CompletableFuture<SendResult<String, MemberCreateRequestKafkaDto>> future = memberKafkaService.sendMessage(memberCreateRequestKafkaDto);

        return future.handle((result, ex) -> {
            if (ex == null) {
                log.info("kafka 전송 성공 TOPIC: '{}' / offset: '{}' ", result.getRecordMetadata().topic(), result.getRecordMetadata().offset());
                return ResponseEntity.ok(
                Map.of("status", "SUCCESS", "message", "메시지 전송 요청이 성공적으로 처리되었습니다.")
            );
            } else {
                log.error("kafka 전송 실패 TOPIC: '{}' / commandNo: '{}' / err_message: '{}' ", result.getRecordMetadata().topic(), memberCreateRequestKafkaDto.getCommandNo(), ex.getMessage());
                return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("status", "FAILURE", "message", ex.getMessage()));
            }
            
        });
    }


}
