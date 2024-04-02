
package com.marketvalue.sapient.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketvalue.sapient.Entity.Fund;
import com.marketvalue.sapient.Entity.Holding;
import com.marketvalue.sapient.Entity.Investor;
import com.marketvalue.sapient.Repository.FundRepository;
import com.marketvalue.sapient.Repository.HoldingRepository;
import com.marketvalue.sapient.Repository.InvestorRepository;

@Service
public class GraphService {

    @Autowired
    private InvestorRepository investorRepository;

    @Autowired
    private FundRepository fundRepository;

    @Autowired
    private HoldingRepository holdingRepository;

    public void addInvestor(String investorName) {
        investorRepository.save(new Investor(investorName));
    }

    public void addHolding(String holdingName, double value) {
        holdingRepository.save(new Holding());
    }

    
    public double calculateMarketValue(String fundName, String exclusions) {
    	Fund fund = fundRepository.findByName(fundName);
        if (fund == null) {
            return 0.0;
        }

        double marketValue = 0.0;
        double quantity = 100;
        for (Holding holding : fund.getHoldings()) {
            if (exclusions!=null && exclusions.equalsIgnoreCase(holding.getName())) {
            	Holding h = holdingRepository.findByName(exclusions);
                marketValue += holding.getQuantity() * quantity;
                marketValue = marketValue - h.getQuantity() * quantity;
            }else {
            	  marketValue += holding.getQuantity() * quantity;
            }
        }
        return marketValue;
    }
    
  
    public double calculateInvestorMarketValue(String investorName, String exclusions) {
        Investor investor = investorRepository.findByName(investorName);
        if (investor == null) {
            return 0.0;
        }

        double marketValue = 0.0;
        for (Fund fund : investor.getFunds()) {
            marketValue += calculateMarketValue(fund.getName(), exclusions);
        }
        return marketValue;
    }

	public void setInvestorRepository(InvestorRepository investorRepository) {
		this.investorRepository= investorRepository;
		
	}

	public void setFundRepository(FundRepository fundRepository) {
		this.fundRepository=fundRepository;
		
	}
    
}
