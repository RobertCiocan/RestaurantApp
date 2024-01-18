package com.gastrosfera.produse.repository;

import com.gastrosfera.produse.model.Produs;
import com.gastrosfera.shared.v1.produs.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdusRepository extends MongoRepository<Produs, String> {
    @Query("{category:'?0'}")
    List<Produs> findAll(Category category);
}
