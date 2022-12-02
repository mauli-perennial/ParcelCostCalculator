package com.example.parceldeliveryapplication.serviceimpl;

import com.example.parceldeliveryapplication.costcalculator.ParcelCostCalculator;
import com.example.parceldeliveryapplication.dto.ParcelDTO;
import com.example.parceldeliveryapplication.enums.ParcelPriority;
import com.example.parceldeliveryapplication.helper.ParcelPriorityCheck;
import com.example.parceldeliveryapplication.service.VoucherService;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import tec.units.ri.quantity.Quantities;
import tec.units.ri.unit.Units;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ParcelServiceImplTest {

    @Mock
    private ParcelPriorityCheck parcelPriorityCheck;

    @Mock
    private VoucherService voucherService;

    @Mock
    private ParcelCostCalculator parcelCostCalculator;


    @InjectMocks
    private ParcelServiceImpl parcelService;


    @Test
    void parcelCostCalculator() {
        ParcelDTO parcelDTO = new ParcelDTO(10.0D,10.0D, 10.0D,10.0D);
        String voucher ="MYNT";
        ParcelPriority parcelPriority = ParcelPriority.THIRD;
        Mockito.when( parcelPriorityCheck.parcelPriority(parcelDTO)).thenReturn(parcelPriority);
        Mockito.when(parcelCostCalculator.getParcelCost(parcelPriority.toString(),parcelDTO)).thenReturn(30.0D);
        Mockito.when(voucherService.getDiscount(voucher)).thenReturn(12.25D);
        assertEquals(26.325D, parcelService.parcelCostCalculator(parcelDTO, voucher));
    }
}