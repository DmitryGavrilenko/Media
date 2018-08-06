package com.computools.data.saver;

import com.computools.audit.dao.ImageRepository;
import com.computools.audit.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class SaveService {

    private ImageRepository imageRepository;
    @Autowired
    public SaveService(ImageRepository imageRepository){
        this.imageRepository = imageRepository;
    }


    @Async("threadPoolTaskExecutor")
    public Future<String> save(int batchSize){
        List<Image>images = new ArrayList<>();
        for(int i = 0; i < batchSize; i++){
            images.add(new Image("fsdf"));
        }
        imageRepository.saveAll(images);
        return CompletableFuture.completedFuture("Done");
    }

}
