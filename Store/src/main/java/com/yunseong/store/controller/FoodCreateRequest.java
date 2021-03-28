package com.yunseong.store.controller;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FoodCreateRequest {

    private String name;
    private String description;
    private int price;
}
