package com.bankdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @Column(unique = true)
    private  String username;

    @Email
    private  String email;

    @PESEL
    private String pesel;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(referencedColumnName = "id")
    private List<Account> accounts;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    private  List<Card> card;
}
