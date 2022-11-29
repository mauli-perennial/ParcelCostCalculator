package com.example.parceldeliveryapplication.controller;

import com.example.parceldeliveryapplication.dto.ParcelDTO;
import com.example.parceldeliveryapplication.service.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tec.units.ri.quantity.Quantities;
import tec.units.ri.unit.Units;

@RestController
@RequestMapping("/parcel")
public class ParcelController {
    @Autowired
    private ParcelService parcelService;

    @GetMapping("/cost")
    public ResponseEntity<Double> parcelDelivery(@RequestParam double weight, @RequestParam double height, @RequestParam double width, @RequestParam double length) {
        ParcelDTO parcel = new ParcelDTO(Quantities.getQuantity(weight, Units.KILOGRAM), Quantities.getQuantity(height,Units.METRE), Quantities.getQuantity(width,Units.METRE), Quantities.getQuantity(length,Units.METRE));
        return ResponseEntity.ok(parcelService.parcelCostCalculator(parcel));
    }
}
