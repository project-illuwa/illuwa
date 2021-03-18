package com.yunseong.member.service;

import com.yunseong.member.controller.MemberRequest;
import com.yunseong.member.controller.MemberResponse;
import com.yunseong.member.domain.Member;
import com.yunseong.member.domain.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    
    @Test
    @DisplayName("로그인 테스트")
    void signIn(@Mock MemberRepository memberRepository) {
        //given
        Member member = new Member("jys", "pw", "name");
        MemberRequest request = new MemberRequest(member.getUsername(), member.getPassword(), member.getNickname());
        MemberService memberService = new MemberService(memberRepository);
        //when
        when(memberService.signUp(request)).thenReturn(Optional.of(new MemberResponse(member)));
        //then
        assertNotNull(memberService.signUp(request));
    }
}