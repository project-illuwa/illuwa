package com.yunseong.notification.service;

import com.yunseong.common.exception.NotFoundKeyException;
import com.yunseong.common.exception.NotMatchedUserException;
import com.yunseong.notification.controller.NotificationReadResponse;
import com.yunseong.notification.controller.NotificationResponse;
import com.yunseong.notification.controller.NotificationSendRequest;
import com.yunseong.notification.controller.NotificationSendResponse;
import com.yunseong.notification.domain.Notification;
import com.yunseong.notification.domain.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationSendResponse sendMessage(String sender, NotificationSendRequest request) {
        Notification savedNotification = this.notificationRepository.save(new Notification(request.getTitle(), request.getMessage(), sender, request.getReceiver()));
        return new NotificationSendResponse(savedNotification.getReceiver());
    }

    public NotificationReadResponse readMessage(String receiver, long id) {
        Notification notification = this.notificationRepository.findById(id).orElseThrow(() -> new NotFoundKeyException("존재하지 않는 메시지ID입니다."));
        if(notification.isOwner(receiver)) throw new NotMatchedUserException("해당메일의 수신자 정보와 일치하지 않습니다.");
        notification.read();
        return new NotificationReadResponse(notification.getSender(), notification.getReceiver(), notification.getTitle(), notification.getMessage());
    }

    public Page<NotificationResponse> senderMessageList(String sender) {
        return this.notificationRepository.findAllBySender(sender);
    }

    public Page<NotificationResponse> receiverMessageList(String receiver) {
        return this.notificationRepository.findAllByReceiver(receiver);
    }
}
