package com.yunseong.image.controller;

import com.yunseong.image.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@AllArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestPart(required = false, name = "file")MultipartFile[] files, Principal principal) {
        this.imageService.save(principal.getName(), files);
        return ResponseEntity.ok().build();
    }
}
