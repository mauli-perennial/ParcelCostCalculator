package com.example.parceldeliveryapplication.model;

import lombok.Data;

/**
 * class is for the voucher from which we can decide the discount for parcel;
 */
@Data
public class Voucher {
    private String id;
    private Integer discount;
}
