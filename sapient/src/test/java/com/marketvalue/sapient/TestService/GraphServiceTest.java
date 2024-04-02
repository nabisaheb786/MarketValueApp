package com.marketvalue.sapient.TestService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.marketvalue.sapient.Entity.Fund;
import com.marketvalue.sapient.Entity.Holding;
import com.marketvalue.sapient.Entity.Investor;
import com.marketvalue.sapient.Repository.FundRepository;
import com.marketvalue.sapient.Repository.InvestorRepository;
import com.marketvalue.sapient.Service.GraphService;

@ExtendWith(MockitoExtension.class)
public class GraphServiceTest {

    private GraphService graphService;
    private InvestorRepository investorRepositoryMock;
    private FundRepository fundRepositoryMock;

    @Before
    public void setUp() {
        investorRepositoryMock = mock(InvestorRepository.class);
        fundRepositoryMock = mock(FundRepository.class);
        graphService = new GraphService();
        graphService.setInvestorRepository(investorRepositoryMock);
        graphService.setFundRepository(fundRepositoryMock);
    }

    @Test
    public void testCalculateMarketValue_PositiveScenario() {
        Fund mockFund = new Fund();
        mockFund.setName("TestFund");
        Holding holding = new Holding();
        holding.setName("TestHolding");
        holding.setQuantity(10);
        mockFund.getHoldings().add(holding);
        
        when(fundRepositoryMock.findByName("TestFund")).thenReturn(mockFund);

        double marketValue = graphService.calculateMarketValue("TestFund", null);

        assertEquals(1000.0, marketValue, 0.0);
    }

    @Test
    public void testCalculateMarketValue_NegativeScenario() {
      
        when(fundRepositoryMock.findByName("NonExistentFund")).thenReturn(null);

        double marketValue = graphService.calculateMarketValue("NonExistentFund", null);

        assertEquals(0.0, marketValue, 0.0);
    }

    @Test
    public void testCalculateInvestorMarketValue_PositiveScenario() {
        Investor mockInvestor = new Investor();
        mockInvestor.setName("TestInvestor");
        Fund mockFund = new Fund();
        mockFund.setName("TestFund");
        Holding holding = new Holding();
        holding.setName("TestHolding");
        holding.setQuantity(10);
        mockFund.getHoldings().add(holding);
        mockInvestor.getFunds().add(mockFund);
        
        when(investorRepositoryMock.findByName("TestInvestor")).thenReturn(mockInvestor);
        when(fundRepositoryMock.findByName("TestFund")).thenReturn(mockFund);

        double investorMarketValue = graphService.calculateInvestorMarketValue("TestInvestor", null);

        assertEquals(1000.0, investorMarketValue, 0.0);
    }

    @Test
    public void testCalculateInvestorMarketValue_NegativeScenario() {
        
        when(investorRepositoryMock.findByName("NonExistentInvestor")).thenReturn(null);

        double investorMarketValue = graphService.calculateInvestorMarketValue("NonExistentInvestor", null);

        assertEquals(0.0, investorMarketValue, 0.0);
    }
}

