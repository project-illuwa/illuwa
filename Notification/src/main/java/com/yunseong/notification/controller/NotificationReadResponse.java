package com.yunseong.notification.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotificationReadResponse {

    private String sender;
    private String receiver;
    private String title;
    private String message;
}
