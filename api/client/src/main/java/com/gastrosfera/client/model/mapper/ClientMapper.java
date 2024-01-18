package com.gastrosfera.client.model.mapper;

import com.gastrosfera.client.model.Client;
import com.gastrosfera.shared.v1.mapper.BaseMapper;
import com.gastrosfera.shared.v1.client.dto.ClientDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public class ClientMapper {

    public ClientDTO entityToDto(Client entity) {
        if ( entity == null ) {
            return null;
        }

        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setId( entity.getId() );
        clientDTO.setNume( entity.getNume() );
        clientDTO.setPrenume( entity.getPrenume() );
        clientDTO.setEmail( entity.getEmail() );
        clientDTO.setPhone_number(entity.getPhone_number());
        clientDTO.setAddress(entity.getAddress());
        clientDTO.setUsername(entity.getUsername());
        clientDTO.setPassword(entity.getPassword());
        return clientDTO;
    }

    public Client dtoToEntity(ClientDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Client client = new Client();

        client.setId( dto.getId() );
        client.setEmail( dto.getEmail() );
        client.setNume( dto.getNume() );
        client.setPrenume( dto.getPrenume() );
        client.setPhone_number(dto.getPhone_number());
        client.setAddress(dto.getAddress());
        client.setUsername(dto.getUsername());
        client.setPassword(dto.getPassword());
        return client;
    }

    public List<ClientDTO> entityToDto(List<Client> entity) {
        if ( entity == null ) {
            return null;
        }

        List<ClientDTO> list = new ArrayList<ClientDTO>( entity.size() );
        for ( Client client : entity ) {
            list.add( entityToDto( client ) );
        }

        return list;
    }

    public List<Client> dtoToEntity(List<ClientDTO> dto) {
        if ( dto == null ) {
            return null;
        }

        List<Client> list = new ArrayList<Client>( dto.size() );
        for ( ClientDTO clientDTO : dto ) {
            list.add( dtoToEntity( clientDTO ) );
        }

        return list;
    }


    public void update(Client entity, ClientDTO dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getEmail() != null ) {
            entity.setEmail( dto.getEmail() );
        }
        if ( dto.getNume() != null ) {
            entity.setNume( dto.getNume() );
        }
        if ( dto.getPrenume() != null ) {
            entity.setPrenume( dto.getPrenume() );
        }
    }
}