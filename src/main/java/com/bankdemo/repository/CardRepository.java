package com.bankdemo.repository;

import com.bankdemo.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card ,Long> {

}
