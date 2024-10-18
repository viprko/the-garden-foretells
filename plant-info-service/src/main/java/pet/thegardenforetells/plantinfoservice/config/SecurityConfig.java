package pet.thegardenforetells.plantinfoservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final static String ADMIN_ROLE = "ADMIN";
    private final static String MANAGER_ROLE = "MANAGER";
    private final static String CLIENT_ROLE = "CLIENT";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((auth) ->
                        auth
                                .requestMatchers(HttpMethod.GET)
                                    .hasAnyRole(CLIENT_ROLE, MANAGER_ROLE, ADMIN_ROLE)
                                .requestMatchers(HttpMethod.POST)
                                    .hasAnyRole(MANAGER_ROLE, ADMIN_ROLE)
                                .requestMatchers(HttpMethod.PUT)
                                    .hasAnyRole(MANAGER_ROLE, ADMIN_ROLE)
                                .requestMatchers(HttpMethod.DELETE).hasRole(ADMIN_ROLE)
                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
