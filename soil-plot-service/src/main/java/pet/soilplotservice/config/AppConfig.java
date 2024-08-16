package pet.soilplotservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.security.PublicKey;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public AtomicReference<PublicKey> publicKeyAtomicReference() {
        return new AtomicReference<PublicKey>();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
