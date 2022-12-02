package com.example.parceldeliveryapplication.helper;

import com.example.parceldeliveryapplication.dto.ParcelDTO;
import com.example.parceldeliveryapplication.enums.ParcelPriority;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This class is for the validation of the parcel provided by the customers according to their weight , length,width,height;
 **/
@Component
@Slf4j
public class ParcelPriorityCheck {

    /**
     * Constants value fetched from properties file;
     */
    @Value("${parcel.reject}")
    private double rejectParcel;

    @Value("${parcel.heavy}")
    private double heavyParcel;

    @Value("${parcel.small}")
    private double smallParcel;

    @Value("${parcel.medium}")
    private double mediumParcel;

    /**
     * @param parcel which is the object for the parcel with all fields required for that .
     * @return it returns the volume of the parcel .
     */
    public static double parcelVolume(ParcelDTO parcel) {
        return parcel.getHeight() * parcel.getLength() * parcel.getWidth();
    }

    /**
     * @param parcel it takes the parcel object is parameter .
     * @return the String which is the priority of the parcel.
     */
    public ParcelPriority parcelPriority(ParcelDTO parcel) {
        log.info("In Parcel Validation---->");
        ParcelPriority priority = ParcelPriority.DEFAULT;
        double volume = parcelVolume(parcel);
        log.info("Parcel Volume is---->" + volume);
        double weight = parcel.getWeight();
        if (volume > 0 && parcel.getWeight() > 0) {
            if (weight > rejectParcel) {
                priority = ParcelPriority.FIRST;
            } else if (weight > heavyParcel) {
                priority = ParcelPriority.SECOND;
            } else if (volume <= smallParcel) {
                priority = ParcelPriority.THIRD;
            } else if (volume > heavyParcel && volume <= mediumParcel) {
                priority = ParcelPriority.FOURTH;
            } else if (volume > mediumParcel) {
                priority = ParcelPriority.FIFTH;
            }
        }
        log.info("Parcel Validation Completed");
        return priority;
    }
}
