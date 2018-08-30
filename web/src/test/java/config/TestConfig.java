package config;

import com.computools.audit.config.AuditConfig;
import com.computools.config.ServiceConfig;
import com.computools.web.ExampleWorkApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration
@Import({AuditConfig.class})
public class TestConfig {



}
