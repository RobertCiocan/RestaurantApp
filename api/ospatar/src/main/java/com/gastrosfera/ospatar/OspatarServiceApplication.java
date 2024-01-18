package com.gastrosfera.ospatar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration;

@SpringBootApplication(exclude = {MustacheAutoConfiguration.class})
public class OspatarServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OspatarServiceApplication.class, args);
    }

}
