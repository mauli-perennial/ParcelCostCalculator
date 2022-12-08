package com.example.parceldeliveryapplication.controller;

import com.example.parceldeliveryapplication.exceptions.InvalidParcelException;
import com.example.parceldeliveryapplication.exceptions.InvalidVoucherException;
import com.example.parceldeliveryapplication.model.Parcel;
import com.example.parceldeliveryapplication.service.ParcelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ParcelControllerTest {

    @Mock
    ParcelService parcelService;

    @InjectMocks
    private ParcelController parcelController;

    @Test
    void calculateParcelCost() {
        double cost = 27.75;
        Parcel parcelDTO = new Parcel(10.0D, 10.0D, 10.0D, 10.0D);
        String voucher = "MYNT";
        Mockito.when(parcelService.parcelCostCalculator(parcelDTO, voucher)).thenReturn(cost);
        assertEquals(String.valueOf(cost), (parcelController.calculateParcelCost(parcelDTO, voucher).getBody()));
    }

    @Test
    void calculateParcelCostWithException() {
        Parcel parcelDTO = new Parcel(110.0D, 10.0D, 10.0D, 10.0D);
        String voucher = "MYNT";
        Mockito.when(parcelService.parcelCostCalculator(parcelDTO, voucher)).thenThrow(InvalidParcelException.class);
        assertThrows(InvalidParcelException.class, () -> parcelController.calculateParcelCost(parcelDTO, voucher).getBody());
    }

    @Test
    void calculateParcelCostWithVoucherException() {
        Parcel parcelDTO = new Parcel(10.0D, 10.0D, 10.0D, 10.0D);
        String voucher = "MYNTss";
        Mockito.when(parcelService.parcelCostCalculator(parcelDTO, voucher)).thenThrow(InvalidVoucherException.class);
        assertThrows(InvalidVoucherException.class, () -> parcelController.calculateParcelCost(parcelDTO, voucher).getBody());
    }
}