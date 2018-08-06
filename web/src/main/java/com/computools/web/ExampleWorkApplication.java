package com.computools.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
public class ExampleWorkApplication {
    public static void main(String[] args) {
//        new SpringApplicationBuilder(ExampleWorkApplication.class)
//                .web(WebApplicationType.NONE)
//                .run(args);
        SpringApplication.run(ExampleWorkApplication.class, args);
    }
}