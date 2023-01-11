package com.example.parceldeliveryapplication.enums;

import com.example.parceldeliveryapplication.config.Constants;
import com.example.parceldeliveryapplication.model.Parcel;

/**
 * enum is for calculating the cost according to priority;
 */
public enum CostByParcelType {
    HEAVY_PARCEL {
        @Override
        public double calculateCost(Parcel parcel) {
            return parcel.getWeight() * Constants.HEAVY_FACTOR;

        }
    }, LARGE_PARCEL {
        @Override
        public double calculateCost(Parcel parcel) {
            return (parcel.parcelVolume() * Constants.LARGE_FACTOR);

        }
    }, MEDIUM_PARCEL {
        @Override
        public double calculateCost(Parcel parcel) {
            return (parcel.parcelVolume() * Constants.MEDIUM_FACTOR);

        }
    }, SMALL_PARCEL {
        @Override
        public double calculateCost(Parcel parcel) {
            System.out.println("factor is"+Constants.SMALL_FACTOR);
            return (parcel.parcelVolume() * Constants.SMALL_FACTOR);

        }
    };

    public abstract double calculateCost(Parcel parcel);
}
