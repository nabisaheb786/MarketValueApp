package com.marketvalue.sapient.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.marketvalue.sapient.Entity.Fund;
import com.marketvalue.sapient.Entity.Investor;
import com.marketvalue.sapient.Service.FundService;
import com.marketvalue.sapient.Service.InvestorService;

@RestController
public class FundController {

    @Autowired
    private FundService fundService;
    
    @Autowired
    private InvestorService investorService;
      
    @PostMapping("/fund/{investorName}")
    public ResponseEntity<String> addFund(@RequestBody Fund fund, @PathVariable String investorName) {
        Investor investor = investorService.getInvestor(investorName);
        if (investor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Investor not found");
        }
        
        fund.setInvestor(investor);
        fundService.addFund(fund);
        
        return ResponseEntity.status(HttpStatus.CREATED).body("Fund added successfully");
    }
}
