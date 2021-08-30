package com.bankdemo.controller;

import com.bankdemo.DTO.AccountDTO;
import com.bankdemo.DTO.AccountTypeDTO;
import com.bankdemo.services.Impl.AccountTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounttype")
public class AccountTypeController {

   private static final Logger logger = LoggerFactory.getLogger(AccountTypeController.class);

    private final AccountTypeService accountTypeService;

    public AccountTypeController(AccountTypeService accountTypeService) {
        this.accountTypeService = accountTypeService;
    }



    @GetMapping("/")
    public List<AccountTypeDTO> getAllAccountsType(){
        return accountTypeService.findAllAccounts();

    }
    @GetMapping("/{id}")
    public AccountTypeDTO getAccountTypeByID(@PathVariable("id") Long id){
        return accountTypeService.findAccountTypeById(id);
    }

    @PostMapping("/add")
    public AccountTypeDTO saveAccountType ( @RequestBody AccountTypeDTO toSave){
        var newAccountType =  accountTypeService.addAccountType(toSave);
        return newAccountType;
    }
    @PutMapping("/{id}")
    public AccountTypeDTO replaceAccountType (@PathVariable("id") Long id,@RequestBody AccountTypeDTO toSave){
        return accountTypeService.replaceAccountTypeByID(id,toSave);
    }

    @PatchMapping("/{id}")
    public AccountTypeDTO updateAccountType (@PathVariable("id") Long id,@RequestBody AccountTypeDTO toSave){
        return accountTypeService.updateAccountTypeById(id,toSave);
    }
    @DeleteMapping("/{id}")
    public  void deleteAccountType (@PathVariable("id") Long id){
        accountTypeService.deleteAccountTypeById(id);
    }

}
