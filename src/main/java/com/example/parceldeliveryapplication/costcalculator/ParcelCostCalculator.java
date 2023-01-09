package com.example.parceldeliveryapplication.costcalculator;

import com.example.parceldeliveryapplication.model.Parcel;
import com.example.parceldeliveryapplication.enums.ParcelCostRuleType;
import com.example.parceldeliveryapplication.exceptions.InvalidParcelException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Command Pattern implementation for calculating cost of the parcel.
 */
@Component
public class ParcelCostCalculator {
    private static final Map<String, CostCalculator> COSTS;

    static {
        final Map<String, CostCalculator> costs = new HashMap<>();
        costs.put("FIRST", parcelDTO -> 0);
        costs.put("SECOND", ParcelCostRuleType.HEAVY_PARCEL::calculateCost);
        costs.put("THIRD", ParcelCostRuleType.SMALL_PARCEL::calculateCost);
        costs.put("FOURTH", ParcelCostRuleType.MEDIUM_PARCEL::calculateCost);
        costs.put("FIFTH", ParcelCostRuleType.LARGE_PARCEL::calculateCost);
        COSTS = Collections.unmodifiableMap(costs);
    }

    @Value("${REJECT_PARCEL}")
    private String rejectParcel;

    /**
     * @param priority parcel priority is input to method which is used to call specific method to calculate the cost
     * @param parcel   parcel is the input which is used for calculating the cost of the parcel
     * @return this method return the cost of the parcel
     */
    public double getParcelCost(String priority, Parcel parcel) {
        double cost = 0;
        CostCalculator command = COSTS.get(priority);
        if (command == null) {
            throw new IllegalArgumentException("Invalid parcel type type: " + priority);
        }
        if (!priority.equalsIgnoreCase("FIRST")) {
            cost = command.getCost(parcel);

        } else {
            throw new InvalidParcelException(rejectParcel);
        }
        return cost;
    }
}
