package com.bankdemo.controller;

import com.bankdemo.DTO.BenefitDTO;
import com.bankdemo.entity.Benefit;
import com.bankdemo.services.Impl.BenefitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/benefit")
public class BenefitController {

    private static final Logger logger = LoggerFactory.getLogger(BenefitController.class);

    private final BenefitService benefitService;

    public BenefitController(BenefitService benefitService) {
        this.benefitService = benefitService;
    }


    @GetMapping
    public List<BenefitDTO> getAllBenefits(){
        logger.info("get all benefits");
        return benefitService.findAllBenefits();

    }

/*    @GetMapping("/add")
    public Benefit add(){

        Benefit benefit=new Benefit();
        benefit.setName("benefit");
        benefit.setValue("2123");

        benefitService.saveBenefit(benefit)
        return benefitRepository.findAll().stream().findAny().orElse(new Benefit());
    }*/

    @PostMapping("/add")
    public BenefitDTO addBenefit(@RequestBody BenefitDTO toSave) {
        logger.info("adding benefit: [{}]", toSave);
        return benefitService.saveBenefit(toSave);

    }
}
