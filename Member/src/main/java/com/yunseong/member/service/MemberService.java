package com.yunseong.member.service;

import com.yunseong.member.controller.MemberRequest;
import com.yunseong.member.controller.MemberResponse;
import com.yunseong.member.domain.Member;
import com.yunseong.member.domain.MemberDetails;
import com.yunseong.member.domain.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public MemberResponse signUp(MemberRequest memberRequest) {
        Member savedMember = this.memberRepository.save(new Member(memberRequest.getUsername(), memberRequest.getPassword(), memberRequest.getNickname()));
        return new MemberResponse(savedMember);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        Member member = this.memberRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("해당 계정을 가진 유저가 존재하지않습니다."));
        return new MemberDetails(member);
    }
}
