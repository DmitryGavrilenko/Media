package com.computools.data.saver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Configuration
@EnableAsync
public class StarterConfig {

    @Bean
    public ExecutorService executor(){
        int poolSize = Runtime.getRuntime().availableProcessors()*2;
        return Executors.newFixedThreadPool(poolSize);
    }

}
