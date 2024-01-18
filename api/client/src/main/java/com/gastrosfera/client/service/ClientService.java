package com.gastrosfera.client.service;


import com.gastrosfera.shared.v1.client.dto.ClientDTO;

import java.util.List;

public interface ClientService {
    ClientDTO createClient(ClientDTO clientDTO);
    ClientDTO getClient(Long id);
    List<ClientDTO> getClients();
}