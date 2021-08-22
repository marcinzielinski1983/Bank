package com.bankdemo.services.Impl;

import com.bankdemo.DTO.BenefitDTO;
import com.bankdemo.converter.BenefitMapper;
import com.bankdemo.entity.Benefit;
import com.bankdemo.repository.BenefitRepository;
import org.assertj.core.internal.Iterables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class BenefitService {

    private static final Logger logger = LoggerFactory.getLogger(BenefitService.class);

    private final BenefitRepository benefitRepository;

    private final BenefitMapper benefitMapper;

    public BenefitService(BenefitRepository benefitRepository, BenefitMapper benefitMapper) {
        this.benefitRepository = benefitRepository;
        this.benefitMapper = benefitMapper;
    }


    public  List<BenefitDTO> findAllBenefits(){
        var result =  benefitRepository.findAll()
                .stream()
                .map(benefit -> benefitMapper.fromEntityToDto(benefit))
                .collect(toList());
        logger.info("number of found benefits: [{}]", result.size());
        logger.debug("result: [{}]", result);
        return result;
    }


    public BenefitDTO saveBenefit(BenefitDTO toSave){
        Benefit entityToSave = benefitMapper.fromDtoToEntity(toSave);
            benefitRepository.save(entityToSave);
            logger.info("saved benefit: [{}],",entityToSave);
        return benefitMapper.fromEntityToDto(entityToSave);
    }








}
