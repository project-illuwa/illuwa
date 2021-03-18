package com.yunseong.notification.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotificationResponse {

    private long id;
    private String title;
    private String sender;
    private String receiver;
}
