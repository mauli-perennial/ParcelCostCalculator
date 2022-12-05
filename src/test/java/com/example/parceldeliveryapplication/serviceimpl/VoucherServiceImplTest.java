package com.example.parceldeliveryapplication.serviceimpl;

import com.example.parceldeliveryapplication.exceptions.InvalidVoucherException;
import com.example.parceldeliveryapplication.model.Voucher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class VoucherServiceImplTest {
    @Mock
    WebClient webClient;
    @InjectMocks
    VoucherServiceImpl voucherService;
    @BeforeEach
    void init(){
        ReflectionTestUtils.setField(voucherService,"apiKey","?key=apikey");
        ReflectionTestUtils.setField(voucherService,"invalidVoucher","voucher is invalid");
    }

    @Test
    void getDiscount() throws ParseException {
        String date = "2020-09-16";
        String voucher = "MYNT";
        double discount = 12.25D;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Voucher vouchers = new Voucher(voucher,discount,format.parse(date));
        Mockito.when(webClient.get().uri(Mockito.anyString(),Mockito.anyString()).retrieve().onStatus(HttpStatus::isError, clientResponse -> Mono.error(new InvalidVoucherException(Mockito.anyString()))).bodyToMono(Voucher.class).block()).thenReturn(vouchers);
        assertEquals(discount,voucherService.getDiscount(voucher));
    }
}