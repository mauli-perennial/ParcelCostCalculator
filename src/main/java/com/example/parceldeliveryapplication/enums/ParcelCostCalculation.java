package com.example.parceldeliveryapplication.enums;

import com.example.parceldeliveryapplication.config.Constants;
import com.example.parceldeliveryapplication.dto.ParcelDTO;
import com.example.parceldeliveryapplication.exceptions.InvalidParcelException;
import com.example.parceldeliveryapplication.helper.ParcelValidator;

/**
 * enum is for calculating the cost according to priority;
 */
public enum ParcelCostCalculation {
    REJECT {
        @Override
        public double calculateCost(ParcelDTO dto) {
            if (dto.getWeight() > Constants.REJECT) {
                throw new InvalidParcelException(Constants.REJECT_PARCEL);
            }
            return 0.0;

        }
    }, HEAVY_PARCEL {
        @Override
        public double calculateCost(ParcelDTO dto) {
            return dto.getWeight() * Double.parseDouble(CostConversionFactors.HEAVY.factor);

        }
    }, LARGE_PARCEL {
        @Override
        public double calculateCost(ParcelDTO dto) {
            return (ParcelValidator.parcelVolume(dto)) * Double.parseDouble(CostConversionFactors.LARGE.factor);

        }
    }, MEDIUM_PARCEL {
        @Override
        public double calculateCost(ParcelDTO dto) {
            return (ParcelValidator.parcelVolume(dto)) * Double.parseDouble(CostConversionFactors.MEDIUM.factor);

        }
    }, SMALL_PARCEL {
        @Override
        public double calculateCost(ParcelDTO dto) {
            return (ParcelValidator.parcelVolume(dto)) * Double.parseDouble(CostConversionFactors.SMALL.factor);

        }
    };

    public abstract double calculateCost(ParcelDTO dto);
}
