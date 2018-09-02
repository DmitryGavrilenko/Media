package com.computools.web;

import com.computools.web.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


public class ExampleWorkApplication {
    public static void main(String[] args) {
//        new SpringApplicationBuilder(ExampleWorkApplication.class)
//                .web(WebApplicationType.NONE)
//                .run(args);
        SpringApplication.run(AppConfig.class, args);
    }
}