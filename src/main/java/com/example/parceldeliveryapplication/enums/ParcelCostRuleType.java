package com.example.parceldeliveryapplication.enums;

import com.example.parceldeliveryapplication.config.Constants;
import com.example.parceldeliveryapplication.model.Parcel;

/**
 * enum is for calculating the cost according to priority;
 */
public enum ParcelCostRuleType {
    HEAVY_PARCEL {
        @Override
        public double calculateCost(Parcel dto) {
            return dto.getWeight() * Constants.HEAVY_FACTOR;

        }
    }, LARGE_PARCEL {
        @Override
        public double calculateCost(Parcel dto) {
            return (dto.parcelVolume() * Constants.LARGE_FACTOR);

        }
    }, MEDIUM_PARCEL {
        @Override
        public double calculateCost(Parcel dto) {
            return (dto.parcelVolume() * Constants.MEDIUM_FACTOR);

        }
    }, SMALL_PARCEL {
        @Override
        public double calculateCost(Parcel dto) {
            System.out.println("factor is"+Constants.SMALL_FACTOR);
            return (dto.parcelVolume() * Constants.SMALL_FACTOR);

        }
    };

    public abstract double calculateCost(Parcel dto);
}
