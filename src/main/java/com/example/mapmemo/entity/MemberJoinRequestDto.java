package com.example.mapmemo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberJoinRequestDto {
    private String loginId;
    private String password;
    private String email;
    private String name;
}
