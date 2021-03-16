package com.yunseong.member.domain;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;

@Getter
public class MemberDetails extends User {

    private long id;

    public MemberDetails(Member member) {
        super(member.getUsername(), member.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_" + member.getMemberGrade().name())));
        this.id = member.getMemberId();
    }
}
