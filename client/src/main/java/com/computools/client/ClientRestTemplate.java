package com.computools.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

//@Component
public class ClientRestTemplate implements CommandLineRunner {

    private ClientSaveService clientSaveService;
    private int batchSize = 125_000;
    @Autowired
    public ClientRestTemplate(ClientSaveService clientSaveService){
        this.clientSaveService = clientSaveService;
    }


    @Override
    public void run(String... args) throws Exception {
        long startTime = System.currentTimeMillis();
        List<Future<String>> futures = new ArrayList<>();

        for (int i = 0; i < 100; i++){
            futures.add(clientSaveService.save(batchSize));
        }

        futures.forEach((result) -> {
            try {
                result.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        long timeEnd = System.currentTimeMillis() - startTime;
        System.out.println("time to Query Processing  :  " + timeEnd);
    }
}
