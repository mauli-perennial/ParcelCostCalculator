package com.example.parceldeliveryapplication.controller;

import com.example.parceldeliveryapplication.dto.ParcelDTO;
import com.example.parceldeliveryapplication.helper.ParcelPriorityCheck;
import com.example.parceldeliveryapplication.service.ParcelService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This Is Parcel Controller used for handling the request to the particular method.
 */
@RestController
@RequestMapping("/parcel")
public class ParcelController {
    @Autowired
    private ParcelService parcelService;

    @Autowired
    private Logger logger;

    @Autowired
    private ParcelPriorityCheck parcelPriorityCheck;

    /**
     * @param parcel  This Will take parcel object as input parameter from request body with length,width,weight,height fields
     * @param voucher This will Take the voucher String as a input which is required for the
     * @return It returns the calculated cost for the parcel.
     */
    @PostMapping("/cost")
    public ResponseEntity<?> calculateParcelCost(@RequestBody(required = true) ParcelDTO parcel, @RequestParam(required = false, defaultValue = "") String voucher) {
        logger.info("In controller for the parcel---->");
        String cost = String.valueOf(parcelService.parcelCostCalculator(parcel, voucher));
        return ResponseEntity.ok(cost);
    }
}
