package com.marketvalue.sapient.TestController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.marketvalue.sapient.Controller.FundController;
import com.marketvalue.sapient.Entity.Fund;
import com.marketvalue.sapient.Entity.Investor;
import com.marketvalue.sapient.Service.FundService;
import com.marketvalue.sapient.Service.InvestorService;

@ExtendWith(MockitoExtension.class)
public class FundControllerTest {

    @Mock
    private FundService fundService;

    @Mock
    private InvestorService investorService;

    @InjectMocks
    private FundController fundController;

    @Test
    public void testAddFund_Success() {
        // Mock data
        Fund fund = new Fund();
        Investor investor = new Investor();
        investor.setName("TestInvestor");

        // Mock behavior
        when(investorService.getInvestor("TestInvestor")).thenReturn(investor);

        // Perform the operation
        ResponseEntity<String> response = fundController.addFund(fund, "TestInvestor");

        // Verify
        verify(fundService).addFund(fund);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Fund added successfully", response.getBody());
    }

    @Test
    public void testAddFund_InvestorNotFound() {
        // Mock data
        Fund fund = new Fund();

        // Mock behavior
        when(investorService.getInvestor(anyString())).thenReturn(null);

        // Perform the operation
        ResponseEntity<String> response = fundController.addFund(fund, "NonExistingInvestor");

        // Verify
        verify(fundService, never()).addFund(any());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Investor not found", response.getBody());
    }
}
