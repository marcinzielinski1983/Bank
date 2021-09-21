package com.bankdemo.services.Impl;

import com.bankdemo.entity.Role;
import com.bankdemo.entity.User;
import com.bankdemo.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);


    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public Role findRoleEntityByID (Long id) {
        var result = roleRepository.findAll()
                .stream()
                .filter(role -> role.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("No role find with id: [{}]", id)));
        logger.info("Find user with id: [{}]", result);
        return result;
    }

}
