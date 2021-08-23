package com.bankdemo.converter;

import com.bankdemo.DTO.AccountTypeDTO;
import com.bankdemo.entity.AccountType;
import com.bankdemo.entity.Benefit;
import com.bankdemo.services.Impl.AccountTypeService;
import com.bankdemo.services.Impl.BenefitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountTypeMapper  implements Mapper<AccountType, AccountTypeDTO> {

    Logger logger = LoggerFactory.getLogger(AccountTypeMapper.class);

    public  final AccountTypeService accountTypeService;
    public  final BenefitService benefitService;

    public AccountTypeMapper(AccountTypeService accountTypeService, BenefitService benefitService) {
        this.accountTypeService = accountTypeService;
        this.benefitService = benefitService;
    }

    @Override
    public AccountTypeDTO fromEntityToDto(AccountType entity) {
        List<Long> accountsTypeIds = entity.getBenefits()
                .stream()
                .map(benefit -> benefit.getId())
                .collect(Collectors.toList());
        var result = new AccountTypeDTO(entity.getId(), entity.getName(),accountsTypeIds);
        logger.info("AccountType entity convert to DTO : [{}]", result);

        return result;
    }

    @Override
    public AccountType fromDtoToEntity(AccountTypeDTO dto) {
        List<Benefit> benefitsById = new ArrayList<>();
        for (Long element:dto.getBenefitsId()) {
            benefitsById.add(benefitService.findBenefitEntityById(element));
        }
        var result = new AccountType(dto.getId(), dto.getName(),benefitsById);
        logger.debug("AccountType convert to entity : [{}]", result);
        return  result;

    }
}
