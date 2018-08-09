import com.computools.client.config.ClientConfig;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executor;

@SpringBootApplication
public class Starter {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ClientConfig.class).web(WebApplicationType.NONE)
                .run(args);
    }
}
