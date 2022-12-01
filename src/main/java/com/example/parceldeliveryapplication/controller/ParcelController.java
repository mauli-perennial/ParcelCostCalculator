package com.example.parceldeliveryapplication.controller;

import com.example.parceldeliveryapplication.dto.ParcelDTO;
import com.example.parceldeliveryapplication.service.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parcel")
public class ParcelController {
    @Autowired
    private ParcelService parcelService;

    @GetMapping
    public ResponseEntity<Double> calculateParcelCost(@RequestParam double weight, @RequestParam double height, @RequestParam double width, @RequestParam double length, @RequestParam String voucher) {
        ParcelDTO parcel = new ParcelDTO(weight, height, width, length);
        return ResponseEntity.ok(parcelService.parcelCostCalculator(parcel,voucher));
    }
}
