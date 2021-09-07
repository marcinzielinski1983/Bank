package com.bankdemo.controller;


import com.bankdemo.DTO.CurrencyDTO;
import com.bankdemo.DTO.UserDTO;
import com.bankdemo.entity.Card;
import com.bankdemo.services.Impl.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyController.class);
    private  final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }


    @GetMapping
    public List<CurrencyDTO> getAllCurrency(){
        return currencyService.findAllCurrency();
    }
    @GetMapping("/{id}")
    public  CurrencyDTO getCurrencyById(@PathVariable("id") Long id){
        return currencyService.findCurrencyById(id);
    }
    @PostMapping("/add")
    public CurrencyDTO addCurrency(@RequestBody CurrencyDTO toSave){
        return currencyService.saveNewCurrency(toSave);
    }

    @PutMapping("/{id}")
    public CurrencyDTO replaceCurrency (@PathVariable("id") Long id,@RequestBody CurrencyDTO toReplace){
        return currencyService.replaceCurrency(id,toReplace);
    }
    @PatchMapping("/{id}")
    public CurrencyDTO updateCurrency(@PathVariable("id") Long id,@RequestBody CurrencyDTO toUpdate){
        return currencyService.updateCurrencyById(id,toUpdate);
    }
    @DeleteMapping("/{id}")
    public  void deleteCurrency(@PathVariable("id") Long id){
        currencyService.deleteCurrencyById(id);
    }

}
