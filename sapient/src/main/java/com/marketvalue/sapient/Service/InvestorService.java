package com.marketvalue.sapient.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.marketvalue.sapient.Entity.Investor;
import com.marketvalue.sapient.Repository.InvestorRepository;

@Service
public class InvestorService {

    @Autowired
    private InvestorRepository investorRepository;

    @Autowired
    private FundService fundService;
    
    public void addInvestor(Investor investor ) throws Exception {
    	Investor invest = getInvestor(investor.getName());
    	if(invest!=null) {
    		throw new Exception("Investor already exist");
    	}
        investorRepository.save(investor);
    }
    
    public Investor getInvestor(String investor) {
    	return investorRepository.findByName(investor);
    }

	public void setInvestorRepository(InvestorRepository investorRepository) {
		this.investorRepository= investorRepository;
		
	}
	
	 @ExceptionHandler(value = RuntimeException.class)
	    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
	    }

}

