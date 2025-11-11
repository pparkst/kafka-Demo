package com.pparkst.consumer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pparkst.consumer.domain.Member;
import com.pparkst.common.MemberCreateRequestKafkaDto;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Member toEntity(MemberCreateRequestKafkaDto memberCreateRequestKafkaDto);
}
