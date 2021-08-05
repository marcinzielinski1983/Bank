package com.bankdemo.converter;

import com.bankdemo.DTO.AccountDTO;
import com.bankdemo.entity.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountMapper implements Mapper<Account,AccountDTO> {

    private static final Logger logger = LoggerFactory.getLogger(AccountMapper.class);


    @Override
    public AccountDTO fromEntityToDto(Account entity) {
        var result = new AccountDTO(entity.getId(),entity.getNumber(),entity.getUser(), entity.getBalance(), entity.getDebit()
                ,entity.getCurrency(),entity.getCard(),entity.getAccountType());
        logger.info("converting  ENTITY: {[]} to DTO {[]}", entity ,result );
        return result;
    }

    @Override
    public Account fromDtoToEntity(AccountDTO dto) {
        var result = new Account(dto.getId(), dto.getNumber(), dto.getUser(),dto.getBalance(),
                dto.getDebit(), dto.getCurrency(),dto.getCard(),dto.getAccountType());
        logger.info("converting  DTO: {[]} to ENTITY {[]}", dto ,result );
        return result;
    }


   
}
