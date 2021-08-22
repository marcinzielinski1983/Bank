package com.bankdemo.controller;

import com.bankdemo.DTO.AccountDTO;
import com.bankdemo.services.Impl.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

  private final AccountService accountService;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/all")
    public List<AccountDTO> getAllAccounts(){
          return accountService.findAllAccounts();

    }

    @PostMapping("/add")
    public AccountDTO saveAccount ( @RequestBody AccountDTO toSave){
       var newAccount =  accountService.saveAccount(toSave);
       return newAccount;


    }

}
