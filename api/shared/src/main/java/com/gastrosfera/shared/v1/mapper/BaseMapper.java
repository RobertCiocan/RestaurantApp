package com.gastrosfera.shared.v1.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

public interface BaseMapper<T, U> {

    U entityToDto(T entity);
    T dtoToEntity(U dto);

    List<U> entityToDto(List<T> entity);
    List<T> dtoToEntity(List<U> dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget T entity, U dto);

}
