package com.gastrosfera.rezervare.service;


import com.gastrosfera.shared.v1.reserve.dto.ReserveDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReserveService{
    ReserveDTO createReserve(ReserveDTO reserveDTO);
    ReserveDTO getReserve(long id);
    List<ReserveDTO> getReserveByMasa(String masa);

    ReserveDTO updateReserve(ReserveDTO reserveDTO);

    ResponseEntity<Void> deleteReserve(long id);

}