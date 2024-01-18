package com.gastrosfera.client.service;

import com.gastrosfera.client.model.mapper.ClientMapper;
import com.gastrosfera.client.repository.ClientRepository;
import com.gastrosfera.client.service.internal.ClientServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientServiceConfiguration {

    @Bean
    public ClientService clientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        return new ClientServiceImpl(clientRepository, clientMapper);
    }

}