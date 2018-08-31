package config;

import com.computools.web.config.AppConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.multipart.MultipartFile;
import utils.MultipartFileImpl;

@Configuration
@EnableAutoConfiguration
@Import({AppConfig.class})
public class TestConfig {

    @Bean
    public MultipartFile multipartFile(){
        return new MultipartFileImpl();
    }

}
