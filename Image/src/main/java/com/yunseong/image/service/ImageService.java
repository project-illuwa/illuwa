package com.yunseong.image.service;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Transactional
public class ImageService {

    private final Resource resource = new DefaultResourceLoader().getResource("file:D:/Springboot/[20201007]Third_Project/board/src/main/resources/images/");

    public void save(long id, MultipartFile[] files) {
        if(files != null && files.length > 0) {
            try {
                Path path = this.resource.getFile().toPath();
                Files.createDirectory(path.resolve(String.valueOf(id)));
                for (MultipartFile file : files)
                    Files.copy(file.getInputStream(), path.resolve(id + "/" + file.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
