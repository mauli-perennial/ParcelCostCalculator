package com.example.parceldeliveryapplication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Value("${VOUCHER_RESOURCE}")
    private String voucherServiceResourceUrl;

    @Bean
    public WebClient GetWebclient() {
        return WebClient.builder().baseUrl(voucherServiceResourceUrl).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    }
}
