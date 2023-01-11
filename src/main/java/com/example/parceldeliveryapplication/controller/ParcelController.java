package com.example.parceldeliveryapplication.controller;

import com.example.parceldeliveryapplication.model.Parcel;
import com.example.parceldeliveryapplication.service.ParcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * This Is Parcel Controller used for handling the request to the particular method.
 */
@RestController
@RequestMapping("/parcel")
@Slf4j
public class ParcelController {
    @Autowired
    private ParcelService parcelService;


    /**
     * @param parcel  This Will take parcel object as input parameter from request body with length,width,weight,height fields
     * @param voucher This will Take the voucher String as a input which is required for the
     * @return It returns the calculated cost for the parcel.
     * This method returns us calculation cost  of the parcel according to priority of the parcel or will give proper error message to user if he enters invalid data
     */
    @PostMapping("/cost")
    public ResponseEntity<Double> calculateParcelCost(@Valid @RequestBody(required = true) Parcel parcel, @RequestParam(required = false, defaultValue = "") String voucher) {
        log.info("Request Processing started for parcel cost calculation-->");
        return ResponseEntity.ok(parcelService.parcelCostCalculator(parcel, voucher));
    }
}
