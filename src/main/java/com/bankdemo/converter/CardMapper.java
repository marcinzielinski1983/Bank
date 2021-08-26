package com.bankdemo.converter;

import com.bankdemo.DTO.CardDTO;
import com.bankdemo.entity.Account;
import com.bankdemo.entity.Card;
import com.bankdemo.entity.User;
import com.bankdemo.services.Impl.AccountService;
import com.bankdemo.services.Impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CardMapper implements Mapper<Card, CardDTO> {

    Logger logger = LoggerFactory.getLogger(CardMapper.class);
    private  final UserService userService;
    private  final AccountService accountService;

    public CardMapper(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }


    @Override
    public CardDTO fromEntityToDto(Card entity) {
        var accountsID = entity.getAccounts()
                .stream()
                .map(account -> account.getId())
                .collect(Collectors.toList());
        var result = new CardDTO(entity.getId(), entity.getNumber(),entity.getUser().getId(),accountsID );
        logger.info("Card entity convert to dto : [{}]", result);
        return  result;
    }

    @Override
    public Card fromDtoToEntity(CardDTO dto) {
       User userWithCardId = userService.findUserEntityByID(dto.getUserID());
        List <Account> accountsWithIds = new ArrayList<>();
        for (Long element : dto.getAccountsID())
        {
            accountsWithIds.add(accountService.findEntityAccountById(element)) ;
        }
        var result = new Card(dto.getId(),
                        dto.getNumber(),userWithCardId,accountsWithIds);
        logger.info("CardDTO convert to Entity : [{}]", result);

        return result;
    }
}
