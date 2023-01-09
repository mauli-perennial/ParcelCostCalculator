package com.example.parceldeliveryapplication.costcalculator;

import com.example.parceldeliveryapplication.exceptions.InvalidParcelException;
import com.example.parceldeliveryapplication.model.Parcel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ParcelCostCalculatorTest {
    @InjectMocks
    ParcelCostCalculator parcelCostCalculator;

    @BeforeEach
    void init() {
        ReflectionTestUtils.setField(parcelCostCalculator, "rejectParcel", "parcel size is too large or low, Hence parcel is rejected");
    }

    @Test
    void getParcelCost() {
        Parcel parcelDTO = new Parcel(22, 10, 10, 10);
        String priority = "SECOND";
        assertEquals(0.0D, parcelCostCalculator.getParcelCost(priority, parcelDTO));
    }

    @Test
    void getParcelCostWithException() {
        Parcel parcelDTO = new Parcel(110, 10, 10, 10);
        String priority = "FIRST";
        assertThrows(InvalidParcelException.class, () -> parcelCostCalculator.getParcelCost(priority, parcelDTO));
    }
}