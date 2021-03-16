package com.yunseong.member.controller;

import com.yunseong.member.domain.Member;
import com.yunseong.member.domain.MemberGrade;
import lombok.Getter;

@Getter
public class MemberResponse {

    private String username;
    private String nickname;
    private MemberGrade grade;

    public MemberResponse(Member member) {
        this.username = member.getUsername();
        this.nickname = member.getNickname();
        this.grade = member.getMemberGrade();
    }
}
