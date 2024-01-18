package com.gastrosfera.rezervare.service;

import com.gastrosfera.rezervare.model.mapper.ReserveMapper;
import com.gastrosfera.rezervare.repository.ReserveRepository;
import com.gastrosfera.rezervare.service.internal.ReserveServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReserveServiceConfiguration {
    @Bean
    public ReserveService reserveService(ReserveRepository reserveRepository, ReserveMapper reserveMapper){
        return new ReserveServiceImpl(reserveRepository, reserveMapper);
    }
}
