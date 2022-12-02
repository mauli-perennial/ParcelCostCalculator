package com.example.parceldeliveryapplication.model;

import lombok.Data;

import java.util.Date;

/**
 * class is for the voucher from which we can decide the discount for parcel;
 */
@Data
public class Voucher {
    private String code;
    private double discount;
    private Date expiry;

    public Voucher(String code, double discount, Date expiry) {
        this.code = code;
        this.discount = discount;
        this.expiry = expiry;
    }

    public Voucher() {
    }
}
