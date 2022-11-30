package com.example.parceldeliveryapplication.serviceImpl;

import com.example.parceldeliveryapplication.config.Constants;
import com.example.parceldeliveryapplication.dto.ParcelDTO;
import com.example.parceldeliveryapplication.enums.ParcelCostCalculation;
import com.example.parceldeliveryapplication.helper.ParcelValidator;
import com.example.parceldeliveryapplication.model.Voucher;
import com.example.parceldeliveryapplication.service.ParcelService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ParcelServiceImpl implements ParcelService {
    @Autowired
    private ParcelValidator validator;

    @Autowired
    private Logger logger;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public double parcelCostCalculator(ParcelDTO parcel, Integer voucher) {

        String parcelType = validator.parcelValidation(parcel);
        logger.info("parcel type is ------>" + parcelType);
        if (parcelType.equalsIgnoreCase(ParcelCostCalculation.REJECT.toString())) {
            throw new RuntimeException(Constants.REJECTPARCEL);
        }
        double cost = 0;

        switch (parcelType) {
            case "FIRST":
                cost = ParcelCostCalculation.REJECT.calculateCost(parcel);
                break;
            case "SECOND":
                cost = ParcelCostCalculation.HEAVY_PARCEL.calculateCost(parcel);
                break;
            case "THIRD":
                cost = ParcelCostCalculation.SMALL_PARCEL.calculateCost(parcel);
                break;
            case "FOURTH":
                cost = ParcelCostCalculation.MEDIUM_PARCEL.calculateCost(parcel);
                break;
            case "FIFTH":
                cost = ParcelCostCalculation.LARGE_PARCEL.calculateCost(parcel);
                break;

        }

       /* Voucher response = restTemplate.getForObject(Constants.VOUCHER_RESOURCE + voucher, Voucher.class);

        logger.info("discount for the applied voucher is ---->" + response.getDiscount());

        if (response != null) {
            cost = cost - cost * (response.getDiscount() / 100);
        } else {
            throw new RuntimeException("coupon is expired");
        }*/
        return cost;

    }

}
