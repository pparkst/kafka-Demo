package com.pparkst.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberCreateRequestKafkaDto {
    private Long commandNo;
    private String account;
    private String password;
    private String name;
}
