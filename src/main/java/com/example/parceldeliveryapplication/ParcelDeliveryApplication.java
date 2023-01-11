package com.example.parceldeliveryapplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
public class ParcelDeliveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParcelDeliveryApplication.class, args);
    }
}
