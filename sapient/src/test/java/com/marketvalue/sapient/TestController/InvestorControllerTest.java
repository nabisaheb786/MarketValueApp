package com.marketvalue.sapient.TestController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.marketvalue.sapient.Controller.InvestorController;
import com.marketvalue.sapient.Entity.Investor;
import com.marketvalue.sapient.Service.InvestorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class InvestorControllerTest {

    @InjectMocks
    private InvestorController investorController;

    @Mock
    private InvestorService investorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddInvestor_Positive() throws Exception {
        // Given
        Investor investor = new Investor();
        doNothing().when(investorService).addInvestor(any(Investor.class));

        // When
        ResponseEntity<String> responseEntity = investorController.addInvestor(investor);

        // Then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Investor added successfully", responseEntity.getBody());
        verify(investorService, times(1)).addInvestor(any(Investor.class));
    }

    @Test
    public void testAddInvestor_Negative() throws Exception {
        // Given
        Investor investor = new Investor();
        doThrow(new RuntimeException("Failed to add investor")).when(investorService).addInvestor(any(Investor.class));

        // When
        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = investorController.addInvestor(investor);
        } catch (RuntimeException e) {
            // Then
            assertEquals("Failed to add investor", e.getMessage());
            return; // Exit the test method as the exception is expected
        }

        // If no exception is thrown, fail the test
        fail("Expected RuntimeException was not thrown");

        // If you want to further assert the response entity, you can do it here
    }

}

