package com.example.parceldeliveryapplication.service;

import com.example.parceldeliveryapplication.dto.ParcelDTO;

public interface ParcelService {
    String parcelCostCalculator(ParcelDTO parcel,String voucher);
}
