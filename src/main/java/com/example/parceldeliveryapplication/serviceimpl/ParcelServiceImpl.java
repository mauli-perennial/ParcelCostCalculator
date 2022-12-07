package com.example.parceldeliveryapplication.serviceimpl;

import com.example.parceldeliveryapplication.costcalculator.ParcelCostCalculator;
import com.example.parceldeliveryapplication.dto.ParcelDTO;
import com.example.parceldeliveryapplication.enums.ParcelPriority;
import com.example.parceldeliveryapplication.exceptions.BadVoucherException;
import com.example.parceldeliveryapplication.exceptions.InvalidParcelException;
import com.example.parceldeliveryapplication.helper.ParcelPriorityCheck;
import com.example.parceldeliveryapplication.service.ParcelService;
import com.example.parceldeliveryapplication.service.VoucherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * This class is for the parcel service implementation which is actully used for calclulation of the parcel cost
 */
@Service
@Slf4j
public class ParcelServiceImpl implements ParcelService {
    /**
     * validation bean injected to call the methods on helper for parcel priority;
     */
    @Autowired
    private ParcelPriorityCheck parcelPriority;
    /**
     * voucher service bean injected for accessing the voucher service
     */
    @Autowired
    private VoucherService voucherService;

    @Autowired
    private ParcelCostCalculator parcelCostCalculator;

    @Value("${invalid.voucher}")
    private String invalidVoucher;

    @Value("${REJECT_PARCEL}")
    private String rejectParcel;

    /**
     * @param parcel  parcel object and voucher code is input;
     * @param voucher This is coupon code provided for the service to get discount for which we are using the external service
     * @return return the calculated cost of the parcel
     */
    @Override
    public double parcelCostCalculator(ParcelDTO parcel, String voucher) {
        log.info("In Parcel service ----->");
        double cost = 0;
        ParcelPriority parcelType = parcelPriority.parcelPriority(parcel);
        try {
            log.info("Cost Calculation Started for Parcel--->");
            cost = parcelCostCalculator.getParcelCost(parcelType.toString(), parcel);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new InvalidParcelException(rejectParcel);
        }
        log.info("cost calculation done for the parcel--->");
        double discount = 0;
        try {
            if (!voucher.isEmpty() && !voucher.isBlank()) {
                discount = voucherService.getDiscount(voucher);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new BadVoucherException(invalidVoucher);
        }
        log.info(" response got from the voucher service");
        if (discount > 0) {
            cost = cost - (cost * (discount / 100));
        }
        return cost;
    }


}
