package com.marketvalue.sapient.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketvalue.sapient.Entity.Holding;


public interface HoldingRepository extends JpaRepository<Holding, Long> {

	Holding findByName(String holdingName);
	
}