package com.example.parceldeliveryapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParcelDto {
    private double volume;
    private double weight;
    private double rejectParcel;
    private double smallParcel;
    private double heavyParcel;
    private double mediumParcel;
}
