package com.computools.client.config;

import com.computools.data.saver.StarterConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = "com.computools.client")
@Import(StarterConfig.class)
public class ClientConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
