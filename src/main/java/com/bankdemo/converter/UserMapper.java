package com.bankdemo.converter;

import com.bankdemo.DTO.UserDTO;
import com.bankdemo.entity.Account;
import com.bankdemo.entity.Card;
import com.bankdemo.entity.User;
import com.bankdemo.services.Impl.AccountService;
import com.bankdemo.services.Impl.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper implements Mapper<User, UserDTO> {

    private static final Logger logger = LoggerFactory.getLogger(UserMapper.class);
    private final AccountService accountService;
    private final CardService cardService;

    public UserMapper(AccountService accountService, CardService cardService) {
        this.accountService = accountService;
        this.cardService = cardService;
    }


    @Override
    public UserDTO fromEntityToDto(User entity) {
        var result = new UserDTO(entity.getId(), entity.getName(),
                entity.getSurname(), entity.getUsername(),
                entity.getEmail(), entity.getPesel(),
                entity.getAccounts().stream().map(account -> account.getId()).collect(Collectors.toList()),
                entity.getCard().stream().map(card -> card.getId()).collect(Collectors.toList()));
        logger.info("Entity convert to DTO");
        return result;
    }

    @Override
    public User fromDtoToEntity(UserDTO dto) {
        List<Account> accountsWithDtoId = accountService.findAccountByIdReturnEntity(dto.getId());
        List<Card> cardsWithDtoId = cardService.findAllEntityCardsById(dto.getId());

        var result = new User(dto.getId(), dto.getName(), dto.getSurname(),
                dto.getUsername(), dto.getEmail(), dto.getPesel(),
                accountsWithDtoId, cardsWithDtoId);
        logger.info("UserDto map to Entity: [{}]", result);
        return result;
    }
}
