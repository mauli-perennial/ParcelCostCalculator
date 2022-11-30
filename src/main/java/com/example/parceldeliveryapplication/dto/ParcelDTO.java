package com.example.parceldeliveryapplication.dto;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;

public final class ParcelDTO {
    private final Quantity<Mass> weight;
    private final Quantity<Length> height;
    private final Quantity<Length> width;
    private final Quantity<Length> length;

    public ParcelDTO(final Quantity<Mass> weight, final Quantity<Length> height, final Quantity<Length> width, final Quantity<Length> length) {
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.length = length;
    }


    public Quantity<Mass> getWeight() {
        return weight;
    }

    public  Quantity<Length> getHeight() {
        return height;
    }

    public  Quantity<Length> getWidth() {
        return width;
    }

    public  Quantity<Length> getLength() {
        return length;
    }
}
