package com.yunseong.store.controller;

import com.yunseong.store.domain.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class StoreResponse {

    private String name;
    private String description;
    private String notice;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;
    private Location location;
}
