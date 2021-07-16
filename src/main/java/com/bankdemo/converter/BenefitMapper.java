package com.bankdemo.converter;

import com.bankdemo.DTO.BenefitDTO;
import com.bankdemo.entity.Benefit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BenefitMapper implements Mapper<Benefit, BenefitDTO> {

    private static  final Logger logger = LoggerFactory.getLogger(BenefitMapper.class);


    @Override
    public BenefitDTO fromEntityToDto(Benefit entity) {

        var result = new BenefitDTO(entity.getId(), entity.getName(), entity.getValue());
        logger.debug("converting entity: {[]}  to DTO {[]}", entity,result);
        return result;
    }

    @Override
    public Benefit fromDtoToEntity(BenefitDTO dto) {

        var result = new Benefit(dto.getId(), dto.getName(), dto.getValue());
        logger.debug("converting DTO: {[]}  to entity {[]}", dto,result);
        return result;
    }
}
