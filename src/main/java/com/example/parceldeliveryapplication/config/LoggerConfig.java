package com.example.parceldeliveryapplication.config;

import com.example.parceldeliveryapplication.ParcelDeliveryApplication;
import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
This Class is for logger configuration bean creation which can be used for adding logs.
 */
@Configuration
public class LoggerConfig {
    @Bean
    public Logger getLogger() {
        Logger log = LogManager.getLogger(String.valueOf(ParcelDeliveryApplication.class));
        PropertyConfigurator.configure(getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + "log4j.properties");
        return log;
    }
}
