package com.bankdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.Set;


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

    private  String password;


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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns   = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public User(Long id, String name, String surname, String password, String username, String email, String pesel, List<Account> accounts, List<Card> card, List<Role> roles) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.username = username;
        this.email = email;
        this.pesel = pesel;
        this.accounts = accounts;
        this.card = card;
        this.roles = roles;
    }
}
