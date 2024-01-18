package com.gastrosfera.ospatar.model.mapper;

import com.gastrosfera.ospatar.model.Ospatar;
import com.gastrosfera.shared.v1.mapper.BaseMapper;
import com.gastrosfera.shared.v1.ospatar.dto.OspatarDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OspatarMapper extends BaseMapper<Ospatar, OspatarDTO> {
}
