package com.computools.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.computools.dto","com.computools.service"})
public class ServiceConfig {
}
