package com.pparkst.api.web.dto;

import com.pparkst.api.enums.MemberEnum.Status;

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
public class MemberResponseDto {
    private String account;
    private String name;
    private Status status;
}
