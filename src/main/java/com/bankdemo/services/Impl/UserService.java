package com.bankdemo.services.Impl;


import com.bankdemo.DTO.UserDTO;
import com.bankdemo.converter.UserMapper;
import com.bankdemo.entity.User;
import com.bankdemo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private  final UserRepository userRepository;

    private  final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public List<UserDTO> findAllUsers (){
        var result = userRepository.findAll()
                .stream()
                .map(user -> userMapper.fromEntityToDto(user))
                .collect(Collectors.toList());
        logger.info("Number of find User: [{}]", result.size());
        logger.debug("results: [{}]", result);
        return result;
    }

    public User findUserEntityByID ( Long id){
        var result = userRepository.findAll()
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(()-> new RuntimeException(String.format("No user find with id: [{}]", id)));
        logger.info("Find user with id: [{}]", result);
        return  result;

    }

//    public  UserDTO addUser(UserDTO newUser){
//        User userToSave = userMapper.fromDtoToEntity(newUser);
//
//
//    }


}
