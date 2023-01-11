package com.example.parceldeliveryapplication.service;

import com.example.parceldeliveryapplication.model.Parcel;

public interface ParcelService {
    double parcelCostCalculator(Parcel parcel, String voucher);
    double getDiscountOnVoucher(String voucher);
    double getVolume(Parcel parcel);
}
