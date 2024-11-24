package com.engsoft.marmita.service;

import com.engsoft.marmita.model.integration.GeocodeResponse;
import com.engsoft.marmita.service.integration.GoogleGeocodingService;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    private final GoogleGeocodingService geocodingService;

    public LocationService(GoogleGeocodingService geocodingService) {
        this.geocodingService = geocodingService;
    }

    public String findLocation(String address) {
        return geocodingService.getLocation(address);
    }

    public GeocodeResponse.Location findCoordinates(String address) {
        return geocodingService.getCoordinates(address);
    }

}
