package com.example.parceldeliveryapplication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;




@ComponentScan
@EnableAutoConfiguration
@Configuration
@EnableSwagger2
public class ParcelDeliveryApplication {
    @Value("${VOUCHER_RESOURCE}")
    private String voucherServiceResourceUrl;

    public static void main(String[] args) {
        SpringApplication.run(ParcelDeliveryApplication.class, args);
    }
}
