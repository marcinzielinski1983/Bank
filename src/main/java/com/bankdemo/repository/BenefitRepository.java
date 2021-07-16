package com.bankdemo.repository;

import com.bankdemo.entity.Benefit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BenefitRepository extends JpaRepository<Benefit,Long> {

}
