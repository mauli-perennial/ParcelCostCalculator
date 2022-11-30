package com.example.parceldeliveryapplication.enums;

import com.example.parceldeliveryapplication.config.Constants;
import com.example.parceldeliveryapplication.exceptions.InvalidParcelException;
import com.example.parceldeliveryapplication.helper.ParcelValidator;
import com.example.parceldeliveryapplication.dto.ParcelDTO;

public enum ParcelCostCalculation {
    REJECT {
        @Override
        public double calculateCost(ParcelDTO dto) {
            if (Double.parseDouble(dto.getWeight().toString().substring(0, 5)) > Constants.REJECT) {
                throw new InvalidParcelException(Constants.REJECT_PARCEL);
            }
            return 0.0;

        }
    }, HEAVY_PARCEL {
        @Override
        public double calculateCost(ParcelDTO dto) {
            return Double.parseDouble(dto.getWeight().toString().substring(0, 5)) * Double.parseDouble(CostConversionFactors.HEAVY.factor);

        }
    }, LARGE_PARCEL {
        @Override
        public double calculateCost(ParcelDTO dto) {
            return (Double.parseDouble(ParcelValidator.parcelVolume(dto).toString().substring(0,6))) * Double.parseDouble(CostConversionFactors.LARGE.factor);

        }
    }, MEDIUM_PARCEL {
        @Override
        public double calculateCost(ParcelDTO dto) {
            return (Double.parseDouble(ParcelValidator.parcelVolume(dto).toString().substring(0,6))) * Double.parseDouble(CostConversionFactors.MEDIUM.factor);

        }
    }, SMALL_PARCEL {
        @Override
        public double calculateCost(ParcelDTO dto) {
            return (Double.parseDouble(ParcelValidator.parcelVolume(dto).toString().substring(0,6))) * Double.parseDouble(CostConversionFactors.SMALL.factor);

        }
    };

    public abstract double calculateCost(ParcelDTO dto);
}
