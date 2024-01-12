package com.gastrosfera.ospatar.repository;

import com.gastrosfera.ospatar.model.Ospatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface OspatarRepository extends JpaRepository<Ospatar, String>, PagingAndSortingRepository<Ospatar, String> {

    Optional<Ospatar> findByExternalUid(String externalUid);
}
