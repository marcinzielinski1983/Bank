package com.bankdemo.controller;

import com.bankdemo.entity.Benefit;
import com.bankdemo.repository.BenefitRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/benefit")
public class BenefitController {


    private final BenefitRepository benefitRepository;

    public BenefitController(BenefitRepository benefitRepository) {
        this.benefitRepository = benefitRepository;
    }

    @GetMapping("/add")
    public Benefit add(){

        Benefit benefit=new Benefit();
        benefit.setName("benefit");
        benefit.setValue("2123");

        benefitRepository.save(benefit);
        return benefitRepository.findAll().stream().findAny().orElse(new Benefit());
    }
}
