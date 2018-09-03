package com.computools.web.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;
import com.computools.utils.MultipartFileImpl;

@Configuration
@EnableAutoConfiguration
@Import({AppConfig.class})
@ActiveProfiles("test")
public class TestConfig {

    @Bean
    public MultipartFile multipartFile(){
        return new MultipartFileImpl();
    }

}
