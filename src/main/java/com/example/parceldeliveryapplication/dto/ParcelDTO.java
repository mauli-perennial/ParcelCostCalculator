package com.example.parceldeliveryapplication.dto;

public class ParcelDTO {
    private final double weight;
    private final double height;
    private final double width;
    private final double length;

    public ParcelDTO(double weight, double height, double width, double length) {
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.length = length;
    }


    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }
}
