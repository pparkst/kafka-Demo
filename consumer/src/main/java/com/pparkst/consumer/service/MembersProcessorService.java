package com.pparkst.consumer.service;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.pparkst.consumer.domain.Member;
import com.pparkst.common.MemberCreateRequestKafkaDto;
import com.pparkst.consumer.mapper.MemberMapper;
import com.pparkst.consumer.repository.MembersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MembersProcessorService {
    private final MembersRepository membersRepository;
    private final MemberMapper memberMapper;

    public boolean add(MemberCreateRequestKafkaDto memberCreateRequestKafkaDto) {
        Member member = memberMapper.toEntity(memberCreateRequestKafkaDto);
        member.init();

        if(membersRepository.findByAccount(member.getAccount()).isPresent()) {
            throw new DuplicateKeyException("이미 존재하는 계정입니다.");
        }

        member = membersRepository.save(member);        

        if(member == null || member.getId() == null) {
            return false;
        } else {
            return true;
        }
    }
}
