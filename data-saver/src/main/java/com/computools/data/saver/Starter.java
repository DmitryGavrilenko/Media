package com.computools.data.saver;

import com.mediatype.examplework.dao.ImageRepository;
import com.mediatype.examplework.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Cacheable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

//@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.mediatype.examplework")
public class Starter implements CommandLineRunner {

    private ImageRepository imageRepository;

    private AtomicLong count;

    @Autowired
    public Starter(ImageRepository imageRepository){
        this.imageRepository = imageRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        count = new AtomicLong();
        int poolSize = Runtime.getRuntime().availableProcessors()*2;
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        ConcurrentLinkedQueue<Image> images = new ConcurrentLinkedQueue<>();

        long timeStart = System.currentTimeMillis();
        for (int i = 0; i < 8; i++){
            executorService.submit(() -> {
                for(int c = 0; c < 125_000; c++) {
                    imageRepository.save(new Image("www"));
                    count.incrementAndGet();
                }
                imageRepository.flush();
            });
        }

        while(!executorService.isTerminated()) executorService.shutdown();
        System.out.println("Time : " + (System.currentTimeMillis() - timeStart) + "ms" +
                " count: " + count.get());

    }
    public static void main(String[] args){
        SpringApplication.run(Starter.class, args);
    }
}
