package com.example.parceldeliveryapplication.serviceImpl;

import com.example.parceldeliveryapplication.dto.ParcelDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import tec.units.ri.quantity.Quantities;
import tec.units.ri.unit.Units;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ParcelServiceImpl.class)
class ParcelServiceImplTest {

    @MockBean
    private ParcelServiceImpl parcelService;

    @Test
    void parcelCostCalculator() {
        ParcelDTO parcelDTO = new ParcelDTO(Quantities.getQuantity(23, Units.KILOGRAM), Quantities.getQuantity(9, Units.METRE), Quantities.getQuantity(10, Units.METRE), Quantities.getQuantity(10, Units.METRE));
        double cost = 460.0;
        Mockito.when(parcelService.parcelCostCalculator(parcelDTO, 7)).thenReturn(cost);
        assertEquals(cost, parcelService.parcelCostCalculator(parcelDTO, 7));
    }
}