package com.example.parceldeliveryapplication.serviceimpl;

import com.example.parceldeliveryapplication.model.Voucher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class VoucherServiceImplTest {

    @InjectMocks
    VoucherServiceImpl voucherService;
    @Mock
    private WebClient webClient;
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpecMock;
    @SuppressWarnings("rawtypes")
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;
    @Mock
    private WebClient.ResponseSpec responseSpecMock;
    @Mock
    private Mono<Voucher> voucherMono;

    @BeforeEach
    void init() {
        ReflectionTestUtils.setField(voucherService, "apiKey", "?key=apikey");
        ReflectionTestUtils.setField(voucherService, "invalidVoucher", "voucher is invalid");
    }

    @Test
    void getDiscount() throws ParseException {
        String date = "2020-09-16";
        String voucher = "MYNT";
        double discount = 12.25D;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Voucher vouchers = new Voucher(voucher, discount, format.parse(date));
        Mockito.when(webClient.get()).thenReturn(requestHeadersUriSpecMock);
        Mockito.when(requestHeadersUriSpecMock.uri(Mockito.anyString())).thenReturn(requestHeadersSpecMock);
        Mockito.when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        Mockito.when(responseSpecMock.onStatus(Mockito.any(Predicate.class), Mockito.any())).thenReturn(responseSpecMock);
        Mockito.when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<Voucher>> notNull())).thenReturn(voucherMono);
        Mockito.when(voucherMono.block()).thenReturn(vouchers);
        assertEquals(discount, voucherService.getDiscount(voucher));
    }
}