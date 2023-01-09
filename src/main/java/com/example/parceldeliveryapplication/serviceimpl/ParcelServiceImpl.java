package com.example.parceldeliveryapplication.serviceimpl;

import com.example.parceldeliveryapplication.costcalculator.ParcelCostCalculator;
import com.example.parceldeliveryapplication.dto.ParcelDto;
import com.example.parceldeliveryapplication.enums.ParcelPriority;
import com.example.parceldeliveryapplication.exceptions.InvalidParcelException;
import com.example.parceldeliveryapplication.exceptions.InvalidVoucherException;
import com.example.parceldeliveryapplication.model.Parcel;
import com.example.parceldeliveryapplication.service.ParcelService;
import com.example.parceldeliveryapplication.service.VoucherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * This class is for the parcel service implementation which is actully used for calclulation of the parcel cost
 */
@Service
@Slf4j
public class ParcelServiceImpl implements ParcelService {
    /**
     * voucher service bean injected for accessing the voucher service
     */
    @Autowired
    private VoucherService voucherService;

    @Autowired
    private ParcelCostCalculator parcelCostCalculator;

    /**
     * Constants value fetched from properties file;
     */

    @Value("${invalid.voucher}")
    private String invalidVoucher;

    @Value("${REJECT_PARCEL}")
    private String rejectParcels;

    @Value("${parcel.reject}")
    private double rejectParcel;
    @Value("${parcel.heavy}")
    private double heavyParcel;
    @Value("${parcel.small}")
    private double smallParcel;
    @Value("${parcel.medium}")
    private double mediumParcel;


    /**
     * @param parcel  parcel object and voucher code is input;
     * @param voucher This is coupon code provided for the service to get discount for which we are using the external service
     * @return return the calculated cost of the parcel
     */
    @Override
    public double parcelCostCalculator(Parcel parcel, String voucher) {
        log.info("In Parcel service ----->");

        double cost = 0;

        ParcelDto parcelDto = new ParcelDto(parcel.parcelVolume(), parcel.getWeight(), rejectParcel, smallParcel, heavyParcel, mediumParcel);
        try {
            for (ParcelPriority priority : ParcelPriority.values()) {
                if (!Objects.equals(priority.getPriority(parcelDto), "")) {
                    log.info("Parcel priority is---"+priority);
                    cost = parcelCostCalculator.getParcelCost(priority.toString(), parcel);
                    break;
                }
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new InvalidParcelException(rejectParcels);
        }

        cost = cost - (cost * getDiscountOnVoucher(voucher) / 100);

        log.info("cost calculation done for the parcel--->");
        return cost;
    }

    /**
     * @param voucher This Method takes voucher as input for getting discount from another service
     * @return This method returns the discount for the parcel
     */
    @Override
    public double getDiscountOnVoucher(String voucher) {
        log.info("Calling voucher service---->");
        double discount = 0;
        try {
            if (!voucher.isEmpty() && !"".equals(voucher)) {
                discount = voucherService.getDiscount(voucher);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new InvalidVoucherException(invalidVoucher);
        }
        log.info(" response got from the voucher service");
        return discount;
    }
}
