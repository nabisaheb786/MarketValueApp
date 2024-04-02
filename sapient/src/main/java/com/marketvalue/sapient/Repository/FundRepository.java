package com.marketvalue.sapient.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.marketvalue.sapient.Entity.Fund;

public interface FundRepository extends JpaRepository<Fund, Long> {

	Fund findByName(String fundName);
}