package com.yunseong.notification.controller;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class NotificationSendRequest {

    private String title;
    private String message;
    private String receiver;
}
