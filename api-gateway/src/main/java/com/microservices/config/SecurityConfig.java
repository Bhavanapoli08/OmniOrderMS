package com.microservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/eureka/**").permitAll()    // Allow Eureka UI & registry
                        .pathMatchers("/actuator/**").permitAll()  // Optional: Allow actuator
                        .anyExchange().authenticated()             // All other endpoints need JWT
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt());     // Enable JWT Resource Server

        return serverHttpSecurity.build();
    }
}
