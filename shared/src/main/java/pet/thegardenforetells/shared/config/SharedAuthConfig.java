package pet.thegardenforetells.shared.config;

import java.security.PublicKey;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pet.thegardenforetells.shared.util.JwtAuthFilter;
import pet.thegardenforetells.shared.util.JwtUtil;

@Configuration
public class SharedAuthConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public AtomicReference<PublicKey> atomicReference() {
        return new AtomicReference<>();
    }

    @Bean
    public JwtUtil jwtUtil(@Value("${shared.jwt-util.claims.user-id:userId}")
                           String userIdClaimName,
                           @Value("${shared.jwt-util.claims.role:role}")
                           String userAuthoritiesClaimName,
                           @Value("${shared.jwt-util.public-key-url}")
                           String publicKeyUrl,
                           AtomicReference<PublicKey> publicKeyReference,
                           RestTemplate restTemplate) {
        return new JwtUtil(userIdClaimName, userAuthoritiesClaimName, publicKeyUrl,
                publicKeyReference, restTemplate);
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter(JwtUtil jwtUtil) {
        return new JwtAuthFilter(jwtUtil);
    }
}
