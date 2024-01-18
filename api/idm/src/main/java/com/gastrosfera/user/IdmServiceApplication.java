package com.gastrosfera.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration;

@SpringBootApplication(exclude = {MustacheAutoConfiguration.class})
public class IdmServiceApplication{

    public static void main(String[] args) {
        SpringApplication.run(IdmServiceApplication.class, args);
    }

}
