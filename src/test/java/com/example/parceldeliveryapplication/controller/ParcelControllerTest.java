package com.example.parceldeliveryapplication.controller;

import com.example.parceldeliveryapplication.dto.ParcelDTO;
import com.example.parceldeliveryapplication.service.ParcelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ParcelControllerTest {

    @InjectMocks
    private ParcelController parcelController;
    @Mock
    ParcelService parcelService;

    @Test
    void calculateParcelCost() {
        String cost = "8.325";
        ParcelDTO parcelDTO = new ParcelDTO(1,3,19,10);
        String voucher = "MYNT";
        Mockito.when(parcelService.parcelCostCalculator(parcelDTO,voucher)).thenReturn(Double.valueOf(cost));
        assertEquals(Double.valueOf(cost).toString(), parcelController.calculateParcelCost(parcelDTO,voucher).getBody().toString());
    }
}