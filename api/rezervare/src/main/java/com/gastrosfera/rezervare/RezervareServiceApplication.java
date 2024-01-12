package com.gastrosfera.rezervare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration;

@SpringBootApplication(exclude = {MustacheAutoConfiguration.class})
public class RezervareServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RezervareServiceApplication.class, args);
    }
}
