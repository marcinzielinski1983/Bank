package com.bankdemo.controller;

import com.bankdemo.DTO.CardDTO;
import com.bankdemo.DTO.UserDTO;
import com.bankdemo.services.Impl.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    private static final Logger logger = LoggerFactory.getLogger(CardController.class);
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public List<CardDTO> getAllCards(){
        return cardService.findAllCards();
    }
    @GetMapping("/{id}")
    public  CardDTO getCardById(@PathVariable("id") Long id){
        return cardService.findCardById(id);
    }
    @PostMapping("/add")
    public CardDTO addCard(@RequestBody CardDTO toSave){
        return cardService.addNewCard(toSave);
    }

    @PutMapping("/{id}")
    public CardDTO replaceCard (@PathVariable("id") Long id,@RequestBody CardDTO toSave){
        return cardService.replaceCard(id,toSave);
    }
    @PatchMapping("/{id}")
    public CardDTO updateCard(@PathVariable("id") Long id,@RequestBody CardDTO toUpdate){
        return cardService.updateCard(id,toUpdate);
    }
    @DeleteMapping("/{id}")
    public  void deleteCard(@PathVariable("id") Long id){
        cardService.deleteCardById(id);
    }

}
