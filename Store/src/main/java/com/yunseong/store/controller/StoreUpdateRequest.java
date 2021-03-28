package com.yunseong.store.controller;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreUpdateRequest {

    private String name;
    private String description;
    private String notice;
    @DateTimeFormat(pattern = "H:mm")
    private LocalDateTime minTime;
    @DateTimeFormat(pattern = "H:mm")
    private LocalDateTime maxTime;
}
