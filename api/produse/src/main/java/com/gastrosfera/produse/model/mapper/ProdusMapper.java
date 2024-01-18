package com.gastrosfera.produse.model.mapper;

import com.gastrosfera.produse.model.Produs;
import com.gastrosfera.shared.v1.mapper.BaseMapper;
import com.gastrosfera.shared.v1.produs.dto.ProdusDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdusMapper extends BaseMapper<Produs, ProdusDTO> {
}
