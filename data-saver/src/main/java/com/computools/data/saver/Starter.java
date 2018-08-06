package com.computools.data.saver;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/*
* Common comments.
* 1. If you are creating async configuration, please ensure what such configuration has only one instance
* 2. If you have`t any additional work separated by type of exception please create one exception handling part(line 43)
* */

@Component
public class Starter implements CommandLineRunner {

    private SaveService saveService;

    public Starter(SaveService saveService){
        this.saveService = saveService;
    }

    @Override
    public void run(String... args) throws Exception {

        long time;
        long timeStart = System.currentTimeMillis();

        int dataSize = 1_000_000;
        int batchSize = 10_000;
        int endIndex = dataSize / batchSize;
        List<Future<String>> futures = new ArrayList<>();

        for(int i = 0; i < endIndex; i++) {
            futures.add(saveService.save(batchSize));
        }

        futures.forEach((result) -> {
            try {
                result.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        time = System.currentTimeMillis() - timeStart;
        System.out.println("Time : " + time + "ms" );

    }


}
