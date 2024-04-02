package com.marketvalue.sapient.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.marketvalue.sapient.Entity.Investor;
import com.marketvalue.sapient.Service.InvestorService;

@RestController
public class InvestorController {

    @Autowired
    private InvestorService investorService;
    
    @PostMapping("/investor")
    public ResponseEntity<String> addInvestor(@RequestBody Investor investor) throws Exception {
        investorService.addInvestor(investor);
        return ResponseEntity.status(HttpStatus.CREATED).body("Investor added successfully");
    }
}
