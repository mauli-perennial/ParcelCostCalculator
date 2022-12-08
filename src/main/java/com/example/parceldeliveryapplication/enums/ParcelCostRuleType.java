package com.example.parceldeliveryapplication.enums;

import com.example.parceldeliveryapplication.model.Parcel;

/**
 * enum is for calculating the cost according to priority;
 */
public enum ParcelCostRuleType {
    HEAVY_PARCEL {
        @Override
        public double calculateCost(Parcel dto) {
            return dto.getWeight() * Double.parseDouble(CostConversionFactors.HEAVY.factor);

        }
    }, LARGE_PARCEL {
        @Override
        public double calculateCost(Parcel dto) {
            return (dto.parcelVolume() * Double.parseDouble(CostConversionFactors.LARGE.factor));

        }
    }, MEDIUM_PARCEL {
        @Override
        public double calculateCost(Parcel dto) {
            return (dto.parcelVolume() * Double.parseDouble(CostConversionFactors.MEDIUM.factor));

        }
    }, SMALL_PARCEL {
        @Override
        public double calculateCost(Parcel dto) {
            return (dto.parcelVolume() * Double.parseDouble(CostConversionFactors.SMALL.factor));

        }
    };

    public abstract double calculateCost(Parcel dto);
}
