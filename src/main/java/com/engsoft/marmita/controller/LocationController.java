package com.engsoft.marmita.controller;

import com.engsoft.marmita.model.integration.GeocodeResponse;
import com.engsoft.marmita.service.LocationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/geocode")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/localizacao")
    public String getLocation(@RequestParam String address) {
        return locationService.findLocation(address);
    }

    @GetMapping("/coordenadas")
    public GeocodeResponse.Location getCoordinates(@RequestParam String address) {
        return locationService.findCoordinates(address);
    }
}