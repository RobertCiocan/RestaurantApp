package com.gastrosfera.rezervare.configuration;

import com.gastrosfera.shared.v1.configuration.JpaConfiguration;
import com.gastrosfera.shared.v1.serialization.SerializationConfig;
import jakarta.persistence.Entity;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"com.gastrosfera.shared.v1"})
@EntityScan(basePackages = {"com.gastrosfera.rezervare.model"})
@EnableJpaRepositories(basePackages = {"com.gastrosfera.rezervare.repository"})
@Import(value = {JpaConfiguration.class, SerializationConfig.class})
public class ServiceConfiguration {

}
