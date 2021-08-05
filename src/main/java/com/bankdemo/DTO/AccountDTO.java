package com.bankdemo.DTO;


import com.bankdemo.entity.AccountType;
import com.bankdemo.entity.Card;
import com.bankdemo.entity.Currency;
import com.bankdemo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDTO {


    private Long id;

    private Long number;

    private User user;

    private Long balance;

    private Long debit;

    private Currency currency;


    private Card card;

    private AccountType accountType;

}
