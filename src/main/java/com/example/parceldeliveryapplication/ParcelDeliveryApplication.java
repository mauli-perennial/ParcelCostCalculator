package com.example.parceldeliveryapplication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class ParcelDeliveryApplication {
    @Value("${VOUCHER_RESOURCE}")
    private String voucherServiceResourceUrl;

    public static void main(String[] args) {
        SpringApplication.run(ParcelDeliveryApplication.class, args);
    }
    @Bean
    public WebClient getWebClient () {
        return WebClient.create(voucherServiceResourceUrl);
    }
}
