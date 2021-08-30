package com.bankdemo.services.Impl;

import com.bankdemo.DTO.AccountDTO;
import com.bankdemo.DTO.AccountTypeDTO;
import com.bankdemo.converter.AccountTypeMapper;
import com.bankdemo.entity.AccountType;
import com.bankdemo.repository.AccountTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

@Service
public class AccountTypeService {

    private static final Logger logger = LoggerFactory.getLogger(AccountTypeService.class);

    private final AccountTypeMapper accountTypeMapper;
    private  final AccountTypeRepository accountTypeRepository;

    public AccountTypeService(AccountTypeMapper accountTypeMapper, AccountTypeRepository accountTypeRepository) {
        this.accountTypeMapper = accountTypeMapper;
        this.accountTypeRepository = accountTypeRepository;
    }

    public  AccountTypeDTO addAccountType (AccountTypeDTO toSave){
        var accountTypeToSave = accountTypeMapper.fromDtoToEntity(toSave);
        logger.info("The new Account Type will be saved : [{}]", toSave);
        return accountTypeMapper.fromEntityToDto(accountTypeRepository.save(accountTypeToSave));
    }


    public AccountType findAccountTypeEntityById(Long id){
       return accountTypeRepository.findAll()
                .stream()
                .filter(accountType -> accountType.getId().equals(id))
                .findFirst()
               .orElseThrow(()-> new RuntimeException(String.format("No accountType with Id: [{}]", id)));
    }

    public AccountTypeDTO findAccountTypeById(Long id){
        var result=  accountTypeRepository.findAll()
                .stream()
                .filter(accountType -> accountType.getId().equals(id))
                .findFirst()
                .orElseThrow(()-> new RuntimeException(String.format("No accountType with Id: [{}]", id)));
        return  accountTypeMapper.fromEntityToDto(result);
    }




    public List <AccountTypeDTO> findAllAccounts (){
        var result = accountTypeRepository.findAll()
                .stream()
                .map(accountTypeMapper::fromEntityToDto)
                .collect(toList());
        logger.info("number of find Account types is : [{}]", result.size());
        return  result;
    }


    public  AccountTypeDTO replaceAccountTypeByID(Long id, AccountTypeDTO toReplace){
        AccountType accountType = findAccountTypeEntityById(id);
        AccountType accountTypeMapped = accountTypeMapper.fromDtoToEntity(toReplace);
        accountTypeMapped.setId(id);
        logger.info("Replace account type is: [{}]", accountTypeMapped);
        return accountTypeMapper.fromEntityToDto(accountTypeRepository.save(accountTypeMapped));

    }

    public AccountTypeDTO updateAccountTypeById( Long id, AccountTypeDTO toUpdate){
        AccountType accountType = findAccountTypeEntityById(id);
        AccountType accountTypeMapped = accountTypeMapper.fromDtoToEntity(toUpdate);
        if (nonNull(accountTypeMapped.getBenefits()))
            accountType.setBenefits(accountTypeMapped.getBenefits());

        if (nonNull(accountTypeMapped.getName()))
            accountType.setBenefits(accountTypeMapped.getBenefits());
        logger.info("This is updated Account type: [{}]", accountType);
        return accountTypeMapper.fromEntityToDto(accountTypeRepository.save(accountType));

    }

    public boolean deleteAccountTypeById(Long id){
        var exist = accountTypeRepository.existsById(id);
        if(exist)
            accountTypeRepository.deleteById(id);
        return exist;

    }

}
