package com.bankdemo.services.Impl;

import com.bankdemo.DTO.AccountDTO;
import com.bankdemo.converter.AccountMapper;
import com.bankdemo.entity.Account;
import com.bankdemo.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;


    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }


        public List<AccountDTO> findAllAccounts(){
        var result = accountRepository.findAll()
                .stream()
                .map(account -> accountMapper.fromEntityToDto(account))
                .collect(toList());
        logger.info("number of find Accounds : [{}]", result.size());
        logger.debug("result : [{}]" , result);
        return  result;
        }

        public  AccountDTO saveAccount( AccountDTO toSave){
        var entityToSave = accountMapper.fromDtoToEntity(toSave);
        accountRepository.save(entityToSave);
        logger.info("saved Account [{}] :", entityToSave);
        return accountMapper.fromEntityToDto(entityToSave);
        }




    public List<AccountDTO> findAccountById (Long id) {
        var accountId = accountRepository.findAll()
                .stream()
                .filter(account -> account.getId().equals(id))
                .map(account -> accountMapper.fromEntityToDto(account))
                .collect(toList());
        logger.info("Account with id: [{}] is : [{}]", id, accountId);
        return  accountId;
    }

    public Account findEntityAccountById(Long id) {
        var accountId = accountRepository.findAll()
                .stream()
                .filter(account -> account.getId().equals(id))
                .findFirst()
                .orElseThrow(()-> new RuntimeException(String.format("No account with id: [{}]", id)));
        logger.info("Account with id: [{}] is : [{}]", id, accountId);
        return  accountId;
    }



}
