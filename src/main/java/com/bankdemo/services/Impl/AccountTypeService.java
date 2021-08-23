package com.bankdemo.services.Impl;

import com.bankdemo.converter.AccountTypeMapper;
import com.bankdemo.entity.AccountType;
import com.bankdemo.repository.AccountTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountTypeService {

    private static final Logger logger = LoggerFactory.getLogger(AccountTypeService.class);

    private final AccountTypeMapper accountTypeMapper;
    private  final AccountTypeRepository accountTypeRepository;

    public AccountTypeService(AccountTypeMapper accountTypeMapper, AccountTypeRepository accountTypeRepository) {
        this.accountTypeMapper = accountTypeMapper;
        this.accountTypeRepository = accountTypeRepository;
    }

    public List<AccountType> findAccountTypeEntityById(Long id){
       return accountTypeRepository.findAll()
                .stream()
                .filter(accountType -> accountType.getId().equals(id))
                .collect(Collectors.toList());
    }


}
