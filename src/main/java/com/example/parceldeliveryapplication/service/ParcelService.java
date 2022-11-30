package com.example.parceldeliveryapplication.service;

import com.example.parceldeliveryapplication.dto.ParcelDTO;

public interface ParcelService {
    double parcelCostCalculator(ParcelDTO parcel,Integer voucher);
}
