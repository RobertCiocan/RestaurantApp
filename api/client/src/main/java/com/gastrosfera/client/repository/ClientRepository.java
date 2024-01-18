package com.gastrosfera.client.repository;


import com.gastrosfera.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long>, PagingAndSortingRepository<Client, Long> {

    Optional<Client> findByid(Long Uid);
    Optional<Client> findByUsername(String username);
}