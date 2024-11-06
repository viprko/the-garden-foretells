package pet.thegardenforetells.plantinfoservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pet.thegardenforetells.shared.config.SharedAuthConfig;

@Configuration
@Import(SharedAuthConfig.class)
public class AppConfig {
}
