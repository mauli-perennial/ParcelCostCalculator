package com.example.parceldeliveryapplication.enums;

/**
 * enum is for the cost conversation factors which can be changed when needed;
 */
public enum CostConversionFactors {
    REJECT("N/A"), HEAVY("20"), SMALL("0.03"), MEDIUM("0.04"), LARGE("0.05");
    public final String factor;

    CostConversionFactors(String factor) {
        this.factor = factor;
    }

    public String getFactor() {
        return factor;
    }

    @Override
    public String toString() {
        return factor;
    }
}


