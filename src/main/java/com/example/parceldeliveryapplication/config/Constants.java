package com.example.parceldeliveryapplication.config;

import java.math.BigDecimal;

/*
This class is used constant creation which can be user across the application.
 */
public class Constants {
    public static final double REJECT = 50.0;
    public static final double HEAVY_PARCEL = 10.0;
    public static final double SMALL_PARCEL = 1500.0;
    public static final double MEDIUM_PARCEL = 2500.0;
    public static final String REJECT_PARCEL = "parcel size is too large parcel is rejected";

    public static final String VOUCHER_RESOURCE = "http://localhost:3000/coupon/";
    public static final String PARCEL_VALIDATION_ERROR= "error while validating the parcel";

    public static final String CURRENCY= "PHP";

    public static final String INVALID_VOUCHER="voucher is invalid";

    private Constants() {
    }
}

