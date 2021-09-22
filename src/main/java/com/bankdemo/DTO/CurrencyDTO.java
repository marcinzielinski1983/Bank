package com.bankdemo.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@Data
public class CurrencyDTO {


    private Long id;


    private String name;

    private Long valueLast;

    private Long timeOfTheLastUpdate;

}




