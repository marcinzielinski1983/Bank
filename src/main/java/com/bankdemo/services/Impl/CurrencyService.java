package com.bankdemo.services.Impl;


import com.bankdemo.DTO.CurrencyDTO;
import com.bankdemo.converter.CurrencyMapper;
import com.bankdemo.entity.Currency;
import com.bankdemo.repository.CurrencyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class CurrencyService {

    public static final Logger logger = LoggerFactory.getLogger(CardService.class);
    public final CurrencyRepository currencyRepository;
    public final CurrencyMapper currencyMapper;

    public CurrencyService(CurrencyRepository currencyRepository, CurrencyMapper currencyMapper) {
        this.currencyRepository = currencyRepository;
        this.currencyMapper = currencyMapper;
    }


    public CurrencyDTO saveNewCurrency(CurrencyDTO toSave) {
        var currencyToSave = currencyMapper.fromDtoToEntity(toSave);
        logger.debug("New currency is saved");
        return currencyMapper.fromEntityToDto(currencyRepository.save(currencyToSave));
    }

    public List<CurrencyDTO> findAllCurrency() {
        var result = currencyRepository.findAll()
                .stream()
                .map(currencyMapper::fromEntityToDto)
                .collect(Collectors.toList());
        logger.info("Return currency list size is : [{}]", result.size());
        return result;
    }

    public CurrencyDTO findCurrencyById(Long id) {
        var result = finCurrencyByIdFromRepository(id);
        logger.debug("curency with id: [{}]", result);
        return currencyMapper.fromEntityToDto(result);
    }

    public Currency finCurrencyByIdFromRepository(Long id) {
        return currencyRepository.findAll()
                .stream()
                .filter(currency -> currency.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Can not find currency with id: [{}]", id)));

    }

    public  CurrencyDTO updateCurrencyById(Long id, CurrencyDTO toUpdate){
        Currency currency = finCurrencyByIdFromRepository(id);
        Currency currencyMapped = currencyMapper.fromDtoToEntity(toUpdate);
        logger.info("updated currency: [{}] with changes to apply : [{}]",currency, toUpdate );

        if(nonNull(currencyMapped.getName())){
            currency.setName(currencyMapped.getName());
        }
        if(nonNull(currencyMapped.getTimeOfTheLastUpdate())){
            currency.setTimeOfTheLastUpdate(currencyMapped.getTimeOfTheLastUpdate());
        }

        if (nonNull(currencyMapped.getValueLast())){
            currency.setValueLast(currencyMapped.getValueLast());
        }
        return currencyMapper.fromEntityToDto(currencyRepository.save(currency));

    }



    public CurrencyDTO replaceCurrency(Long id, CurrencyDTO toUpdate) {
        Currency currency = finCurrencyByIdFromRepository(id);
        Currency currencyMapped = currencyMapper.fromDtoToEntity(toUpdate);
        currencyMapped.setId(id);
        currencyRepository.findAll().removeIf(currency1 -> currency1.getId().equals(id));
        currencyRepository.findAll().add(currencyMapped);
        logger.info("replacing currency [{}] with new one [{}]", currency, toUpdate);
        return currencyMapper.fromEntityToDto(currencyMapped);

    }

    public boolean deleteCurrencyById(Long id){
        var exist = currencyRepository.existsById(id);
        if(exist){
            currencyRepository.deleteById(id);
        }
        return  exist;

    }


}
