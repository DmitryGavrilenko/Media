package com.computools.data.saver;

import com.mediatype.examplework.ExampleWorkApplication;
import com.mediatype.examplework.dao.ImageRepository;
import com.mediatype.examplework.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@ComponentScan(basePackageClasses = ImageRepository.class)
public class Starter implements CommandLineRunner {

    private ImageRepository imageRepository;

    @Autowired
    public Starter(ImageRepository imageRepository){
        this.imageRepository = imageRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        int poolSize = Runtime.getRuntime().availableProcessors()*2;
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);

        long timeStart = System.currentTimeMillis();
        for (int i = 0; i < 125_000; i++){
            executorService.submit(() -> {
                imageRepository.save(new Image("www"));
            });
        }
        System.out.println("Time : " + (System.currentTimeMillis() - timeStart) + "ms");


    }
}
