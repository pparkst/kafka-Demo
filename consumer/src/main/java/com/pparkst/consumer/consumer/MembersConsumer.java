package com.pparkst.consumer.consumer;

import java.util.Map;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import com.pparkst.common.MemberCreateRequestKafkaDto;
import com.pparkst.consumer.service.MembersProcessorService;
import org.springframework.kafka.support.Acknowledgment;

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
    public void addConsume(MemberCreateRequestKafkaDto memberCreateRequestKafkaDto, Acknowledgment ack, @Headers Map<String, Object> headers) {
        try {
            log.info("Kafka Message Received ==> message: '{}", memberCreateRequestKafkaDto.toString());
            boolean isSuccess = false;
            isSuccess = membersProcessorService.add(memberCreateRequestKafkaDto);

            if(isSuccess) {
                //commit
                ack.acknowledge();
            } else {
                log.error("DB 등록에 실패하였습니다.");
            }

        } catch (DuplicateKeyException duplicateKeyException) {
            System.out.println("DuplicateKey Exception 발생!");
            System.out.println("해당 예외는 커밋 처리하고 패스합니다.");
            ack.acknowledge();

        } catch (Exception ex) {
            System.out.println("Exception 발생! ");
            System.out.println(ex.getClass().getName());
            log.error(ex.getMessage());
        }
    }
}
