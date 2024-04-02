package com.marketvalue.sapient.Service;

import com.marketvalue.sapient.Entity.Fund;
import com.marketvalue.sapient.Entity.Holding;
import com.marketvalue.sapient.Repository.FundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class FundService {

    @Autowired
    private FundRepository fundRepository;
    
    public void addFund(Fund fund) {
        fundRepository.save(fund);
    }
    
    public Fund getFund(String fundName) {
    	return fundRepository.findByName(fundName);
    }
  

    public FundService(FundRepository fundRepository) {
        this.fundRepository = fundRepository;
    }
}
