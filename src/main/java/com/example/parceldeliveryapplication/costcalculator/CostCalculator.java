package com.example.parceldeliveryapplication.costcalculator;

import com.example.parceldeliveryapplication.dto.ParcelDTO;

public interface CostCalculator {
    double getCost(ParcelDTO parcelDTO);
}
