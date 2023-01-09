package com.example.parceldeliveryapplication.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

/**
 * This is the model class for the parcel which have fields weight,width,height,length.
 */

public class Parcel {
    /**
     * This is the mandatory field which will be required while initializing the object of the parcel.
     */
    @Max(value = 50, message = "Parcel weight should not be greater than 50")
    @Positive(message = "Weight should be positive")
    private Double weight;
    /**
     * This is the mandatory field which will be required while initializing the object of the parcel.
     */
    @Positive(message = "Height should be positive")
    private Double height;
    /**
     * This is the mandatory field which will be required while initializing the object of the parcel.
     */
    @Positive(message = "Width should be positive")
    private Double width;
    /**
     * This is the mandatory field which will be required while initializing the object of the parcel.
     */
    @Positive(message = "Length should be positive")
    private  Double length;

    public Parcel() {
    }

    public Parcel(double weight, double height, double width, double length) {
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.length = length;
    }


    public Double getWeight() {
        return weight;
    }

    public Double getHeight() {
        return height;
    }

    public Double getWidth() {
        return width;
    }

    public Double getLength() {
        return length;
    }

    public double parcelVolume() {
        return width * height * length;
    }
}
