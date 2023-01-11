package com.example.parceldeliveryapplication.serviceimpl;

import com.example.parceldeliveryapplication.costcalculator.ParcelCostCalculator;
import com.example.parceldeliveryapplication.helper.ParcelHelper;
import com.example.parceldeliveryapplication.enums.ParcelPriority;
import com.example.parceldeliveryapplication.exceptions.InvalidParcelException;
import com.example.parceldeliveryapplication.exceptions.InvalidVoucherException;
import com.example.parceldeliveryapplication.model.Parcel;
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
    private VoucherService voucherService;

    @Mock
    private ParcelCostCalculator parcelCostCalculator;


    @InjectMocks
    private ParcelServiceImpl parcelService;

    @BeforeEach
    void init() {
        ReflectionTestUtils.setField(parcelService, "rejectParcel", 50.0D);
        ReflectionTestUtils.setField(parcelService, "heavyParcel", 10.0D);
        ReflectionTestUtils.setField(parcelService, "smallParcel", 1500.0D);
        ReflectionTestUtils.setField(parcelService, "mediumParcel", 2500.0d);
    }

    @Test
    void parcelCostCalculatorTest() {
        Parcel parcel = new Parcel(10.0D, 10.0D, 10.0D, 10.0D);
        String voucher = "MYNT";
        ParcelHelper parcelDto = new ParcelHelper(parcel.parcelVolume(), parcel.getWeight(), 50.0D, 1500.0D, 10.0D, 2500.0D);
        ParcelPriority parcelPriority = ParcelPriority.THIRD;
        Mockito.when(parcelCostCalculator.getParcelCost(parcelPriority.toString(), parcel)).thenReturn(30.0D);
        Mockito.when(voucherService.getDiscount(voucher)).thenReturn(12.25D);
        assertEquals(26.325D, parcelService.parcelCostCalculator(parcel, voucher));
    }

    @Test
    void testInvalidParcelException() {
        Parcel parcelDTO = new Parcel(51.0D, 10.0D, 10.0D, 10.0D);
        String voucher = "MYNT";
        ParcelPriority parcelPriority = ParcelPriority.THIRD;
        Mockito.when(parcelCostCalculator.getParcelCost(parcelPriority.toString(), parcelDTO)).thenThrow(InvalidParcelException.class);
        assertThrows(InvalidParcelException.class, () -> parcelService.parcelCostCalculator(parcelDTO, voucher));
    }

    @Test
    void testInvalidVoucherException() {
        Parcel parcel = new Parcel(10.0D, 10.0D, 10.0D, 10.0D);
        String voucher = "djhd";
        ParcelHelper parcelDto = new ParcelHelper(parcel.parcelVolume(), parcel.getWeight(), 50.0D, 1500.0D, 1500.0D, 2500.0D);
        Mockito.when(voucherService.getDiscount(voucher)).thenThrow(InvalidVoucherException.class);
        assertThrows(InvalidVoucherException.class, () -> parcelService.parcelCostCalculator(parcel, voucher));
    }

    @Test
    void getDiscountOnVoucher() {
        String voucher = "MYNT";
        double discount = 12.25D;
        Mockito.when(voucherService.getDiscount(voucher)).thenReturn(discount);
        assertEquals(discount, parcelService.getDiscountOnVoucher(voucher));

    }

    @Test
    void testInvalidparcelException() {
        String voucher = "MYNTss";
        Mockito.when(voucherService.getDiscount(voucher)).thenThrow(InvalidVoucherException.class);
        assertThrows(InvalidVoucherException.class, () -> parcelService.getDiscountOnVoucher(voucher));

    }
}