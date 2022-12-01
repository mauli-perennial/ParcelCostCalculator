package com.example.parceldeliveryapplication.helper;

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
@WebMvcTest(value = ParcelValidator.class)
class ParcelValidatorTest {
    @Autowired
    private MockMvc mock;

    @MockBean
    private ParcelValidator parcelValidator;

    @Test
    void parcelValidation() {
        ParcelDTO parcelDTO = new ParcelDTO(2,9,10,10);
        String parcelType = "THIRD";
        Mockito.when(parcelValidator.parcelValidation(parcelDTO)).thenReturn(parcelType);
        assertEquals(parcelType, parcelValidator.parcelValidation(parcelDTO));
    }
}