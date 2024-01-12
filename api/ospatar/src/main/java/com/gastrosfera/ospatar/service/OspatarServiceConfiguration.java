package com.gastrosfera.ospatar.service;

import com.gastrosfera.ospatar.model.mapper.OspatarMapper;
import com.gastrosfera.ospatar.repository.OspatarRepository;
import com.gastrosfera.ospatar.service.internal.OspatarServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OspatarServiceConfiguration {

    @Bean
    public OspatarService ospatarService(OspatarRepository ospatarRepository, OspatarMapper ospatarMapper) {
        return new OspatarServiceImpl(ospatarRepository, ospatarMapper);
    }

}
