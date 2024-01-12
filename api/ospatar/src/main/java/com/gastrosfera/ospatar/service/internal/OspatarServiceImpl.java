package com.gastrosfera.ospatar.service.internal;

import com.gastrosfera.ospatar.model.Ospatar;
import com.gastrosfera.ospatar.model.mapper.OspatarMapper;
import com.gastrosfera.ospatar.repository.OspatarRepository;
import com.gastrosfera.ospatar.service.OspatarService;
import com.gastrosfera.shared.v1.exception._4xx.EntityAlreadyExistsException;
import com.gastrosfera.shared.v1.exception._4xx.EntityDoesNotExistException;
import com.gastrosfera.shared.v1.ospatar.dto.OspatarDTO;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class OspatarServiceImpl implements OspatarService {

    private final OspatarRepository ospatarRepository;
    private final OspatarMapper ospatarMapper;

    @Override
    public OspatarDTO createOspatar(OspatarDTO ospatarDTO) {
        Optional<Ospatar> existingOspatar = ospatarRepository.findById(ospatarDTO.getCnp());
        if (existingOspatar.isPresent()) {
            throw new EntityAlreadyExistsException(String.format("Ospatarul %s este inregistrat deja", ospatarDTO.getCnp()));
        }
        return ospatarMapper.entityToDto(ospatarRepository.save(ospatarMapper.dtoToEntity(ospatarDTO)));
    }

    @Override
    public OspatarDTO getOspatar(String uuid) {
        Optional<Ospatar> existingOspatar = ospatarRepository.findById(uuid);
        if (existingOspatar.isEmpty()) {
            throw new EntityDoesNotExistException(String.format("Ospatarul %s nu este inregistrat", uuid));
        }
        return ospatarMapper.entityToDto(existingOspatar.get());
    }
}
