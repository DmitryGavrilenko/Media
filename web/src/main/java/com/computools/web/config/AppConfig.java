package com.computools.web.config;

import com.computools.audit.config.AuditConfig;
import com.computools.config.ServiceConfig;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//@ComponentScan(basePackages = { "com.computools.data.saver"})
@Import({AuditConfig.class, ServiceConfig.class
//        , ClientConfig.class
})
public class AppConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
