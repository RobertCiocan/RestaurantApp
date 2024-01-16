package com.gastrosfera.produse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration;

@SpringBootApplication(exclude = {MustacheAutoConfiguration.class})
public class ProduseServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProduseServiceApplication.class, args);
    }
}
