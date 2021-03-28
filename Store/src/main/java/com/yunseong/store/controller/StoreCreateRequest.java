package com.yunseong.store.controller;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreCreateRequest {

    private String name;
    private String description;
    private String notice;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime minTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime maxTime;
    private List<String> uri;
}
