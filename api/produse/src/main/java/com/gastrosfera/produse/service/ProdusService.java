package com.gastrosfera.produse.service;

import com.gastrosfera.shared.v1.produs.Category;
import com.gastrosfera.shared.v1.produs.dto.ProdusDTO;

import java.util.List;

public interface ProdusService {
    ProdusDTO createProduct(ProdusDTO produsDTO);
    ProdusDTO getProduct(String id);
    //List<ProdusDTO> getAllProducts();
    List<ProdusDTO> getAllProducts(Category category);
    void deleteProduct(String id);
    void updateProduct(ProdusDTO produsDTO);
}
