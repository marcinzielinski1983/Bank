package com.bankdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long number;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "account")
    private List<User> user;

    private Long balance;

    private Long debit;

    private String currency;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(referencedColumnName = "id")
    private Card card;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private AccountType accountType;


}
