package com.gastrosfera.rezervare.model.mapper;

import com.gastrosfera.rezervare.model.Reserve;
import com.gastrosfera.rezervare.repository.ReserveRepository;
import com.gastrosfera.shared.v1.mapper.BaseMapper;
import com.gastrosfera.shared.v1.reserve.dto.ReserveDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReserveMapper extends BaseMapper<Reserve, ReserveDTO> {
}