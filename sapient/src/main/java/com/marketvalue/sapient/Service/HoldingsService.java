package com.marketvalue.sapient.Service;

import com.marketvalue.sapient.Entity.Holding;
import com.marketvalue.sapient.Entity.Investor;
import com.marketvalue.sapient.Repository.HoldingRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HoldingsService {

    @Autowired
    private HoldingRepository holdingsRepository;
    
    public void addHolding(Holding holding) {
    	holdingsRepository.save(holding);
    }
    
    public Optional<Holding> getInvestor(Long holdingId ) {
    	return holdingsRepository.findById(holdingId);
    }

	public void setHoldingRepository(HoldingRepository holdingRepository) {
		this.holdingsRepository= holdingsRepository;
		
	}
}
