package com.gastrosfera.user.service;

import com.gastrosfera.user.model.mapper.UserMapper;
import com.gastrosfera.user.repository.UserRepository;
import com.gastrosfera.user.service.internal.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceConfiguration {

    @Bean
    public UserService userService(UserRepository userRepository, UserMapper userMapper) {
        return new UserServiceImpl(userRepository, userMapper);
    }

}
