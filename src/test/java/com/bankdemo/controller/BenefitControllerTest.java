package com.bankdemo.controller;

import com.bankdemo.DTO.BenefitDTO;
import com.bankdemo.controller.BenefitController;
import com.bankdemo.repository.BenefitRepository;
import com.bankdemo.services.Impl.BenefitService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.springframework.test.web.client.ExpectedCount.times;


@RunWith(MockitoJUnitRunner.class)
public class BenefitControllerTest {

    @Mock
    BenefitService benefitService;

    @InjectMocks
    BenefitController benefitController;

   @Before
    public void init(){
        Mockito.when(benefitService.findAllBenefits()).thenReturn(prepareMockListBenefit());
    }

    @Test
    public void analyzeBenefitControllerMethodFindBenefit() {

        List<BenefitDTO> benefitList = benefitController.getAllBenefits();
        Assert.assertThat(benefitList, Matchers.hasSize(2));



    }




    @Test
    public  void  analyzeBenefitControllerMethodDelete(){
        List<BenefitDTO> benefitList = benefitController.getAllBenefits();
        var aa = benefitList.size();

       var result =  benefitController.deleteBenefitById(1l);
        System.out.println(benefitList.toString()+ "wynik" + result);
       Assert.assertThat(benefitList, Matchers.hasSize(1));




    }

    private List<BenefitDTO> prepareMockListBenefit(){
         List<BenefitDTO> benefitList = new ArrayList<>();
         benefitList.add(new BenefitDTO(1l,"promocja swiateczna","50" ));
         benefitList.add(new BenefitDTO(2l,"promocja wakacyjna","30" ));

         return benefitList;
    }




}
