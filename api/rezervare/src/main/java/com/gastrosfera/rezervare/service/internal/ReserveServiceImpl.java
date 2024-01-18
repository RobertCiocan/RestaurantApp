package com.gastrosfera.rezervare.service.internal;

import com.gastrosfera.rezervare.model.Reserve;
import com.gastrosfera.rezervare.model.mapper.ReserveMapper;
import com.gastrosfera.rezervare.repository.ReserveRepository;
import com.gastrosfera.rezervare.service.ReserveService;
import com.gastrosfera.shared.v1.exception._4xx.EntityAlreadyExistsException;
import com.gastrosfera.shared.v1.exception._4xx.EntityDoesNotExistException;
import com.gastrosfera.shared.v1.reserve.dto.ReserveDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
public class ReserveServiceImpl implements ReserveService {
    private final ReserveRepository reserveRepository;
    private final ReserveMapper reserveMapper;

    @Override
    public ReserveDTO createReserve(ReserveDTO reserveDTO) {
        Optional<Reserve> existingReserve = reserveRepository.findById(reserveDTO.getMasa());
        if(existingReserve.isPresent()){
            throw new EntityAlreadyExistsException(String.format("Masa %s este deja rezervata", reserveDTO.getMasa()));
        }
        if (!isReservationTimeValid(reserveDTO, reserveDTO.getMasa())) {
            throw new IllegalArgumentException("Intervalul orar pentru rezervare este invalid.");
        }
        return reserveMapper.entityToDto(reserveRepository.save(reserveMapper.dtoToEntity(reserveDTO)));

    }

    @Override
    public ReserveDTO getReserve(String id) {
        Optional<Reserve> existingReserve = reserveRepository.findByMasa(id);
        if(existingReserve.isEmpty()){
            throw new EntityDoesNotExistException(String.format("Masa %s nu este rezervata", id));
        }
        return reserveMapper.entityToDto(existingReserve.get());
    }

    @Override
    public ReserveDTO updateReserve(ReserveDTO reserveDTO) {
        Optional<Reserve> existingReserve = reserveRepository.findById(reserveDTO.getMasa());
        if(existingReserve.isPresent()){
            Reserve reserve = existingReserve.get();
            if (!isReservationTimeValid(reserveDTO, reserve.getMasa())) {

                throw new IllegalArgumentException("Intervalul orar pentru rezervare este invalid.");
            }
            reserve.setName(reserveDTO.getName());
            reserve.setPhone(reserveDTO.getPhone());
            reserve.setData(reserveDTO.getData());
            reserve.setTime(reserveDTO.getTime());
            reserve.setTime_end(reserveDTO.getTime_end());
            reserve.setSpecialRequests(reserveDTO.getSpecialRequests());
            reserve.setGuests(reserveDTO.getGuests());

            Reserve updatedReserve = reserveRepository.save(reserve);

            return reserveMapper.entityToDto(updatedReserve);
        }
        else{
            if (!isReservationTimeValid(reserveDTO, reserveDTO.getMasa())) {
                throw new IllegalArgumentException("Intervalul orar pentru rezervare este invalid.");
            }
            return reserveMapper.entityToDto(reserveRepository.save(reserveMapper.dtoToEntity(reserveDTO)));
        }
    }
    private boolean isReservationTimeValid(ReserveDTO reserveDTO, String table){
        List<Reserve> overlappingReservations = reserveRepository.findOverlappingReservations(
                table,
                reserveDTO.getTime(),
                reserveDTO.getTime_end()
        );
        return overlappingReservations.isEmpty();
    }

    @Override
    public ResponseEntity<Void> deleteReserve(String id) {
        Optional<Reserve> existingReserve = reserveRepository.findByMasa(id);
        if(existingReserve.isPresent()){
            reserveRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


}
