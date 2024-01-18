package com.gastrosfera.produse.service.internal;

import com.gastrosfera.produse.model.Produs;
import com.gastrosfera.produse.model.mapper.ProdusMapper;
import com.gastrosfera.produse.repository.ProdusRepository;
import com.gastrosfera.produse.service.ProdusService;
import com.gastrosfera.shared.v1.exception._4xx.EntityAlreadyExistsException;
import com.gastrosfera.shared.v1.exception._4xx.EntityDoesNotExistException;
import com.gastrosfera.shared.v1.produs.Category;
import com.gastrosfera.shared.v1.produs.dto.ProdusDTO;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ProdusServiceImpl implements ProdusService {

    private final ProdusRepository produsRepository;
    private final ProdusMapper produsMapper;
    @Override
    public ProdusDTO createProduct(ProdusDTO produsDTO) {
        Optional<Produs> produs = produsRepository.findById(produsDTO.getName());
        if(produs.isPresent()){
            throw new EntityAlreadyExistsException("Mai patesti");
        }
        return produsMapper.entityToDto(produsRepository.save(produsMapper.dtoToEntity(produsDTO)));
    }

    @Override
    public ProdusDTO getProduct(String name) {
        Optional<Produs> produs = produsRepository.findById(name);
        if(produs.isEmpty()){
            throw new EntityDoesNotExistException("Mai nu patesti");
        }
        return produsMapper.entityToDto(produs.get());
    }

    @Override
    public List<ProdusDTO> getAllProducts() {
        return produsRepository.findAll().stream().map(produsMapper::entityToDto).toList();
    }

    @Override
    public List<ProdusDTO> getAllProducts(Category category) {
        return produsRepository.findAll(category).stream().map(produsMapper::entityToDto).toList();
    }

    @Override
    public void deleteProduct(String name) {
        Optional<Produs> produs = produsRepository.findById(name);
        if(produs.isEmpty()){
            throw new EntityDoesNotExistException("Mai nu exista pt delete.");
        }
        produsRepository.deleteById(name);
    }

    @Override
    public void updateProduct(ProdusDTO produsDTO) {
        Optional<Produs> produs = produsRepository.findById(produsDTO.getName());
        if(produs.isEmpty()){
            throw new EntityDoesNotExistException("Mai intai trebuie sa ai, ca dupa sa dai");
        }
        produsRepository.save(produsMapper.dtoToEntity(produsDTO));
    }
}
