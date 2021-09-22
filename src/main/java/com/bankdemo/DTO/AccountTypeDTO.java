package com.bankdemo.DTO;

import com.bankdemo.entity.Benefit;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@Data
public class AccountTypeDTO {



    private Long Id;


    private String name;


    private List<Long> benefitsId;



}
