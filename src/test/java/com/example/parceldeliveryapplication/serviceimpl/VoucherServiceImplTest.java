package com.example.parceldeliveryapplication.serviceimpl;

import com.example.parceldeliveryapplication.exceptions.DnsTimeoutException;
import com.example.parceldeliveryapplication.exceptions.InvalidVoucherException;
import com.example.parceldeliveryapplication.model.Voucher;
import io.netty.resolver.dns.DnsNameResolverTimeoutException;
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
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

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
          }

    @Test
    void getDiscount() throws ParseException {
        String date = "2020-09-16";
        String voucher = "MYNT";
        double discount = 0.0D;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Voucher vouchers = new Voucher(voucher, discount, format.parse(date));
        when(webClient.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(anyString())).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.onStatus(Mockito.any(Predicate.class), Mockito.any())).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<Voucher>>notNull())).thenReturn(voucherMono);
        when(voucherMono.block()).thenReturn(vouchers);
        assertEquals(discount, voucherService.getDiscount(voucher));
    }

    @Test
    void getDiscountWithException() throws ParseException {
        String date = "2020-09-16";
        String voucher = "ytty";
        double discount = 12.25D;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Voucher vouchers = new Voucher(voucher, discount, format.parse(date));
        when(webClient.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(anyString())).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.onStatus(Mockito.any(Predicate.class), Mockito.any())).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<Voucher>>notNull())).thenReturn(voucherMono);
        when(voucherMono.block()).thenThrow(InvalidVoucherException.class);
        assertThrows(InvalidVoucherException.class, () -> voucherService.getDiscount(voucher));
    }

    @Test
    void getTimeoutException() throws ParseException {
        when(webClient.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(anyString())).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenThrow(DnsNameResolverTimeoutException.class);
        assertThrows(DnsTimeoutException.class,() -> voucherService.getDiscount("adfas"));
    }
    @Test
    void checkExpiry() throws ParseException {
        String date = "2020-09-16";
        String voucher = "MYNT";
        double discount = 12.25D;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Voucher vouchers = new Voucher(voucher, discount, format.parse(date));
        assertEquals(true ,voucherService.checkExpiry(vouchers) );
    }
}