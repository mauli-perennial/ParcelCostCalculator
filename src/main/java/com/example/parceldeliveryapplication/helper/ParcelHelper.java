package com.example.parceldeliveryapplication.helper;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParcelHelper {
    private double volume;
    private double weight;
    private double rejectParcel;
    private double smallParcel;
    private double heavyParcel;
    private double mediumParcel;
}
