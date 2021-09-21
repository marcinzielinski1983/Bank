package com.bankdemo.DTO;


import com.bankdemo.entity.Account;
import com.bankdemo.entity.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import java.util.List;

@AllArgsConstructor
@Data
@Getter
@Setter
public class UserDTO {




    private Long id;

    private String name;

    private String surname;


    private  String username;

    private String role;


    private  String email;


    private String pesel;


    private List<Long> accountsID;

    private  List<Long> cardID;
}
