package com.marketvalue.sapient.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.marketvalue.sapient.Entity.Investor;

public interface InvestorRepository extends JpaRepository<Investor, Long> {

	Investor findByName(String investorName);
}