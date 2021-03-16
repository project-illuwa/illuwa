package com.yunseong.member.controller;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberRequest {

    private String username;
    private String password;
    private String nickname;
}
