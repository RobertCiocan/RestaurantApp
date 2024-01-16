package com.gastrosfera.rezervare.repository;

import com.gastrosfera.rezervare.model.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ReserveRepository extends JpaRepository<Reserve, String>, PagingAndSortingRepository<Reserve, String>{
    Optional<Reserve> findByMasa(String masa);
}