package com.gastrosfera.shared.v1.configuration;

import com.gastrosfera.shared.v1.audit.EntityAuditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef="auditorProvider")
public class JpaConfiguration {

    @Bean
    AuditorAware<String> auditorProvider() {
        return new EntityAuditor();
    }

}
