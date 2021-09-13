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

    @GetMapping()
    public List<AccountDTO> getAllAccounts(){
          return accountService.findAllAccounts();

    }
    @GetMapping("/{id}")
    public AccountDTO getAccountByID(@PathVariable("id") Long id){
        return accountService.findAccountById(id);
    }

    @PostMapping("/add")
    public AccountDTO saveAccount ( @RequestBody AccountDTO toSave){
       var newAccount =  accountService.addAccount(toSave);
       return newAccount;
    }
    @PutMapping("/{id}")
    public AccountDTO replaceAccount (@PathVariable("id") Long id,@RequestBody AccountDTO toSave){
        return accountService.replaceAccount(id,toSave);
    }

    @PatchMapping("/{id}")
    public AccountDTO updateAccount (@PathVariable("id") Long id,@RequestBody AccountDTO toSave){
        return accountService.updateAccount(id,toSave);
    }
    @DeleteMapping("/{id}")
    public  void deleteAccount (@PathVariable("id") Long id){
        accountService.deleteAccountByID(id);
    }

}
