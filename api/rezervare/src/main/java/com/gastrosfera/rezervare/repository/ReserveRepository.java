package com.gastrosfera.rezervare.repository;

import com.gastrosfera.rezervare.model.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

public interface ReserveRepository extends JpaRepository<Reserve, String>, PagingAndSortingRepository<Reserve, String>{
    List<Reserve> findByMasa(String masa);

    Optional<Reserve> findByUuid(long uuid);
    void deleteByUuid(long id);
    @Query("SELECT r FROM Reserve r WHERE r.masa = :table AND ((r.time >= :startTime AND r.time <= :endTime) OR (r.time_end >= :startTime AND r.time_end <= :endTime))")
    List<Reserve> findOverlappingReservations(
            @Param("table") String table,
            @Param("startTime") Time startTime,
            @Param("endTime") Time endTime
    );
}