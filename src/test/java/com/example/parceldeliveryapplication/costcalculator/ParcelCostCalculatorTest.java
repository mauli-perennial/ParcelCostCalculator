package com.example.parceldeliveryapplication.costcalculator;

import com.example.parceldeliveryapplication.dto.ParcelDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ParcelCostCalculatorTest {
    @InjectMocks
    ParcelCostCalculator parcelCostCalculator;

    @Test
    void getParcelCost() {
        ParcelDTO parcelDTO = new ParcelDTO(10,10,10,10);
        String priority = "THIRD";
        assertEquals(30.0D,parcelCostCalculator.getParcelCost(priority,parcelDTO));
    }
}