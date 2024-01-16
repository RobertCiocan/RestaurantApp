package com.gastrosfera.gateway.filter.internal;

import com.gastrosfera.shared.v1.base.ApiConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Configuration
public class JwtValidationFilter extends AbstractGatewayFilterFactory<Object> {

    private final WebClient webClient;

    // Define a list of paths to be excluded from JWT verification
    private final List<String> excludedPaths = Arrays.asList(
            ApiConstant.API_PATH + ApiConstant.V1_PATH + "/login",
            ApiConstant.API_PATH + ApiConstant.V1_PATH + "/register"
    );

    @Autowired
    public JwtValidationFilter(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8086").build();
    }

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            String requestedPath = exchange.getRequest().getPath().value();
            if (excludedPaths.contains(requestedPath)) {
                return chain.filter(exchange);
            }

            String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            System.out.println(token);

            return validateJwtWithIdm(token)
                    .flatMap(responseEntity -> {
                        if (responseEntity.getStatusCode().is2xxSuccessful()) {
                            return chain.filter(exchange);
                        } else {
                            exchange.getResponse().setStatusCode(responseEntity.getStatusCode());
                            return exchange.getResponse().writeWith(Mono.just(exchange.getResponse()
                                    .bufferFactory().wrap(responseEntity.getBody().getBytes())));
                        }
                    })
                    .onErrorResume(WebClientResponseException.class, e -> handleWebClientResponseException(exchange, e));
        };
    }

    private Mono<ResponseEntity<String>> validateJwtWithIdm(String token) {
        return webClient.get()
                .uri(ApiConstant.API_PATH + ApiConstant.V1_PATH + "/validate")
                .header(HttpHeaders.AUTHORIZATION, token)
                .retrieve()
                .toEntity(String.class)
                .map(responseEntity -> ResponseEntity.status(responseEntity.getStatusCode())
                        .body(responseEntity.getBody()));
    }

    private Mono<Void> handleWebClientResponseException(ServerWebExchange exchange, WebClientResponseException exception) {
        exchange.getResponse().setStatusCode(exception.getStatusCode());
        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse()
                .bufferFactory().wrap(exception.getResponseBodyAsByteArray())));
    }
}


