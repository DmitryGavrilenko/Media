package com.computools.web.config;

import com.computools.audit.config.AuditConfig;
import com.computools.config.ServiceConfig;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.computools.web.controller"})
@Import({AuditConfig.class, ServiceConfig.class
//        , ClientConfig.class
})
public class AppConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
