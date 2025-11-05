package com.pparkst.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pparkst.api.domain.Member;
import com.pparkst.api.enums.MemberEnum.Status;
import com.pparkst.api.mapper.MemberMapper;
import com.pparkst.api.repository.MemberRepository;
import com.pparkst.api.web.dto.MemberCreateRequestDto;
import com.pparkst.api.web.dto.MemberResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;

    public MemberResponseDto add(MemberCreateRequestDto memberCreateRequestDto) {
        Member member = memberMapper.toEntity(memberCreateRequestDto);
        memberRepository.save(member);
        return memberMapper.toResponseDto(member);
    }

    public MemberResponseDto getMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("존재하지 않습니다."));
        return memberMapper.toResponseDto(member);
    }

    public List<MemberResponseDto> getMemberList(Status status) {
        List<Member> memberList = memberRepository.findByStatus(status);
        return memberMapper.toResponseDto(memberList);
        
    }
}
