package com.yunseong.member.controller;

import com.yunseong.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class MemberController {

    private final DiscoveryClient discoveryClient;
    private final MemberService memberService;

    @PostMapping(value = "/members")
    public ResponseEntity<MemberResponse> createMember(@RequestBody MemberRequest request) {
        MemberResponse response = this.memberService.signUp(request);
        return ResponseEntity.created(this.getServiceUrl().orElseThrow()).body(response);
    }

    private Optional<URI> getServiceUrl() {
        List<ServiceInstance> list = this.discoveryClient.getInstances("member");
        if(list != null && list.size() > 0) {
            return Optional.of(list.get(0).getUri());
        }
        return Optional.empty();
    }
}
