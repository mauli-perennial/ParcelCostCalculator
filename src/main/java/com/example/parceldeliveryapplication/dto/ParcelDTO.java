package com.example.parceldeliveryapplication.dto;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import java.math.BigDecimal;

public final class ParcelDTO {
    private final double weight;
    private final double height;
    private final double width;
    private final double length;

    public ParcelDTO(final double weight, final double height, final double width, final double length) {
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.length = length;
    }


    public double getWeight() {
        return weight;
    }

    public  double getHeight() {
        return height;
    }

    public  double getWidth() {
        return width;
    }

    public  double getLength() {
        return length;
    }
}
