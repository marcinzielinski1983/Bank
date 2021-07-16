package com.bankdemo.services.Impl;

import com.bankdemo.DTO.BenefitDTO;
import com.bankdemo.converter.BenefitMapper;
import com.bankdemo.entity.Benefit;
import com.bankdemo.repository.BenefitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
        logger.debug("result: [{}]");
        return result;
    }


    public BenefitDTO saveBenefit(BenefitDTO toSave){
        Long currentMaxID = benefitRepository.findAll()
                .stream()
                .mapToLong(value -> value.getId())
                .max()
                .orElse(1);

        Benefit entityTosave = benefitMapper.fromDtoToEntity(toSave);
        entityTosave.setId(currentMaxID + 1);
        benefitRepository.findAll().add(entityTosave);

        logger.info("saved benefit: [{}],",entityTosave);
        return benefitMapper.fromEntityToDto(entityTosave);
    }







}
