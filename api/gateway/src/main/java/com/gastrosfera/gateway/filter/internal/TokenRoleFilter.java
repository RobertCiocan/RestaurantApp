package com.gastrosfera.gateway.filter.internal;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gastrosfera.shared.v1.base.RoleEnum;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Component
public class TokenRoleFilter extends AbstractGatewayFilterFactory<Object> {
    private final WebClient webClient;

    public TokenRoleFilter(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8086").build();
    }

    @Override
    public GatewayFilter apply(Object object) {
        return (exchange, chain) -> {
            if (hasRequiredRole(exchange, "ROLE_ADMIN")) {
                return chain.filter(exchange);
            } else {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.FORBIDDEN);
                DataBuffer buffer = response.bufferFactory().wrap("Forbidden".getBytes());
                return response.writeWith(Mono.just(buffer));
            }
        };
    }

    private Mono<Map<String, String>> handleWebClientResponseException(WebClientResponseException exception) {
        return Mono.just(exception.getResponseBodyAsByteArray())
                .map(bytes -> {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        return objectMapper.readValue(bytes, new TypeReference<Map<String, String>>() {});
                    } catch (IOException e) {
                        e.printStackTrace();
                        return Collections.singletonMap("error", "Error parsing response");
                    }
                });
    }

    private boolean hasRequiredRole(ServerWebExchange exchange, String requiredRole) {
        String token = extractToken(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION));

        if (token != null) {
            return webClient.get()
                    .uri("/claims")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {})
                    .flatMap(response -> {
                        String roleFromJwt = response.get("role");
                        return Mono.just(roleFromJwt != null && roleFromJwt.equals(requiredRole));
                    })
                    .onErrorResume(WebClientResponseException.class, e -> handleWebClientResponseException(e)
                            .flatMap(errorResponse -> {
                                System.out.println("Error response: " + errorResponse);
                                return Mono.just(false);
                            }))
                    .blockOptional().orElse(false);
        }

        return false;
    }


    private String extractToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

}


