package com.marketvalue.sapient.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.marketvalue.sapient.Entity.Fund;
import com.marketvalue.sapient.Entity.Holding;
import com.marketvalue.sapient.Entity.Investor;
import com.marketvalue.sapient.Service.FundService;
import com.marketvalue.sapient.Service.HoldingsService;
import com.marketvalue.sapient.Service.InvestorService;

@RestController
public class HoldingController {
	
    @Autowired
    private InvestorService investorService;

    @Autowired
    private HoldingsService holdingService;
    
    @Autowired
    FundService fundService;
    
    @PostMapping("/holding/{fundName}/{investorName}")
    public ResponseEntity<String> addHolding(@RequestBody Holding holding, @PathVariable String fundName, @PathVariable String investorName) {
        Investor investor = investorService.getInvestor(investorName);
        Fund fund = fundService.getFund(fundName);

        if (investor == null || fund == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Investor or Fund not found");
        }

        fund.setInvestor(investor);
        holding.setFund(fund);
        holdingService.addHolding(holding);

        return ResponseEntity.status(HttpStatus.CREATED).body("Holding added successfully");
    }
}
