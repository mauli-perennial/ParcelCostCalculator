package com.example.parceldeliveryapplication.costcalculator;

import com.example.parceldeliveryapplication.dto.ParcelDTO;
import com.example.parceldeliveryapplication.enums.ParcelCostCalculation;
import com.example.parceldeliveryapplication.exceptions.InvalidParcelException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class ParcelCostCalculator {

    private static final Map<String, CostCalculator> COSTS;
    @Value("${REJECT_PARCEL}")
    private  String rejectParcel;

    static {
        final Map<String, CostCalculator> costs = new HashMap<>();
        costs.put("FIRST", new CostCalculator() {
            @Override
            public double getCost(ParcelDTO parcelDTO) {
                return 0;
            }
        });
        costs.put("SECOND", ParcelCostCalculation.HEAVY_PARCEL::calculateCost);
        costs.put("THIRD", ParcelCostCalculation.SMALL_PARCEL::calculateCost);
        costs.put("FOURTH", ParcelCostCalculation.MEDIUM_PARCEL::calculateCost);
        costs.put("FIFTH", ParcelCostCalculation.LARGE_PARCEL::calculateCost);
        costs.put("DEFAULT", ParcelCostCalculation.DEFAULT_PARCEL::calculateCost);
        COSTS = Collections.unmodifiableMap(costs);
    }

    public double getParcelCost(String priority, ParcelDTO parcelDTO) {
        double cost=0;
        CostCalculator command = COSTS.get(priority);
        if (command == null) {
            throw new IllegalArgumentException("Invalid parcel type type: " + priority);
        }
        if(!priority.equalsIgnoreCase("FIRST") && !priority.equalsIgnoreCase("DEFAULT")){
           cost = command.getCost(parcelDTO);

        }else {
            throw new InvalidParcelException(rejectParcel);
        }
        return cost;
    }
}
