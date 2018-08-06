package com.computools.data.saver;
import com.computools.audit.model.Image;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class VictorStarter implements CommandLineRunner {
    private final VictorBatchService victorBatchService;
    public static void main(String[] args) {
        new SpringApplicationBuilder(VictorStarter.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
    @Override
    public void run(String... args) throws Exception {
        long start = System.currentTimeMillis();
        List<Image> images = new ArrayList<>();
        for (int i = 0; i < 1000000; i++){
            images.add(new Image("werwer"));
        }
        int batchSize = 10000;
        int maxIndex = images.size() / batchSize;
        List<CompletableFuture> futureList = new ArrayList<>();
        int toIndex = batchSize;
        for (int i = 0; i < maxIndex ; i++){
            if ((maxIndex  - i) != 1) {
                futureList.add(victorBatchService.batchSave(images.subList(i * batchSize, toIndex)));
            } else futureList.add(victorBatchService.batchSave(images.subList(i * batchSize, images.size())));
            toIndex += batchSize;
        }
        futureList.forEach(item -> {
            try {
                item.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        System.out.println("Time for saving 1_000_000 record -> " + (System.currentTimeMillis() - start));
    }
    public VictorStarter(VictorBatchService victorBatchService) {
        this.victorBatchService = victorBatchService;
    }
}