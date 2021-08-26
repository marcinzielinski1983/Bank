package com.bankdemo.converter;

import com.bankdemo.DTO.AccountDTO;
import com.bankdemo.entity.*;
import com.bankdemo.services.Impl.AccountTypeService;
import com.bankdemo.services.Impl.CardService;
import com.bankdemo.services.Impl.CurrencyService;
import com.bankdemo.services.Impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper implements Mapper<Account, AccountDTO> {

    private static final Logger logger = LoggerFactory.getLogger(AccountMapper.class);
    private final UserService userService;
    private final CurrencyService currencyService;
    private final CardService cardService;
    private final AccountTypeService accountTypeService;

    public AccountMapper(UserService userService, CurrencyService currencyService, CardService cardService, AccountTypeService accountTypeService) {
        this.userService = userService;
        this.currencyService = currencyService;
        this.cardService = cardService;
        this.accountTypeService = accountTypeService;
    }

    @Override
    public AccountDTO fromEntityToDto(Account entity) {
        var result = new AccountDTO(entity.getId(), entity.getNumber(), entity.getUser().getId(), entity.getBalance(), entity.getDebit()
                , entity.getCurrency().getId(), entity.getCard().getId(), entity.getAccountType().getId());
        logger.info("converted  ENTITY: {[]} to DTO {[]}", entity, result);
        return result;
    }

    @Override
    public Account fromDtoToEntity(AccountDTO dto) {
        User userById = userService.findUserEntityByID(dto.getUserId());
        Currency currencyById = currencyService.finCurrencyByIdFromRepository(dto.getCurrencyID());
        Card cardById = cardService.findEntityCardById(dto.getCardID());
        AccountType accountTypeById = accountTypeService.findAccountTypeEntityById(dto.getAccountTypeID());

        var result = new Account(dto.getId(), dto.getNumber(), userById, dto.getBalance(),
                dto.getDebit(), currencyById, cardById, accountTypeById);
        logger.info("converting  DTO: {[]} to ENTITY {[]}", dto, result);
        return result;
    }


}
