package com.example.parceldeliveryapplication.serviceimpl;

import com.example.parceldeliveryapplication.config.Constants;
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


    /**
     * This method is used for the getting discount for voucherCode code from external service
     *
     * @param voucherCode voucherCode code is provided
     * @return discount for voucherCode according the voucherCode code provided ;
     */
    @Override
    public double getDiscount(String voucherCode) {
        log.info("Request Processing started for voucherCode-->");
        double discount = 0;
        try {
            log.info("Processing discount request on the voucherCode-->");
            Voucher voucher = webClient.get().
                    uri(voucherCode + apiKey).
                    retrieve().
                    onStatus(HttpStatus::isError, clientResponse -> Mono.error(new InvalidVoucherException(Constants.INVALID_VOUCHER)))
                    .bodyToMono(Voucher.class).block();
            if (voucher != null && Objects.requireNonNull(voucher).getDiscount() > 0) {
                discount = voucher.getDiscount();
                log.debug("Discount applied on the voucherCode  " + voucherCode + "Is " + discount);
                log.debug("Expiry applied on the voucherCode  " + voucherCode + "Is " + voucher.getExpiry());
            }
            return discount;
        } catch (Exception e) {
            log.error("Request Processing on voucherCode to get discount failed--->");
            throw new InvalidVoucherException(Constants.INVALID_VOUCHER);
        }
    }
}