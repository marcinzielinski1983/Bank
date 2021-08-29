package com.bankdemo.services.Impl;

import com.bankdemo.DTO.CardDTO;
import com.bankdemo.converter.CardMapper;
import com.bankdemo.entity.Card;
import com.bankdemo.repository.CardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {

    public static final Logger logger = LoggerFactory.getLogger(CardService.class);
    public final CardRepository cardRepository;
    public final CardMapper cardMapper;

    public CardService(CardRepository cardRepository,@Lazy CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
    }



    public  CardDTO addNewCard (CardDTO toSave){
        var cardToSave = cardMapper.fromDtoToEntity(toSave);
        logger.info("New Card : [{}] is saved", cardToSave);
        return  cardMapper.fromEntityToDto(cardRepository.save(cardToSave));
    }

    public List<CardDTO> findAllCard() {
        var result = cardRepository.findAll()
                .stream()
                .map(cardMapper::fromEntityToDto)
                .collect(Collectors.toList());
        logger.info("Return Card list size is: [{}]", result.size());
        return  result;
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

    public  CardDTO replaceCard (Long id, CardDTO toReplace){
        Card card = findEntityCardById(id);
        Card cardMapped = cardMapper.fromDtoToEntity(toReplace);
        cardRepository.findAll().removeIf(card1 -> card1.getId().equals(id));
        cardRepository.findAll().add(cardMapped);
        logger.info("Replacing Card : [{}] with new card: [{}]", card,cardMapped);
        return cardMapper.fromEntityToDto(cardMapped);

    }


}
