package com.gastrosfera.user.model.mapper;


import com.gastrosfera.shared.v1.mapper.BaseMapper;
import com.gastrosfera.shared.v1.user.dto.UserDTO;
import com.gastrosfera.user.model.User;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDTO> {
}

