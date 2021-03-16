package com.yunseong.member.controller;

import com.yunseong.common.exception.InvalidValueException;
import com.yunseong.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final LoadBalancerClient loadBalancerClient;

    @PostMapping(value = "/members")
    public ResponseEntity<MemberResponse> createMember(@RequestBody MemberRequest request) throws Exception {
        MemberResponse response = this.memberService.signUp(request);
        return ResponseEntity.created(Optional.of(this.loadBalancerClient.choose("member").getUri()).orElseThrow(() -> new InvalidValueException("member 서비스가 중지된 상태입니다. 유레카 서버를 확인해주세요"))).body(response);
    }
}
