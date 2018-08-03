package com.computools.data.saver;

import com.mediatype.examplework.ExampleWorkApplication;
import com.mediatype.examplework.dao.ImageRepository;
import com.mediatype.examplework.model.Image;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootApplication
@Import(ExampleWorkApplication.class)
public class Starter implements CommandLineRunner {

    private ImageRepository imageRepository;

    @Autowired
    public Starter(ImageRepository imageRepository){
        this.imageRepository = imageRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        long time;
        int poolSize = Runtime.getRuntime().availableProcessors()*2; // Absolutely right
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize); // You are working out of spring application, please replace it, and make work with @Async

        long timeStart = System.currentTimeMillis();

        for (int i = 0; i < poolSize; i++){
            executorService.submit(() -> {
                AtomicLong count = new AtomicLong();
                ConcurrentLinkedQueue<Image> images = new ConcurrentLinkedQueue<>();

                for(int c = 0; c < 125_000; c++) {
                    images.add(new Image("www"));
                    count.incrementAndGet();
                    if (count.get()%10_000 == 0 || count.get() == 125_000){
                        save(images);
                    }
                }
            });
        }



        while(!executorService.isTerminated()){
            Thread.sleep(10);
        } executorService.shutdown(); //While making a lot of trouble for performance You need to decrease resource consume for this part
        time = System.currentTimeMillis() - timeStart;
        System.out.println("Time : " + time + "ms" );

    }

    @Transactional
    public void save(ConcurrentLinkedQueue<Image> images){
        imageRepository.saveAll(images);
        images.clear();
    }
    public static void main(String[] args){
        SpringApplication.run(Starter.class, args);
    }
}
