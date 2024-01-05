package com.gastrosfera.user.service.internal;

import com.gastrosfera.user.model.User;
import com.gastrosfera.user.model.mapper.UserMapper;
import com.gastrosfera.user.repository.UserRepository;
import com.gastrosfera.user.service.UserService;
import com.gastrosfera.shared.v1.exception._4xx.EntityAlreadyExistsException;
import com.gastrosfera.shared.v1.exception._4xx.EntityDoesNotExistException;
import com.gastrosfera.shared.v1.user.dto.UserDTO;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findByUsername(userDTO.getUsername());
        if (existingUser.isPresent()) {
            throw new EntityAlreadyExistsException(String.format("Userul %s este deja inregistrat", userDTO.getIdUser()));
        }
        return userMapper.entityToDto(userRepository.save(userMapper.dtoToEntity(userDTO)));
    }

    @Override
    public UserDTO getUser(Long uuid) {
        Optional<User> existingUser = userRepository.findById(uuid);
        if (existingUser.isEmpty()) {
            throw new EntityDoesNotExistException(String.format("Userul %s nu este inregistrat", uuid));
        }
        return userMapper.entityToDto(existingUser.get());
    }

    @Override
    public UserDTO getUserByUsernameAndPassword(String username, String password) {
        User user = userRepository.getUserByUsernameAndPassword(username, password);
        return user != null ? userMapper.entityToDto(user) : null;
    }
}
