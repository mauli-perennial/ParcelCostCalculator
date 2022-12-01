package com.example.parceldeliveryapplication.serviceimpl;

import com.example.parceldeliveryapplication.dto.ParcelDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import tec.units.ri.quantity.Quantities;
import tec.units.ri.unit.Units;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ParcelServiceImplTest.class)
class ParcelServiceImplTest {

    @MockBean
    private ParcelServiceImpl parcelService;

    @Test
    void parcelCostCalculator() {
        ParcelDTO parcelDTO = new ParcelDTO(23,9, 10,10);
        double cost = 460.0;
        Mockito.when(parcelService.parcelCostCalculator(parcelDTO, "7")).thenReturn(cost);
        assertEquals(cost, parcelService.parcelCostCalculator(parcelDTO, "7"));
    }
}