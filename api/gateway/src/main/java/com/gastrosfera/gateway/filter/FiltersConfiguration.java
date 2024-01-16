package com.gastrosfera.gateway.filter;

import com.gastrosfera.gateway.filter.internal.JwtValidationFilter;
import com.gastrosfera.gateway.filter.internal.OncePerRequestFilter;
import com.gastrosfera.gateway.filter.internal.TokenRoleFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class FiltersConfiguration {

    @Bean
    public OncePerRequestFilter oncePerRequestFilter() {
        return new OncePerRequestFilter();
    }

    @Bean
    public JwtValidationFilter jwtValidationFilter(WebClient.Builder webClientBuilder) {
        return new JwtValidationFilter(webClientBuilder);
    }

    @Bean
    public TokenRoleFilter tokenRoleFilter(WebClient.Builder webClientBuilder) {
        return new TokenRoleFilter(webClientBuilder);
    }

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

}
