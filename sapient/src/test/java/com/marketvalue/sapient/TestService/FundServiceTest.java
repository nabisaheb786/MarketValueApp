package com.marketvalue.sapient.TestService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.marketvalue.sapient.Entity.Fund;
import com.marketvalue.sapient.Repository.FundRepository;
import com.marketvalue.sapient.Service.FundService;

@ExtendWith(MockitoExtension.class)
public class FundServiceTest {

    private FundRepository fundRepository;
    private FundService fundService;

    @BeforeEach
    void setUp() {
        fundRepository = mock(FundRepository.class);
        fundService = new FundService(fundRepository);
    }

    @Test
    void testAddFund_PositiveScenario() {
        
        Fund mockFund = new Fund("Test Fund");
        when(fundRepository.save(mockFund)).thenReturn(mockFund);

       
        fundService.addFund(mockFund);

        assertEquals(mockFund, fundRepository.save(mockFund));
    }

    @Test
    void testGetFund_PositiveScenario() {
      
        Fund mockFund = new Fund("Test Fund");
        when(fundRepository.findByName("Test Fund")).thenReturn(mockFund);

     
        Fund retrievedFund = fundService.getFund("Test Fund");

       
        assertEquals(mockFund, retrievedFund);
    }

    @Test
    void testGetFund_NegativeScenario() {
        
        when(fundRepository.findByName("Nonexistent Fund")).thenReturn(null);

    
        Fund retrievedFund = fundService.getFund("Nonexistent Fund");

  
        assertEquals(null, retrievedFund);
    }
}
