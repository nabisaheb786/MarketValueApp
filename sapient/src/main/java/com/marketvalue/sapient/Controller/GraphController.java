package com.marketvalue.sapient.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marketvalue.sapient.Service.GraphService;

@RestController
public class GraphController {

    @Autowired
    private GraphService graphService;

 

    @GetMapping("/marketValue/{fundName}")
    public ResponseEntity<Double> calculateMarketValue(@PathVariable String fundName, @RequestParam(required = false, name = "exclusion") String exclusion) {
        double marketValue = graphService.calculateMarketValue(fundName, exclusion);
        return ResponseEntity.ok(marketValue);
    }
    
    @GetMapping("/InvestorMarketValue/{investorName}")
    public ResponseEntity<Double> calculateInvestorMarketValue(@PathVariable String investorName, @RequestParam(required = false, name = "exclusion") String exclusion) {
        double investorMarketValue = graphService.calculateInvestorMarketValue(investorName, exclusion);
        return ResponseEntity.ok(investorMarketValue);
    }
    
}
