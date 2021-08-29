package com.bankdemo.controller;

import com.bankdemo.DTO.BenefitDTO;
import com.bankdemo.services.Impl.BenefitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @GetMapping("/{id}")
    public BenefitDTO getBenefitId (@PathVariable("id") Long id){
        return benefitService.findBenefitById(id);

    }



    @PostMapping("/add")
    public BenefitDTO addBenefit(@RequestBody BenefitDTO toSave) {
        logger.info("adding benefit: [{}]", toSave);
        return benefitService.addBenefit(toSave);
    }

    @PutMapping("/{id}")
    public BenefitDTO replaceBenefit(@PathVariable ("id") Long id ,@RequestBody BenefitDTO toReplace){
        logger.info("Benefit to replace from controler : [{}]", toReplace);
        return benefitService.replaceBenefitById(id,toReplace);
    }

    @PatchMapping("/{id}")
    public BenefitDTO updateBenefit(@PathVariable ("id") Long id ,@RequestBody BenefitDTO toReplace){
        logger.info("Benefit to replace from controller with new attributes: [{}]", toReplace);
        return benefitService.updateBenefit(id,toReplace);
    }

    @DeleteMapping("/{id}")
    public  void deleteBenefitById( @PathVariable ("id") Long id){
        logger.info("Benefit with id: [{}] be delete", id);
        benefitService.deleteBenefitById(id);
    }


}
