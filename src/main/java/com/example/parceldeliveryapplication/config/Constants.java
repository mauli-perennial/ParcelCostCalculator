package com.example.parceldeliveryapplication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Configurations for the constants needed in Application for logging,errors, validations we can use by importing this ones.
 */
@Configuration
public class Constants {
    public static double SMALL_FACTOR;
    public static double LARGE_FACTOR;
    public static double MEDIUM_FACTOR;
    public static double HEAVY_FACTOR;

    @Value("${factor.small}")
    public void setSmallFactor(final double propertyName) {
        SMALL_FACTOR = propertyName;
    }

    @Value("${factor.large}")
    public void setLargeFactor(final double propertyName) {
        LARGE_FACTOR = propertyName;
    }

    @Value("${factor.heavy}")
    public void setHeavyFactor(final double propertyName) {
        HEAVY_FACTOR = propertyName;
    }

    @Value("${factor.medium}")
    public void setProperty(final double propertyName) {
        MEDIUM_FACTOR = propertyName;
    }

}
