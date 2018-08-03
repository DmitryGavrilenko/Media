package com.computools.data.saver;

import com.computools.audit.dao.ImageRepository;
import com.computools.audit.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Service
public class SaveService {

    private ImageRepository imageRepository;

    @Autowired
    public SaveService(ImageRepository imageRepository, ExecutorService executor){
        this.imageRepository = imageRepository;
    }


    @Async
    public CompletableFuture<String> save(List<Image> images){
        imageRepository.saveAll(images);
        return CompletableFuture.completedFuture("Done");
    }

}
