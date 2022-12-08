package com.example.parceldeliveryapplication.model;

import com.example.parceldeliveryapplication.config.Constants;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

/**
 * This is the model class for the parcel which have fields weight,width,height,length.
 */

public class Parcel {
    /**
     * This is the mandatory field which will be required while initializing the object of the parcel.
     */
    @Positive(message = Constants.POSITIVE_WEIGHT_ATTRIBUTE_NEEDED)
    @Max(value = 50,message = "weight should not be grater than 50")
   // @Pattern(regexp = "[0-9]{1,13}(\\.[0-9]*)?",message = "Weight should not be In String")
    private  Double weight;
    /**
     * This is the mandatory field which will be required while initializing the object of the parcel.
     */
    @Positive(message = Constants.POSITIVE_HEIGHT_ATTRIBUTE_NEEDED)
    private  Double height;
    /**
     * This is the mandatory field which will be required while initializing the object of the parcel.
     */
    @Positive(message = Constants.POSITIVE_WIDTH_ATTRIBUTE_NEEDED)
    private  Double width;
    /**
     * This is the mandatory field which will be required while initializing the object of the parcel.
     */
    @Positive(message = Constants.POSITIVE_LENGTH_ATTRIBUTE_NEEDED)
    private Double length;

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
    public double parcelVolume(){
        return width*height*length;
    }
}
