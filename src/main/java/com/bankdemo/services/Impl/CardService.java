package com.bankdemo.services.Impl;

import com.bankdemo.entity.Card;
import com.bankdemo.repository.CardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {

    public static final Logger logger = LoggerFactory.getLogger(CardService.class);
    public final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }


    public List<Card> findAll() {
        return null;
    }

    ;

    public List<Card> findAllEntityCardsById(Long id) {
        var result = cardRepository.findAll()
                .stream()
                .filter(card -> card.getId().equals(id))
                .collect(Collectors.toList());
        logger.info("Number of card : [{}] , with id: [{}]", result.size(), id);
        return result;


    }


}
