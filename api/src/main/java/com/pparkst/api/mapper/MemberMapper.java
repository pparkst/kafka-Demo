package com.pparkst.api.mapper;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.pparkst.api.domain.Member;
import com.pparkst.api.web.dto.MemberCreateRequestDto;
import com.pparkst.api.web.dto.MemberResponseDto;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Member toEntity(MemberCreateRequestDto memberCreateRequestDto);

    @AfterMapping
    default void init(@MappingTarget Member member){
        member.init();
    }

    MemberResponseDto toResponseDto(Member member);
    List<MemberResponseDto> toResponseDto(List<Member> members);
}
