package com.example.parceldeliveryapplication.costcalculator;

import com.example.parceldeliveryapplication.model.Parcel;

/**
 * Interface for using command pattern having one abstract method.
 */
public interface CostCalculator {
    double getCost(Parcel parcelDTO);
}
