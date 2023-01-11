package com.example.parceldeliveryapplication.costcalculator;

import com.example.parceldeliveryapplication.config.Constants;
import com.example.parceldeliveryapplication.model.Parcel;
import com.example.parceldeliveryapplication.enums.CostByParcelType;
import com.example.parceldeliveryapplication.exceptions.InvalidParcelException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Command Pattern implementation for calculating cost of the parcel.
 */
@Component
@Slf4j
public class ParcelCostCalculator {
    private static final Map<String, CostCalculator> COSTS;

    static {
        final Map<String, CostCalculator> costs = new HashMap<>();
        costs.put("FIRST", parcelDTO -> 0);
        costs.put("SECOND", CostByParcelType.HEAVY_PARCEL::calculateCost);
        costs.put("THIRD", CostByParcelType.SMALL_PARCEL::calculateCost);
        costs.put("FOURTH", CostByParcelType.MEDIUM_PARCEL::calculateCost);
        costs.put("FIFTH", CostByParcelType.LARGE_PARCEL::calculateCost);
        COSTS = Collections.unmodifiableMap(costs);
    }

    /**
     * @param priority parcel priority is input to method which is used to call specific method to calculate the cost
     * @param parcel   parcel is the input which is used for calculating the cost of the parcel
     * @return this method return the cost of the parcel
     */
    public double getParcelCost(String priority, Parcel parcel) {
        double cost = 0;
        log.info("Request processing for calculation started for parcel with priority--->" + priority);
        CostCalculator command = COSTS.get(priority);
        if (command == null) {
            throw new IllegalArgumentException("Invalid parcel type type: " + priority);
        }
        if (!priority.equalsIgnoreCase("FIRST")) {
            cost = command.getCost(parcel);
            log.debug("Cost for the requested parcel with  " + priority + "is " + cost);

        } else {
            log.error("Request processing for calculating the cost is failed due invalid parcel--->");
            throw new InvalidParcelException(Constants.REJECT_PARCEL);
        }
        return cost;
    }
}
