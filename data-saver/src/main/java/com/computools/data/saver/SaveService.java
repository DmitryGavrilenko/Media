package com.computools.data.saver;

import com.computools.audit.dao.ImageRepository;
import com.computools.audit.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;

@Service
public class SaveService {

    private ImageRepository imageRepository;
    private List<Image> images;
    @Autowired
    public SaveService(ImageRepository imageRepository){
        this.imageRepository = imageRepository;
        images = new CopyOnWriteArrayList<>();
    }


    @Async("threadPoolTaskExecutor")
    public Future<String> save(List<Image> images){
        imageRepository.saveAll(images);
        return CompletableFuture.completedFuture("Done");
    }

}
