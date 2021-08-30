package com.bankdemo.services.Impl;

import com.bankdemo.DTO.AccountDTO;
import com.bankdemo.converter.AccountMapper;
import com.bankdemo.entity.Account;
import com.bankdemo.repository.AccountRepository;
import com.sun.istack.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;
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


    public AccountDTO addAccount(AccountDTO toSave) {
        var entityToSave = accountMapper.fromDtoToEntity(toSave);
        logger.info("saved Account [{}] :", entityToSave);
        return accountMapper.fromEntityToDto(accountRepository.save(entityToSave));
    }

    public List<AccountDTO> findAllAccounts() {
        var result = accountRepository.findAll()
                .stream()
                .map(account -> accountMapper.fromEntityToDto(account))
                .collect(toList());
        logger.info("number of find Accounds : [{}]", result.size());
        logger.debug("result : [{}]", result);
        return result;
    }


    public Account findEntityAccountById(Long id) {
        var accountId = accountRepository.findAll()
                .stream()
                .filter(account -> account.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("No account with id: [{}]", id)));
        logger.info("Account with id: [{}] is : [{}]", id, accountId);
        return accountId;
    }


    public AccountDTO findAccountById(Long id) {
        var accountId = accountRepository.findAll()
                .stream()
                .filter(account -> account.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("No account with id: [{}]", id)));
        logger.info("Account with id: [{}] is : [{}]", id, accountId);
        return accountMapper.fromEntityToDto(accountId);
    }

    public AccountDTO replaceAccount (Long id, AccountDTO toReplace){
        Account accountMapped = accountMapper.fromDtoToEntity(toReplace);
        accountMapped.setId(id);
        logger.info("Replaced account : [{}]", accountMapped);
        return  accountMapper.fromEntityToDto(accountRepository.save(accountMapped));
    }

    public AccountDTO updateAccount (Long id, AccountDTO toUpdate){
        Account account = findEntityAccountById(id);
        Account accountMapped = accountMapper.fromDtoToEntity(toUpdate);
        if(nonNull(accountMapped.getAccountType()))
            account.setAccountType(accountMapped.getAccountType());
        if (nonNull(accountMapped.getBalance()))
            account.setBalance(accountMapped.getBalance());
        if (nonNull(accountMapped.getCard()))
            account.setCard(accountMapped.getCard());
        if (nonNull(accountMapped.getCurrency()))
            account.setCurrency(accountMapped.getCurrency());
        if (nonNull(accountMapped.getDebit()))
            account.setDebit(accountMapped.getDebit());
        if (nonNull(accountMapped.getNumber()))
            account.setNumber(accountMapped.getNumber());
        if (nonNull(accountMapped.getUser()))
            account.setUser(accountMapped.getUser());
        logger.info("Update account will be: [{}]", account);
        return accountMapper.fromEntityToDto(accountRepository.save(account));
    }

    public boolean deleteAccountByID(Long id){
        var exist = accountRepository.existsById(id);
        logger.info("Account with id: [{}] will be deleted", id);
        if(exist)
            accountRepository.deleteById(id);
        return exist;

    }


}
