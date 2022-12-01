package com.example.parceldeliveryapplication.helper;

import com.example.parceldeliveryapplication.config.Constants;
import com.example.parceldeliveryapplication.dto.ParcelDTO;
import com.example.parceldeliveryapplication.enums.ParcelPriority;
import com.example.parceldeliveryapplication.exceptions.InvalidParcelException;
import org.springframework.stereotype.Component;
import tec.units.ri.unit.Units;

import javax.measure.Quantity;
import javax.measure.quantity.Volume;

import static com.example.parceldeliveryapplication.config.Constants.*;

/**
This class is for the validation of the parcel provided by the customers according to their weight , length,width,height;
 **/
@Component
public class ParcelValidator {
    /**
     * @param parcel which is the object for the parcel with all fields required for that .
     * @return it returns the volume of the parcel .
     */
    public static double parcelVolume(ParcelDTO parcel) {
        return  parcel.getHeight() * parcel.getLength() * parcel.getWidth();
    }

    /**
     * @param parcel it takes the parcel object is parameter .
     * @return the String which is the priority of the parcel.
     */
    public String parcelValidation(ParcelDTO parcel) {

        String priority = "";
        double volume;
        if (parcel.getLength()>0 && parcel.getHeight()>0 && parcel.getWidth()>0) {
            volume = parcelVolume(parcel);
        } else {
            throw new InvalidParcelException("units are not proper");
        }
        try {
            double weight = parcel.getWeight();
            if (weight >= REJECT) {
                priority = ParcelPriority.FIRST.toString();
            } else if (weight > HEAVY_PARCEL && weight <= REJECT) {
                priority = ParcelPriority.SECOND.toString();
            } else if (volume <= SMALL_PARCEL) {
                priority = ParcelPriority.THIRD.toString();
            } else if (volume > SMALL_PARCEL && volume <= MEDIUM_PARCEL) {
                priority = ParcelPriority.FOURTH.toString();
            } else if (volume >= Constants.MEDIUM_PARCEL) {
                priority = ParcelPriority.FIFTH.toString();
            }

        } catch (Exception e) {
            throw new InvalidParcelException(PARCEL_VALIDATION_ERROR);
        }
        return priority;
    }

}
