package com.computools.data.saver;

import com.computools.audit.dao.ImageRepository;
import com.computools.audit.model.Image;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class VictorBatchService {
    private final ImageRepository repository;

    @Async
    public CompletableFuture<String> batchSave(List<Image> images){
        repository.saveAll(images);
        return CompletableFuture.completedFuture("Complete");
    }


    public VictorBatchService(ImageRepository repository) {
        this.repository = repository;
    }
}