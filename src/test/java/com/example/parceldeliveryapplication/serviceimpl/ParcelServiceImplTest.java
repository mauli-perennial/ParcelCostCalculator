package com.example.parceldeliveryapplication.serviceimpl;

import com.example.parceldeliveryapplication.costcalculator.ParcelCostCalculator;
import com.example.parceldeliveryapplication.dto.ParcelDTO;
import com.example.parceldeliveryapplication.enums.ParcelPriority;
import com.example.parceldeliveryapplication.exceptions.BadVoucherException;
import com.example.parceldeliveryapplication.exceptions.InvalidParcelException;
import com.example.parceldeliveryapplication.exceptions.InvalidVoucherException;
import com.example.parceldeliveryapplication.helper.ParcelPriorityCheck;
import com.example.parceldeliveryapplication.service.VoucherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @BeforeEach
    void init() {
        ReflectionTestUtils.setField(parcelService, "rejectParcel", "large parcel");
        ReflectionTestUtils.setField(parcelService,"invalidVoucher","provided voucher is invalid");
    }

    @Test
    void parcelCostCalculator() {
        ParcelDTO parcelDTO = new ParcelDTO(10.0D, 10.0D, 10.0D, 10.0D);
        String voucher = "MYNT";
        ParcelPriority parcelPriority = ParcelPriority.THIRD;
        Mockito.when(parcelPriorityCheck.parcelPriority(parcelDTO)).thenReturn(parcelPriority);
        Mockito.when(parcelCostCalculator.getParcelCost(parcelPriority.toString(), parcelDTO)).thenReturn(30.0D);
        Mockito.when(voucherService.getDiscount(voucher)).thenReturn(12.25D);
        assertEquals(26.325D, parcelService.parcelCostCalculator(parcelDTO, voucher));
    }

    @Test
    void parcelCostCalculatorWithException() {
        ParcelDTO parcelDTO = new ParcelDTO(51.0D, 10.0D, 10.0D, 10.0D);
        String voucher = "MYNT";
        ParcelPriority parcelPriority = ParcelPriority.THIRD;
        Mockito.when(parcelPriorityCheck.parcelPriority(parcelDTO)).thenReturn(parcelPriority);
        Mockito.when(parcelCostCalculator.getParcelCost(parcelPriority.toString(), parcelDTO)).thenThrow(InvalidParcelException.class);
        assertThrows(InvalidParcelException.class,()->parcelService.parcelCostCalculator(parcelDTO,voucher));
    }

    @Test
    void parcelCostCalculatorWithVoucherException() {
        ParcelDTO parcelDTO = new ParcelDTO(10.0D, 10.0D, 10.0D, 10.0D);
        String voucher = "ee";
        ParcelPriority parcelPriority = ParcelPriority.THIRD;
        Mockito.when(parcelPriorityCheck.parcelPriority(parcelDTO)).thenReturn(parcelPriority);
        Mockito.when(parcelCostCalculator.getParcelCost(parcelPriority.toString(), parcelDTO)).thenReturn(30.0D);
        Mockito.when(voucherService.getDiscount(voucher)).thenThrow(BadVoucherException.class);
        assertThrows(BadVoucherException.class,()->parcelService.parcelCostCalculator(parcelDTO,voucher));
    }
}