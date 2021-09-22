package com.bankdemo.DTO;


import com.bankdemo.entity.Account;
import com.bankdemo.entity.Card;
import com.bankdemo.entity.Role;
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
import java.util.Set;


@Data
@Getter
@Setter
public class UserDTO {




    private Long id;

    private String name;

    private String surname;

    private  String password;

    private  String username;

    private String role;


    private  String email;


    private String pesel;


    private List<Long> accountsID;

    private  List<Long> cardID;

    private Set<Long> rolesID;

    public UserDTO(Long id, String name, String surname, String password, String username, String email, String pesel, List<Long> accountsID, List<Long> cardID, Set<Long> rolesID) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.username = username;
        this.email = email;
        this.pesel = pesel;
        this.accountsID = accountsID;
        this.cardID = cardID;
        this.rolesID = rolesID;
    }
}
