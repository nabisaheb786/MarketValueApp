package com.marketvalue.sapient.TestController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.marketvalue.sapient.Controller.GraphController;
import com.marketvalue.sapient.Service.GraphService;

public class GraphControllerTest {

    @Mock
    private GraphService graphService;

    @InjectMocks
    private GraphController graphController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateMarketValue_PositiveScenario() {
        // Mock data
        String fundName = "TestFund";
        String exclusion = "TestExclusion";
        double expectedMarketValue = 1000.0;

        // Mock service behavior
        when(graphService.calculateMarketValue(fundName, exclusion)).thenReturn(expectedMarketValue);

        // Test controller method
        ResponseEntity<Double> response = graphController.calculateMarketValue(fundName, exclusion);

        // Verify interactions and assertions
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedMarketValue, response.getBody());
        verify(graphService, times(1)).calculateMarketValue(fundName, exclusion);
    }

    @Test
    public void testCalculateMarketValue_NegativeScenario() {
        // Mock data
        String fundName = "NonexistentFund";
        String exclusion = null;

        // Mock service behavior
        when(graphService.calculateMarketValue(fundName, exclusion)).thenReturn(Double.NaN);

        // Test controller method
        ResponseEntity<Double> response = graphController.calculateMarketValue(fundName, exclusion);

        // Verify interactions and assertions
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Double.NaN, response.getBody());
        verify(graphService, times(1)).calculateMarketValue(fundName, exclusion);
    }
}
