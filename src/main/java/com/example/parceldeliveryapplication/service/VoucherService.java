package com.example.parceldeliveryapplication.service;

import com.example.parceldeliveryapplication.model.Voucher;

public interface VoucherService {
    double getDiscount(String voucher);
     boolean checkExpiry(Voucher voucher);
}
