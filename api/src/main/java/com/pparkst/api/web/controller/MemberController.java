package com.pparkst.api.web.controller;

import org.springframework.web.bind.annotation.RestController;

import com.pparkst.api.enums.MemberEnum.Status;
import com.pparkst.api.service.MemberService;
import com.pparkst.api.web.dto.MemberCreateRequestDto;
import com.pparkst.api.web.dto.MemberResponseDto;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/kafkaapi/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getMember(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<MemberResponseDto>> getMemberList(@RequestParam Status status) {
        List<MemberResponseDto> memberResponseDtos = memberService.getMemberList(status);
        return ResponseEntity.ok(memberResponseDtos);
    }
    
    @PostMapping()
    public ResponseEntity<MemberResponseDto> addMember(@RequestBody MemberCreateRequestDto memberCreateRequestDto) {
        MemberResponseDto memberResponseDto = memberService.add(memberCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberResponseDto);
    }

}
