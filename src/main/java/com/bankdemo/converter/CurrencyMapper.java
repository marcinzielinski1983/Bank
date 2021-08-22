package com.bankdemo.converter;

import com.bankdemo.DTO.CurrencyDTO;
import com.bankdemo.entity.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CurrencyMapper implements Mapper <Currency, CurrencyDTO> {

    Logger logger = LoggerFactory.getLogger(CurrencyMapper.class);

    @Override
    public CurrencyDTO fromEntityToDto(Currency entity) {
        var result = new CurrencyDTO(entity.getId(), entity.getName(), entity.getValueLast(), entity.getTimeOfTheLastUpdate());
        logger.info("Currency entity convert to DTO : [{}]", result);
        return result;
    }

    @Override
    public Currency fromDtoToEntity(CurrencyDTO dto) {
        var result = new Currency(dto.getId(), dto.getName(), dto.getValueLast(), dto.getTimeOfTheLastUpdate());
        logger.info("CurrencyDTO convert to entity: [{}]", result);
        return result;
    }
}
