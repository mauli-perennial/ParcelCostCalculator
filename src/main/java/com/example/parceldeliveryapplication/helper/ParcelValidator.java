package com.example.parceldeliveryapplication.helper;

import com.example.parceldeliveryapplication.config.Constants;
import com.example.parceldeliveryapplication.enums.ParcelPriority;
import com.example.parceldeliveryapplication.dto.ParcelDTO;
import org.springframework.stereotype.Component;
import tec.units.ri.unit.Units;

import javax.measure.Quantity;
import javax.measure.quantity.Volume;

import static com.example.parceldeliveryapplication.config.Constants.*;

@Component
public class ParcelValidator {

    public static Quantity<Volume> parcelVolume(ParcelDTO parcel) {
        return (Quantity<Volume>) parcel.getHeight().multiply(parcel.getWidth()).multiply(parcel.getLength());
    }

    public String parcelValidation(ParcelDTO parcel) {

        String priority = "";
        double volume;
        try {
            if(parcel.getLength().getUnit().equals(Units.METRE) &&parcel.getLength().getUnit().equals(Units.METRE) && parcel.getLength().getUnit().equals(Units.METRE)){
                 volume = Double.parseDouble(parcelVolume(parcel).toString().substring(0, 6));
            }else {
                throw new RuntimeException("units are not proper");
            }
            double weight = Double.parseDouble(parcel.getWeight().toString().substring(0, 4));
            if (parcel.getWeight().getUnit().equals(Units.KILOGRAM) && weight >= REJECT) {
                priority = ParcelPriority.FIRST.toString();
            } else if (weight > HEAVYPARCEL && weight <= REJECT) {
                priority = ParcelPriority.SECOND.toString();
            } else if (volume <= SMALLPARCEL) {
                priority = ParcelPriority.THIRD.toString();
            } else if (volume > SMALLPARCEL && volume <= MEDIUMPARCEL) {
                priority = ParcelPriority.FOURTH.toString();
            } else if (volume >= Constants.MEDIUMPARCEL) {
                priority = ParcelPriority.FIFTH.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return priority;
    }

}
