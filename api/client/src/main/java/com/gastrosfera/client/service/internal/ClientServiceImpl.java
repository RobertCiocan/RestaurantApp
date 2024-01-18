package com.gastrosfera.client.service.internal;

import com.gastrosfera.client.service.ClientService;
import com.gastrosfera.client.model.Client;
import com.gastrosfera.client.model.mapper.ClientMapper;
import com.gastrosfera.client.repository.ClientRepository;
import com.gastrosfera.client.service.ClientService;
import com.gastrosfera.shared.v1.exception._4xx.EntityAlreadyExistsException;
import com.gastrosfera.shared.v1.exception._4xx.EntityDoesNotExistException;
import com.gastrosfera.shared.v1.client.dto.ClientDTO;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        Optional<Client> existingClient = clientRepository.findByUsername(clientDTO.getUsername());
        if (existingClient.isPresent()) {
            throw new EntityAlreadyExistsException(String.format("Clientul %s este inregistrat deja", clientDTO.getIdentifier()));
        }
        System.out.println("fa create "+ clientMapper.dtoToEntity(clientDTO).getPassword());
        return clientMapper.entityToDto(clientRepository.save(clientMapper.dtoToEntity(clientDTO)));
    }

    @Override
    public ClientDTO getClient(Long id) {
        Optional<Client> existingClient = clientRepository.findById(id);
        if (existingClient.isEmpty()) {
            throw new EntityDoesNotExistException(String.format("Clientul %s nu este inregistrat", id));
        }
        return clientMapper.entityToDto(existingClient.get());
    }

    @Override
    public List<ClientDTO> getClients()
    {
        List<Client> clients = clientRepository.findAll();
        if (clients.isEmpty())
        {
            throw new EntityDoesNotExistException(String.format("Nu exista clienti"));
        }
        return clients.stream()
                .map(clientMapper::entityToDto)
                .collect(Collectors.toList());
    }
}
