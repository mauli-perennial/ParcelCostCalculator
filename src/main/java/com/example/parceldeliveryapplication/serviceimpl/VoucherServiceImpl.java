package com.example.parceldeliveryapplication.serviceimpl;

import com.example.parceldeliveryapplication.exceptions.InvalidVoucherException;
import com.example.parceldeliveryapplication.model.Voucher;
import com.example.parceldeliveryapplication.service.VoucherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * class is used for the to connect with mocker server running on another system for voucher
 */
@Service
@Slf4j
public class VoucherServiceImpl implements VoucherService {

    /*
    This is for the calling the another service which is running on another server
     */


    @Autowired
    private WebClient webClient;
    @Value("${API_KEY}")
    private String apiKey;

    @Value("${invalid.voucher}")
    private String invalidVoucher;


    /**
     * This method is used for the getting discount for voucher code from external service
     *
     * @param voucher voucher code is provided
     * @return discount for voucher according the voucher code provided ;
     */
    public double getDiscount(String voucher) {
        log.info("In Voucher service for the parcel discount ");
        double discount = 0;
        Voucher vouchers = webClient.get().uri(voucher + apiKey).retrieve().onStatus(HttpStatus::isError, clientResponse -> Mono.error(new InvalidVoucherException(invalidVoucher))).bodyToMono(Voucher.class).block();
        log.info("Discount for the voucher is---->");
        if (vouchers != null && Objects.requireNonNull(vouchers).getDiscount() > 0) {
            discount = vouchers.getDiscount();
        }
        return discount;
    }
}