package com.gastrosfera.user.service;

import com.gastrosfera.shared.v1.user.dto.UserDTO;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);
    UserDTO getUser(Long uuid);
    UserDTO getUserByUsernameAndPassword(String username, String password);

}
