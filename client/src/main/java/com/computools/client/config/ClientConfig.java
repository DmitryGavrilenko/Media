package com.computools.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.concurrent.Executor;

@Configuration
@ComponentScan(basePackages = "com.computools.client")
@EnableAsync
public class ClientConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public Resource staticResource(){
        return new FileSystemResource(new File("images/lake.jpg"));
    }


    @Bean
    public Executor executor(){
        int poolSize = Runtime.getRuntime().availableProcessors() * 25;
        ThreadPoolTaskExecutor threadPoolTaskExecutor =  new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(poolSize);
        return threadPoolTaskExecutor;
    }
}
