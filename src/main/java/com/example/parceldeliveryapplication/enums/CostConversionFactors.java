package com.example.parceldeliveryapplication.enums;

public enum CostConversionFactors {
    REJECT("N/A"), HEAVY("20"), SMALL("0.03"), MEDIUM("0.04"), LARGE("0.05");
    public final String factor;

    CostConversionFactors(String factor) {
        this.factor = factor;
    }
}


