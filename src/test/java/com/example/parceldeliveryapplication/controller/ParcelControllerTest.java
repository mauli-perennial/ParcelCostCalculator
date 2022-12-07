package com.example.parceldeliveryapplication.controller;

import com.example.parceldeliveryapplication.dto.ParcelDTO;
import com.example.parceldeliveryapplication.service.ParcelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ParcelControllerTest {

    @Mock
    ParcelService parcelService;

    @InjectMocks
    private ParcelController parcelController;

    @Test
    void calculateParcelCost() {
        double cost = 27.75;
        ParcelDTO parcelDTO = new ParcelDTO(10.0D,10.0D,10.0D,10.0D);
        String voucher = "MYNT";
        Mockito.when(parcelService.parcelCostCalculator(parcelDTO,voucher)).thenReturn(cost);
        assertEquals(String.valueOf(cost), (parcelController.calculateParcelCost(parcelDTO,voucher).getBody()));
    }
}