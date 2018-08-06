package com.computools.data.saver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Configuration
@EnableAsync
@ComponentScan(basePackageClasses = {SaveService.class})
public class StarterConfig {

    @Bean
    public Executor executor(){
        int poolSize = Runtime.getRuntime().availableProcessors()*2;
        ThreadPoolTaskExecutor threadPoolTaskExecutor =  new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(poolSize);
        return threadPoolTaskExecutor;
    }

}
