package com.bankdemo.DTO;

import com.bankdemo.entity.Account;
import com.bankdemo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@Getter
@Setter
public class CardDTO {

    private Long id;


    private Long number;


    private Long  userID;


    private List<Long> accountsID;





}
