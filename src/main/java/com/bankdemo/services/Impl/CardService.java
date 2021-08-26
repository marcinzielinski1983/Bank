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

    public Card findEntityCardById(Long id) {
        var result = cardRepository.findAll()
                .stream()
                .filter(card -> card.getId().equals(id))
                .findFirst()
                        .orElseThrow(()-> new RuntimeException(String.format("No card with Id:", id )));
        logger.info("Card with Id: [{}], : [{}]", id,result);
        return result;
    }


}
