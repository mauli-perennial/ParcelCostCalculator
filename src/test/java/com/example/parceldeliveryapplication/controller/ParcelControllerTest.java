package com.example.parceldeliveryapplication.controller;

import com.example.parceldeliveryapplication.helper.ParcelValidator;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@WebMvcTest(value = ParcelController.class)
class ParcelControllerTest {
    @Autowired
    private MockMvc mock;

    @MockBean
    private ParcelController parcelController;

    @Test
    void calculateParcelCost() {
        Double cost = 460.0;
     //   Mockito.when(parcelController.calculateParcelCost(23,9,10,10,"18V")).thenReturn(cost);
        assertEquals(cost, parcelController.calculateParcelCost(23,9,10,10,"18V"));
    }
}