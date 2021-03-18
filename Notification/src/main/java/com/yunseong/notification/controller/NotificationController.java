package com.yunseong.notification.controller;

import com.yunseong.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "/notifications", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/{id}")
    public ResponseEntity<NotificationReadResponse> readMessage(Principal principal, @PathVariable long id) {
        NotificationReadResponse response = this.notificationService.readMessage(principal.getName(), id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/send")
    public ResponseEntity<PagedModel<NotificationResponse>> sendMessages(Principal principal) {
        PagedModel<NotificationResponse> pagedModel = getPage(this.notificationService.senderMessageList(principal.getName()));
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/receive")
    public ResponseEntity<PagedModel<NotificationResponse>> receiveMessages(Principal principal) {
        PagedModel<NotificationResponse> pagedModel = getPage(this.notificationService.receiverMessageList(principal.getName()));
        return ResponseEntity.ok(pagedModel);
    }

    @PostMapping
    public NotificationSendResponse sendMessage(Principal principal, @RequestBody NotificationSendRequest request) {
        return this.notificationService.sendMessage(principal.getName(), request);
    }

    private PagedModel<NotificationResponse> getPage(Page<NotificationResponse> page) {
        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages());
        return PagedModel.of(page.getContent(), metadata);
    }
}
