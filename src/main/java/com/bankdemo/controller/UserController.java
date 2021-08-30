package com.bankdemo.controller;

import com.bankdemo.DTO.UserDTO;
import com.bankdemo.services.Impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

   private static final Logger logger = LoggerFactory.getLogger(UserController.class);
   private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public List<UserDTO> getAllUsers(){
        return userService.findAllUsers();
    }
    @GetMapping("/{id}")
    public  UserDTO getUserById(@PathVariable("id") Long id){
        return userService.findUserByID(id);
    }
    @PostMapping("/add")
    public UserDTO addUser(@RequestBody UserDTO toSave){
        return userService.addNewUser(toSave);
    }

    @PutMapping("/{id}")
    public UserDTO replaceUser (@PathVariable("id") Long id,@RequestBody UserDTO toSave){
        return userService.replaceUser(id,toSave);
    }
    @PatchMapping("/{id}")
    public UserDTO updateUser(@PathVariable("id") Long id,@RequestBody UserDTO toUpdate){
        return userService.updateUser(id,toUpdate);
    }
    @DeleteMapping("/{id}")
    public  void deleteUser(@PathVariable("id") Long id){
        userService.deleteUserById(id);
    }




}
