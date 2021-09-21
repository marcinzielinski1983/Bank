package com.bankdemo.converter;

import com.bankdemo.DTO.UserDTO;
import com.bankdemo.entity.Account;
import com.bankdemo.entity.Card;
import com.bankdemo.entity.Role;
import com.bankdemo.entity.User;
import com.bankdemo.services.Impl.AccountService;
import com.bankdemo.services.Impl.CardService;
import com.bankdemo.services.Impl.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper implements Mapper<User, UserDTO> {

    private static final Logger logger = LoggerFactory.getLogger(UserMapper.class);
    private final AccountService accountService;
    private final CardService cardService;
    private final RoleService roleService;

    public UserMapper(@Lazy AccountService accountService, CardService cardService, RoleService roleService) {
        this.accountService = accountService;
        this.cardService = cardService;
        this.roleService = roleService;
    }



    @Override
    public UserDTO fromEntityToDto(User entity) {

        var result = new UserDTO(entity.getId(), entity.getName(), entity.getSurname(), entity.getPassword(),
                entity.getUsername(), entity.getEmail(), entity.getPesel(),
                entity.getAccounts().stream().map(account -> account.getId()).collect(Collectors.toList()),
                entity.getCard().stream().map(card -> card.getId()).collect(Collectors.toList()),
                entity.getRoles().stream().map(role -> role.getId()).collect(Collectors.toSet()));

        logger.info("Entity convert to DTO");
        return result;
    }


    @Override
    public User fromDtoToEntity(UserDTO dto) {
        List<Account> accountsWithDtoId = new ArrayList<>();
        for (Long element : dto.getAccountsID()) {
            accountsWithDtoId.add(accountService.findEntityAccountById(element));
        }

        List<Card> cardsWithDtoId = new ArrayList<>();
        for (Long element : dto.getCardID()) {
            cardsWithDtoId.add(cardService.findEntityCardById(element));
        }
        List<Role> rolesWithDtoId = new ArrayList<>();
        for (Long element : dto.getRolesID())
            rolesWithDtoId.add(roleService.findRoleEntityByID(element));

        List<Role> roleList = new ArrayList<>();
        var result = new User(dto.getId(), dto.getName(), dto.getSurname(), dto.getPassword(),
                dto.getUsername(), dto.getEmail(), dto.getPesel(),
                accountsWithDtoId, cardsWithDtoId, rolesWithDtoId);


        logger.info("UserDto map to Entity: [{}]", result);
        return result;
    }


}
