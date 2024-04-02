package com.marketvalue.sapient.TestService;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.marketvalue.sapient.Entity.Investor;
import com.marketvalue.sapient.Repository.InvestorRepository;
import com.marketvalue.sapient.Service.InvestorService;

@ExtendWith(MockitoExtension.class)
public class InvestorServiceTest {

    private InvestorService investorService;
    private InvestorRepository investorRepository;

    @Before
    public void setup() {
        investorRepository = mock(InvestorRepository.class);
        investorService = new InvestorService();
        investorService.setInvestorRepository(investorRepository);
    }

    @Test
    public void testAddInvestor_Positive() throws Exception {
        Investor investor = new Investor();
        investor.setName("investor");
        investorService.addInvestor(investor);
        verify(investorRepository).save(investor);
    }

    @Test
    public void testGetInvestor_Positive() {
        String investorName = "investor";
        Investor expectedInvestor = new Investor();
        expectedInvestor.setName(investorName);

        when(investorRepository.findByName(investorName)).thenReturn(expectedInvestor);

        Investor actualInvestor = investorService.getInvestor(investorName);

        assertEquals(expectedInvestor, actualInvestor);
    }

    @Test
    public void testGetInvestor_Negative() {
        String investorName = "Nonexistent Investor";

        when(investorRepository.findByName(investorName)).thenReturn(null);

        Investor actualInvestor = investorService.getInvestor(investorName);

        assertNull(actualInvestor);
    }

  
}

