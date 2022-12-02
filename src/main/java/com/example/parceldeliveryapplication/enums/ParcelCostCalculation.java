package com.example.parceldeliveryapplication.enums;

import com.example.parceldeliveryapplication.dto.ParcelDTO;
import com.example.parceldeliveryapplication.helper.ParcelPriorityCheck;

/**
 * enum is for calculating the cost according to priority;
 */
public enum ParcelCostCalculation {
    HEAVY_PARCEL {
        @Override
        public double calculateCost(ParcelDTO dto) {
            return dto.getWeight() * Double.parseDouble(CostConversionFactors.HEAVY.factor);

        }
    }, LARGE_PARCEL {
        @Override
        public double calculateCost(ParcelDTO dto) {
            return (ParcelPriorityCheck.parcelVolume(dto)) * Double.parseDouble(CostConversionFactors.LARGE.factor);

        }
    }, MEDIUM_PARCEL {
        @Override
        public double calculateCost(ParcelDTO dto) {
            return (ParcelPriorityCheck.parcelVolume(dto)) * Double.parseDouble(CostConversionFactors.MEDIUM.factor);

        }
    }, SMALL_PARCEL {
        @Override
        public double calculateCost(ParcelDTO dto) {
            return (ParcelPriorityCheck.parcelVolume(dto)) * Double.parseDouble(CostConversionFactors.SMALL.factor);

        }
    },
    DEFAULT_PARCEL {
        @Override
        public double calculateCost(ParcelDTO dto) {
            return 0;

        }
    };

    public abstract double calculateCost(ParcelDTO dto);
}
