package com.yunseong.store.service;

import com.yunseong.common.exception.NotFoundKeyException;
import com.yunseong.store.controller.FoodCreateRequest;
import com.yunseong.store.controller.StoreCreateRequest;
import com.yunseong.store.controller.StoreResponse;
import com.yunseong.store.controller.StoreUpdateRequest;
import com.yunseong.store.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final FoodRepository foodRepository;
    private final ImageRepository imageRepository;

    public void saveStore(StoreCreateRequest request) {
        Store store = new Store(request.getName(), request.getDescription(), request.getNotice(), request.getMinTime(), request.getMaxTime());
        for(String str : request.getUri()) {
            Image image = new Image(str);
            image.setStore(store);
            this.imageRepository.save(image);
        }
        this.storeRepository.save(store);
    }

    public void updateStore(long id, StoreUpdateRequest request) {
        Store store = this.storeRepository.findById(id).orElseThrow(() -> new NotFoundKeyException("존재하지 않는 메시지ID입니다."));
        store.update(request);
    }

    public void updateLocation(long id, Location location) {
        Store store = this.storeRepository.findById(id).orElseThrow(() -> new NotFoundKeyException("존재하지 않는 메시지ID입니다."));
        store.setLocation(location);
    }

    public void saveFood(long id, FoodCreateRequest request) {
        Store store = this.storeRepository.findById(id).orElseThrow(() -> new NotFoundKeyException("존재하지 않는 메시지ID입니다."));
        this.foodRepository.save(new Food(request.getName(), request.getDescription(), request.getPrice(), store));
    }

    @Transactional(readOnly = true)
    public void deleteFood(long id) {
        this.foodRepository.findById(id).orElseThrow(() -> new NotFoundKeyException("존재하지 않는 메시지ID입니다.")).delete();
    }

    @Transactional(readOnly = true)
    public StoreResponse findStore(long id) {
        Store store = this.storeRepository.findById(id).orElseThrow(() -> new NotFoundKeyException("존재하지 않는 메시지ID입니다."));
        return null;
    }
}
