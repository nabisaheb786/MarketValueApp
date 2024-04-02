package com.marketvalue.sapient.TestController;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.marketvalue.sapient.Controller.HoldingController;
import com.marketvalue.sapient.Entity.Fund;
import com.marketvalue.sapient.Entity.Holding;
import com.marketvalue.sapient.Entity.Investor;
import com.marketvalue.sapient.Service.FundService;
import com.marketvalue.sapient.Service.HoldingsService;
import com.marketvalue.sapient.Service.InvestorService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class HoldingControllerTest {

    @InjectMocks
    private HoldingController holdingController;

    @Mock
    private InvestorService investorService;

    @Mock
    private HoldingsService holdingService;

    @Mock
    private FundService fundService;

    private Investor mockInvestor;
    private Fund mockFund;
    private Holding mockHolding;

    @BeforeEach
    public void setup() {
        mockInvestor = new Investor("investor");
        mockFund = new Fund("Test Fund");
        mockHolding = new Holding();
    }

    @Test
    public void testAddHolding_PositiveScenario() {
        when(investorService.getInvestor(anyString())).thenReturn(mockInvestor);
        when(fundService.getFund(anyString())).thenReturn(mockFund);
        doNothing().when(holdingService).addHolding(any(Holding.class));

        ResponseEntity<String> response = holdingController.addHolding(mockHolding, "Test Fund", "John Doe");

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Holding added successfully", response.getBody());
    }

    @Test
    public void testAddHolding_NegativeScenario_InvestorNotFound() {
        when(investorService.getInvestor(anyString())).thenReturn(null);

        ResponseEntity<String> response = holdingController.addHolding(mockHolding, "Test Fund", "John Doe");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Investor or Fund not found", response.getBody());
    }

    @Test
    public void testAddHolding_NegativeScenario_FundNotFound() {
        when(investorService.getInvestor(anyString())).thenReturn(mockInvestor);
        when(fundService.getFund(anyString())).thenReturn(null);

        ResponseEntity<String> response = holdingController.addHolding(mockHolding, "Test Fund", "John Doe");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Investor or Fund not found", response.getBody());
    }
}
