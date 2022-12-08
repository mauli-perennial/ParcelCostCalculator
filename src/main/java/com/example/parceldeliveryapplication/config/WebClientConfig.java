package com.example.parceldeliveryapplication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * This class is used for the configuration of the web client in the application which is used for the calling external service.
 */
@Component
public class WebClientConfig {
    /*
    This constant is fetched from application properties file for getting voucher service url.
     */
    @Value("${VOUCHER_RESOURCE}")
    private String voucherServiceResourceUrl;

    @Bean
    public WebClient GetWebclient() {
        return WebClient.builder().baseUrl(voucherServiceResourceUrl).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    }
}
