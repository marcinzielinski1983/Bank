package com.bankdemo.services.Impl;


import com.bankdemo.DTO.CurrencyDTO;
import com.bankdemo.DTO.UserDTO;
import com.bankdemo.converter.UserMapper;
import com.bankdemo.entity.Currency;
import com.bankdemo.entity.User;
import com.bankdemo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private  final UserRepository userRepository;

    private  final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public UserDTO addNewUser (UserDTO toSave){
        var userToSave = userMapper.fromDtoToEntity(toSave);
        logger.info("New user is saved");
        return  userMapper.fromEntityToDto(userRepository.save(userToSave));

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

    public UserDTO findUserByID ( Long id){
        var result = userRepository.findAll()
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(()-> new RuntimeException(String.format("No user find with id: [{}]", id)));
        logger.info("Find user with id: [{}]", result);
        return  userMapper.fromEntityToDto(result);

    }

    public UserDTO updateUser(Long id, UserDTO toUpdate){
        User user = findUserEntityByID(id);
        User userMapped = userMapper.fromDtoToEntity(toUpdate);
        logger.info("updated user : [{}]", toUpdate);
        if (nonNull(userMapped.getAccounts())){
            user.setAccounts(userMapped.getAccounts());
        }
        if(nonNull(userMapped.getCard())){
            user.setCard(userMapped.getCard());
        }
        if(nonNull(userMapped.getUsername())){
            user.setUsername(userMapped.getUsername());
        }
        if (nonNull(userMapped.getEmail())){
            user.setEmail(userMapped.getEmail());
        }
        if (nonNull(userMapped.getName())){
            user.setName(userMapped.getName());
        }
        if (nonNull(userMapped.getSurname())){
            user.setSurname(userMapped.getSurname());
        }
        if (nonNull(userMapped.getPesel()))
            user.setPesel(userMapped.getPesel());

        return userMapper.fromEntityToDto(userRepository.save(user));

    }



    public UserDTO replaceUser(Long id, UserDTO toUpdate){
        User user = findUserEntityByID(id);
        User userMapped = userMapper.fromDtoToEntity(toUpdate);
        userRepository.findAll().removeIf(user1 -> user1.getId().equals(id));
        userRepository.findAll().add(userMapped);
        logger.info("replacing user : [{}] with new one: [{}] ", user,userMapped);
        return userMapper.fromEntityToDto(userMapped);

    }




    public boolean deleteUserById(Long id){
        var exist = userRepository.existsById(id);
        if(exist){
            userRepository.deleteById(id);
        }
        return exist;
    }

}
