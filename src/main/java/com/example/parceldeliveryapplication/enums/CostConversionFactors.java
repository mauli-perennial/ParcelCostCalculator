package com.example.parceldeliveryapplication.enums;

/**
 * This enum is for the factors for the calculating the cost of the parcel according to its priority.
 */
public enum CostConversionFactors {
    HEAVY("20.0"), LARGE("0.05"), MEDIUM("0.04"), SMALL("0.03");
    public String factor;

    CostConversionFactors(String factor) {
        this.factor = factor;
    }
}

