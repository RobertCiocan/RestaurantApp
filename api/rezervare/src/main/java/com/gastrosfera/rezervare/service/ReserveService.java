package com.gastrosfera.rezervare.service;


import com.gastrosfera.shared.v1.reserve.dto.ReserveDTO;
import org.springframework.http.ResponseEntity;

public interface ReserveService{
    ReserveDTO createReserve(ReserveDTO reserveDTO);
    ReserveDTO getReserve(String id);

    ReserveDTO updateReserve(ReserveDTO reserveDTO);

    ResponseEntity<Void> deleteReserve(String id);

}