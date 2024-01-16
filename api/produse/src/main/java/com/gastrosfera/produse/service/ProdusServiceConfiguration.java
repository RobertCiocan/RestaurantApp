package com.gastrosfera.produse.service;

import com.gastrosfera.produse.model.mapper.ProdusMapper;
import com.gastrosfera.produse.repository.ProdusRepository;
import com.gastrosfera.produse.service.internal.ProdusServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdusServiceConfiguration {
    @Bean
    public ProdusService produsService(ProdusRepository produsRepository, ProdusMapper produsMapper){
        return new ProdusServiceImpl(produsRepository, produsMapper);
    }
}
