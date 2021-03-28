package com.yunseong.store.controller;

import com.yunseong.store.domain.Location;
import com.yunseong.store.service.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/stores", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody StoreCreateRequest request) {
        this.storeService.saveStore(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody StoreUpdateRequest request) {
        this.storeService.updateStore(id, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/location")
    public ResponseEntity<?> location(@PathVariable long id, @RequestBody Location location) {
        this.storeService.updateLocation(id, location);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/food")
    public ResponseEntity<?> addFood(@PathVariable long id, @RequestBody FoodCreateRequest request) {
        this.storeService.saveFood(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/foods/{id}")
    public ResponseEntity<?> deleteFood(@PathVariable long id) {
        this.storeService.deleteFood(id);
        return ResponseEntity.ok().build();
    }
}
