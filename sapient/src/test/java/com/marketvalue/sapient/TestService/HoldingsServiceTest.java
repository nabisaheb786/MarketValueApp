package com.marketvalue.sapient.TestService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.marketvalue.sapient.Entity.Holding;
import com.marketvalue.sapient.Repository.HoldingRepository;
import com.marketvalue.sapient.Service.HoldingsService;

@ExtendWith(MockitoExtension.class)
public class HoldingsServiceTest {

    @InjectMocks
    private HoldingsService holdingsService;

    @Mock
    private HoldingRepository holdingRepository;

    @Test
    void testAddHolding_PositiveScenario() {
        Holding holding = new Holding();

        holdingsService.addHolding(holding);

        verify(holdingRepository, times(1)).save(holding);
    }

    @Test
    void testGetHolding_PositiveScenario() {
        long holdingId = 1L;
        Holding holding = new Holding(/* Add required parameters */);
        when(holdingRepository.findById(holdingId)).thenReturn(Optional.of(holding));

        Optional<Holding> retrievedHolding = holdingsService.getInvestor(holdingId);

        assertTrue(retrievedHolding.isPresent());
        assertEquals(holding, retrievedHolding.get());
    }

    @Test
    void testGetHolding_NegativeScenario() {
        long holdingId = 1L;
        when(holdingRepository.findById(holdingId)).thenReturn(Optional.empty());

        Optional<Holding> retrievedHolding = holdingsService.getInvestor(holdingId);

        assertFalse(retrievedHolding.isPresent());
    }
}
