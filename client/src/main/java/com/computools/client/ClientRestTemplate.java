package com.computools.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
public class ClientRestTemplate implements CommandLineRunner {

    private ClientSaveService clientSaveService;
    private int batchSize = 125_000;
    @Autowired
    public ClientRestTemplate(ClientSaveService clientSaveService){
        this.clientSaveService = clientSaveService;
    }


    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 8; i++){
            clientSaveService.save(batchSize);
        }
    }
}
