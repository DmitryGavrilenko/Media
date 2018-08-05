package com.computools.data.saver;

import com.computools.audit.dao.ImageRepository;
import com.computools.audit.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class Starter implements CommandLineRunner {

    private List<Image> images;
    private SaveService saveService;

//    @Autowired
//    private  ImageRepository imageRepository;

    public Starter(SaveService saveService){
        this.saveService = saveService;
    }

    @Override
    public void run(String... args) throws Exception {

        long time;
        int poolSize = Runtime.getRuntime().availableProcessors()*2; // Absolutely right
//        ExecutorService executorService = Executors.newFixedThreadPool(poolSize); // You are working out of spring application, please replace it, and make work with @Async
//
        long timeStart = System.currentTimeMillis();
//
//        for (int i = 0; i < poolSize; i++){
//            executorService.submit(() -> {
//                AtomicLong count = new AtomicLong();
//                ConcurrentLinkedQueue<Image> images = new ConcurrentLinkedQueue<>();
//
//                for(int c = 0; c < 125_000; c++) {
//                    images.add(new Image("www"));
//                    count.incrementAndGet();
//                    if (count.get()%10_000 == 0 || count.get() == 125_000){
//                        save(images);
//                    }
//                }
//            });
//        }
//
//
//        while(!executorService.isTerminated()){executorService.shutdown(); }  //While making a lot of trouble for performance You need to decrease resource consume for this part

        //TODO =============================================================================================
        int batchSize = 10_000;
        images = new ArrayList<>();
        List<Future<String>> futures = new ArrayList<>();
        for(int i = 0; i < 1_000_000; i++){
            images.add(new Image("dsg"));
        }
        int endIndex = images.size() / batchSize;
        int toIndex = batchSize;

        for(int i = 0; i < endIndex; i++){
            if((endIndex - i) != 1) futures.add(saveService.save(images.subList(i * batchSize, toIndex)));
            else futures.add(saveService.save(images.subList(i*batchSize, images.size())));
            toIndex += batchSize;
        }

        futures.stream().forEach((result) -> {
            try {
                result.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
//
        time = System.currentTimeMillis() - timeStart;
        System.out.println("Time : " + time + "ms" );

    }

//    @Transactional
//    public void save(ConcurrentLinkedQueue<Image> images){
//        imageRepository.saveAll(images);
//        images.clear();
//    }

}
