package pet.thegardenforetells.plantinfoservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pet.thegardenforetells.shared.util.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Value("${auth.roles.admin:admin}")
    private String ADMIN_ROLE;
    @Value("${auth.roles.manager:manger}")
    private String MANAGER_ROLE;
    @Value("${auth.roles.client:client}")
    private String CLIENT_ROLE;
    private final JwtAuthFilter jwtAuthFilter;

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
        return httpSecurity.build();
    }
}
